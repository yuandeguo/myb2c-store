package com.yuan.product.controller;

import com.yuan.param.ProductSearchParam;
import com.yuan.pojo.Product;
import com.yuan.product.service.ProductService;
import com.yuan.utils.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/3 23:51
 * @Description null
 */
@RestController
@RequestMapping("product")
public class ProductSearchController {

    @Resource
    private ProductService productService;

    /**
     * 查询全部商品信息,供search服务更新
     * @return
     */
    @GetMapping("list")
    public List<Product> list(){

        return productService.list();
    }


    /**
     * 根据search参数查询商品
     * @param ProductSearchParam
     * @return
     */
    @PostMapping("search")
    public R search(@RequestBody ProductSearchParam ProductSearchParam){

        return productService.search(ProductSearchParam);
    }
}
