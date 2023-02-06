package com.yuan.order;

import com.yuan.clients.ProductClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/5 15:24
 * @Description null
 */
@MapperScan(basePackages = "com.yuan.order.mapper")
@SpringBootApplication
@EnableFeignClients(clients = {ProductClient.class})
public class OrderApplication3010 {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication3010.class,args);
    }
}
