package com.yuan.product.controller;

import com.yuan.param.ProductCollectParam;
import com.yuan.param.ProductDetailParam;
import com.yuan.pojo.Product;
import com.yuan.product.service.ProductService;
import com.yuan.utils.R;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/5 0:08
 * @Description null
 */
@RestController
@RequestMapping("product")
public class ProductCartController {
    @Resource
    private ProductService productService;


    @PostMapping("cart/detail")
    public Product cdetail(@RequestBody Map<String,Integer> param)
    {
        Integer productID   = param.get("product_id");
        if(productID==null)
            return null;
        ProductDetailParam productDetailParam = new ProductDetailParam();
        productDetailParam .setProductID(productID);
        R detail = productService.detail(productDetailParam);
        Product product= (Product) detail.getData();
        return product;
    }
    @PostMapping("cart/list")
    public List<Product> cartList(@RequestBody @Validated ProductCollectParam param, BindingResult result)
    {
        if(result.hasErrors())
        {
            return new ArrayList<Product>();
        }
        return productService.cartList(param.getProductIds());

    }

}
