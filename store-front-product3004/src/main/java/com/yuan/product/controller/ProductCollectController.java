package com.yuan.product.controller;

import com.yuan.param.ProductCollectParam;
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
 * @date 2023/2/4 22:32
 * @Description null
 */
@RestController
@RequestMapping("product")
public class ProductCollectController {
@Resource
private ProductService productService;

    @PostMapping("collect/list")
    public R productIds(@RequestBody @Validated ProductCollectParam productCollectParam, BindingResult bindingResult)
    {
if (bindingResult.hasErrors())
{
    return R.ok("没有收藏数据");
}
return productService.ids(productCollectParam.getProductIds());
    }


}
