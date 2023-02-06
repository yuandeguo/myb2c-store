package com.yuan.product.listener;

import com.yuan.product.service.ProductService;
import com.yuan.to.OrderToProduct;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/5 17:42
 * @Description null
 */
@Component
public class ProductListener {
    @Resource
    private ProductService productService;
    /**
     * 修改库存数据
     * @param orderToProductList
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "sub.queue"),
            exchange = @Exchange("topic.ex"),
            key = "sub.number"
    ))
    public void subNumber(List<OrderToProduct> orderToProductList){

        //调用业务修改库存即可
        productService.subNumber(orderToProductList);
    }


}
