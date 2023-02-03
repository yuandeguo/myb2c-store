package com.yuan.carousel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/2 23:55
 * @Description 轮播图api
 */
@SpringBootApplication
@MapperScan(basePackages = "com.yuan.carousel.mapper")
public class CarouselApplication {
    public static void main(String[] args) {
        SpringApplication.run(CarouselApplication.class,args);
    }
}
