package com.yuan.admin.controller;

import com.yuan.admin.service.ProductService;
import com.yuan.param.ProductSearchParam;
import com.yuan.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/6 21:44
 * @Description null
 */
@RestController
@RequestMapping("product")
public class ProductController {


    @Autowired
    private ProductService productService;

    @GetMapping("list")
    public R list(ProductSearchParam productSearchParam){

        return productService.search(productSearchParam);
    }



}
