package com.yuan.clients;

import com.yuan.param.ProductCollectParam;
import com.yuan.pojo.Collect;
import com.yuan.pojo.Product;
import com.yuan.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

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

    @PostMapping("/product/collect/list")
    R productIds(@RequestBody ProductCollectParam productCollectParam);
    @PostMapping("/product/cart/detail")
     Product cdetail(@RequestBody Map<String,Integer> param);

    @PostMapping("/product/cart/list")
     List<Product> cartList(@RequestBody  ProductCollectParam param);

}
