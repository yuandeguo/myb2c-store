package com.yuan.user;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

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

    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题(该属性会在旧插件移除后一同移除)
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
