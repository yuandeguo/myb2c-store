package com.yuan;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * projectName: b2c_cloud_store
 *
 * @author: yuan
 * time: 2023/2/1 20:01
 * description: 该模块进行网关的配置
 * EnableDiscoveryClient服务注册标签
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class,args);
    }

}
