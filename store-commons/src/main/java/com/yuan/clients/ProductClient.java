package com.yuan.clients;

import com.yuan.pojo.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/3 23:48
 * @Description 商品服务的客户端
 */
@FeignClient(value = "product-service3004")
public interface ProductClient {

    /**
     * 商品全部数据调用
     * @return
     */
    @GetMapping("/product/list")
    List<Product> list();
}
