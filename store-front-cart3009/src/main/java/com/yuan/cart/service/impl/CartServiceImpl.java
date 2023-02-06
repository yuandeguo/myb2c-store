package com.yuan.cart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuan.cart.mapper.CartMapper;
import com.yuan.cart.service.CartService;
import com.yuan.clients.ProductClient;
import com.yuan.param.CartAddParam;
import com.yuan.param.ProductCollectParam;
import com.yuan.pojo.Cart;
import com.yuan.pojo.Product;
import com.yuan.utils.R;
import com.yuan.vo.CartVo;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/5 0:24
 * @Description null
 */
@Service
@Slf4j
public class CartServiceImpl extends ServiceImpl<CartMapper,Cart> implements CartService {

    @Resource
    private ProductClient productClient;

@Resource
private CartMapper cartMapper;
    /**
     * 添加购物车  001 成功   002 已经存在   003没有库存
     *
     * @param cartAddParam
     * @return
     */
    @Override
    public R save(CartAddParam cartAddParam) {

        Map<String,Integer> param=new HashMap<>();
        param.put("product_id",cartAddParam.getProductId());
//查询商品数据
        Product cdetail = productClient.cdetail(param);

        //检查库存
        if(cdetail==null)
        {
            return R.fail("商品已经被删除，无法添加");
        }
        if(cdetail.getProductNum()==0)
        {
           R r=  R.ok("没有库存，无法添加");
            r.setCode("003");
            return r;
        }
        //检查是否添加过
        QueryWrapper<Cart> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",cartAddParam.getUserId());
        queryWrapper.eq("product_id",cartAddParam.getProductId());
        Cart cart = cartMapper.selectOne(queryWrapper);
        if(cart!=null)
        {
            //证明存在 原数量+1
            cart.setNum(cart.getNum()+1);
            cartMapper.updateById(cart);
            R r=  R.ok("购物车存在该商品，数量+1");
            r.setCode("002");
            return r;
        }
//3.第一次结果封装
        cart = new Cart();
        cart.setNum(1);
        cart.setProductId(cartAddParam.getProductId());
        cart.setUserId(cartAddParam.getUserId());

        cartMapper.insert(cart);

        //结果封装
        CartVo cartVo = new CartVo(cdetail,cart);
        log.info("CartServiceImpl.save业务结束，结果:{}",cartVo);
        return R.ok(cartVo);

        //结果封装和返回



    }

    @Override
    public R list(Integer userId) {
        QueryWrapper<Cart> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        List<Cart> carts =  cartMapper.selectList(queryWrapper);
        if (carts==null||carts.isEmpty())
        {
            carts=new ArrayList<>();
            return R.ok("购物车空空如也",carts);
        }
        List<Integer> list=new ArrayList<>();
        carts.forEach(s->list.add(s.getProductId()));
        ProductCollectParam productCollectParam=new ProductCollectParam();
        productCollectParam.setProductIds(list);
        List<Product> products = productClient.cartList(productCollectParam);
    Map<Integer,Product> map=    products.stream().collect(Collectors.toMap(Product::getProductId,v->v));
List<CartVo> cartVos=new ArrayList<>();
        carts.forEach(s->cartVos.add(new CartVo(map.get(s.getProductId()),s)));
        return R.ok("数据库查询成功",cartVos);


    }

    /**
     * 更新购物车信息，1查询商品数据，库存是否可用，正常修改
     * @param cart
     * @return
     */
    @Override
    public R update(Cart cart) {

        Map<String,Integer> param=new HashMap<>();
        param.put("product_id",cart.getProductId());
//查询商品数据
        Product cdetail = productClient.cdetail(param);
if(cdetail==null||cdetail.getProductNum()<cart.getNum())
{
    log.info("***CartServiceImpl.update业务结束，结果:{}","库存不足" );
return R.fail("库存不足");
}
        QueryWrapper<Cart> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",cart.getUserId());
        queryWrapper.eq("product_id",cart.getProductId());
        Cart cart1 = cartMapper.selectOne(queryWrapper);
cart1.setNum(cart.getNum());
int rows =cartMapper.updateById(cart1);
        log.info("***CartServiceImpl.update业务结束，结果:{}",rows );
        return R.ok("修改成功");
    }

    @Override
    public R remove(Cart cart) {

        QueryWrapper<Cart> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",cart.getUserId());
        queryWrapper.eq("product_id",cart.getProductId());

        int delete = cartMapper.delete(queryWrapper);
        log.info("***CartServiceImpl.remove业务结束，结果:{}",delete );
        return R.ok("删除数据成功!");

    }


}
