package com.yuan.cart.listener;

import com.yuan.cart.service.CartService;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/5 17:39
 * @Description 监听mq消息
 */
@Component
public class CartListener {

    @Resource
    private CartService cartService;

    /**
     * 购物车数据清空监听
     * @param cartIds //要清空的购物车数据集合
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "clear.queue"),
            exchange = @Exchange("topic.ex"),
            key = "clear.cart"
    ))
    public void subNumber(List<Integer> cartIds){


        //调用业务修改库存即可
        cartService.removeBatchByIds(cartIds);
    }
}
