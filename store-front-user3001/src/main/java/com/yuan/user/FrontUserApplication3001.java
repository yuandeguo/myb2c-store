package com.yuan.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/2 16:47
 * @Description 前台服务
 *
 *      * TODO:
 *      *   1.定义接收参数的param 并且添加参数校验注解
 *      *   2.定义controller
 *      *   3.定义service
 *      *   4.定义mapper
 *      *
 *
 *
 */
@MapperScan(basePackages = "com.yuan.user.mapper")
@SpringBootApplication
//开启feign的客户端,暂时不需要
@EnableFeignClients()
public class FrontUserApplication3001 {

    public static void main(String[] args) {
        SpringApplication.run(FrontUserApplication3001.class,args);
    }


}
