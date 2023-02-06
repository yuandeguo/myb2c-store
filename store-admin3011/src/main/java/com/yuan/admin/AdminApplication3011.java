package com.yuan.admin;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.yuan.clients.CategoryClient;
import com.yuan.clients.SearchClient;
import com.yuan.clients.UserClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/5 22:59
 * @Description null
 */
@MapperScan("com.yuan.admin.mapper")
@SpringBootApplication
@EnableCaching //开启缓存支持
@EnableFeignClients(clients = {UserClient.class, CategoryClient.class, SearchClient.class})  //添加客户端引用
public class AdminApplication3011  {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication3011.class,args);
    }


}

