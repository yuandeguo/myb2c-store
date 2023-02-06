package com.yuan.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuan.clients.ProductClient;
import com.yuan.order.mapper.OrderMapper;
import com.yuan.order.service.OrderService;
import com.yuan.param.OrderParam;
import com.yuan.param.ProductCollectParam;
import com.yuan.pojo.Order;
import com.yuan.pojo.Product;
import com.yuan.to.OrderToProduct;
import com.yuan.utils.R;
import com.yuan.vo.CartVo;
import com.yuan.vo.OrderVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/5 15:34
 * @Description 直接调用service的方法，使用baseMapper调用mapper的原始方法
 */
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
@Resource
    ProductClient productClient;
@Resource
    private RabbitTemplate rabbitTemplate;
    /**
     * 进行订单数据保存业务
     * 1 将购物车数据转化为订单数据，
     * 2 进行订单数据批量插入
     * 3 商品库存的修改
     * 4 发送购物车库存修改消息
     * @param orderParam
     * @return
     */
    @Override
    @Transactional
    public R save(OrderParam orderParam) {
        //修改清空购物车的参数
        List<Integer> cartIds = new ArrayList<>();
        //修改批量插入数据库的参数
        List<Order>  orderList = new ArrayList<>();
        //商品修改库存参数集合
        List<OrderToProduct>  orderToProductList  =
                new ArrayList<>();

        Integer userId = orderParam.getUserId();
        List<CartVo> products = orderParam.getProducts();
        //封装order实体类集合
        //统一生成订单编号和创建时间
        //使用时间戳 + 做订单编号和事件 ctime作为商品id

        long ctime = System.currentTimeMillis();

        for (CartVo cartVo : products) {
            cartIds.add(cartVo.getId()); //进行购物车订单保存
            //订单信息保存
            Order order = new Order();
            order.setOrderId(ctime);
            order.setUserId(userId);
            order.setOrderTime(ctime);
            order.setProductId(cartVo.getProductID());
            order.setProductNum(cartVo.getNum());
            order.setProductPrice(cartVo.getPrice());
            orderList.add(order); //添加用户信息
            //修改信息存储
            OrderToProduct orderToProduct = new OrderToProduct();
            orderToProduct.setProductId(cartVo.getProductID());
            orderToProduct.setNum(cartVo.getNum());
            orderToProductList.add(orderToProduct); //添加集合
        }
        //批量数据插入//
        this.saveBatch(orderList); //批量保存

        //修改商品库存 [product-service] [异步通知]
        /**
         *  交换机: topic.ex
         *  routingkey: sub.number
         *  消息: 商品id和减库存数据集合
         */
        rabbitTemplate.convertAndSend("topic.ex","sub.number",orderToProductList);
        //清空对应购物车数据即可 [注意: 不是清空用户所有的购物车数据] [cart-service] [异步通知]
        /**
         * 交换机:topic.ex
         * routingkey: clear.cart
         * 消息: 要清空的购物车id集合
         */
        rabbitTemplate.convertAndSend("topic.ex","clear.cart",cartIds);

        R ok = R.ok("订单生成成功!");
        log.info("OrderServiceImpl.save业务结束，结果:{}",ok);
        return ok;

    }

    @Override
    public R orderList(Integer user_id) {

        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("user_id",user_id);
        List<Order> orderList = this.list(orderQueryWrapper);

        //数据按订单分组
        Map<Long, List<Order>> listMap = orderList.stream().
                collect(Collectors.groupingBy(Order::getOrderId));
        List<Integer> productIds      =orderList.stream().map(Order::getProductId).collect(Collectors.toList());

        ProductCollectParam productCollectParam=new ProductCollectParam();
        productCollectParam.setProductIds(productIds);

        List<Product> products = productClient.cartList(productCollectParam);
        Map<Integer, Product> productCollect = products.stream().collect(Collectors.toMap(Product::getProductId, v -> v));
//结果封装
        List<List<OrderVo>> result = new ArrayList<>();
        for (List<Order> orders : listMap.values()) {
            List<OrderVo> orderVos = new ArrayList<>();
            for (Order order : orders) {
                //返回vo数据封装
                OrderVo orderVo = new OrderVo();
                BeanUtils.copyProperties(order,orderVo);
                Product product = productCollect.get(order.getProductId());
                orderVo.setProductNum(order.getProductNum());
                orderVo.setProductId(order.getProductId());
                orderVo.setProductPrice(order.getProductPrice());
                orderVo.setProductName(product.getProductName());
                orderVo.setProductPicture(product.getProductPicture());
                orderVos.add(orderVo);

            }
            result.add(orderVos);
        }

        R ok = R.ok(result);
        log.info("OrderServiceImpl.myList业务结束，结果:{}",ok);
        return ok;


    }
}
