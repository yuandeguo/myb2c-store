package com.yuan.product.controller;

import com.yuan.param.ProductHotParam;
import com.yuan.param.ProductPromoParam;
import com.yuan.product.service.ProductService;
import com.yuan.utils.R;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/3 15:44
 * @Description null
 */
@RestController
@RequestMapping("product")
public class ProductController {
        @Resource
    private ProductService productService;

    @PostMapping("/promo")
    public R promo(@RequestBody @Validated ProductPromoParam productPromoParam, BindingResult result){

        if (result.hasErrors()){
            return R.fail("数据查询失败!,类别为null");
        }

        return  productService.promo(productPromoParam.getCategoryName());
    }
    @PostMapping("hots")
    public R hots(@RequestBody @Validated ProductHotParam productHotParam, BindingResult result){

        if (result.hasErrors()){
            return R.fail("数据查询失败!");
        }

        return productService.hots(productHotParam);
    }

}
