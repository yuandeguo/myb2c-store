package com.yuan.collect;

import com.yuan.clients.ProductClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/4 21:45
 * @Description null
 */
@MapperScan(basePackages = "com.yuan.collect.mapper")
@SpringBootApplication
@EnableFeignClients(clients = {ProductClient.class})
public class CollectApplication3008 {
    public static void main(String[] args) {
        SpringApplication.run(CollectApplication3008.class,args);
    }
}
