package com.yuan.cart;

import com.yuan.clients.ProductClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/4 23:44
 * @Description 购物车服务
 */

@MapperScan(basePackages = "com.yuan.cart.mapper")
@SpringBootApplication
@EnableFeignClients(clients = {ProductClient.class})
public class CartApplication3009 {
    public static void main(String[] args) {
        SpringApplication.run(CartApplication3009.class,args);
    }

    /**
     * mq序列化方式，选择json！
     * @return
     */
    @Bean
    public MessageConverter messageConverter(){

        return new Jackson2JsonMessageConverter();
    }


}
