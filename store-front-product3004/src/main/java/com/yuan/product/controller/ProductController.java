package com.yuan.product.controller;

import com.yuan.param.ProductDetailParam;
import com.yuan.param.ProductHotParam;
import com.yuan.param.ProductIdsParams;
import com.yuan.param.ProductPromoParam;
import com.yuan.pojo.Product;
import com.yuan.product.service.ProductService;
import com.yuan.utils.R;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
    /**
     * 查询类别商品集合
     *
     * @return
     */
    @PostMapping("category/list")
    public R clist(){

        return productService.clist();
    }
    /**
     * 根据类别集合查询
     * @param productIdsParams
     * @return
     */
    @PostMapping("bycategory")
    public R byCategory(@RequestBody @Validated ProductIdsParams productIdsParams,BindingResult result){

        if (result.hasErrors())
        {return R.fail("类别集合商品查询失败，参数异常");
        }
        return productService.byCategory(productIdsParams);
    }

    /**
     * 查询所有类别的所有商品
     * @param productIdsParams
     * @return
     */
    @PostMapping("all")
    public R all(@RequestBody @Validated ProductIdsParams productIdsParams,BindingResult result){

        if (result.hasErrors())
        {return R.fail("查询所有类别的所有商品，参数异常");
        }
        return productService.byCategory(productIdsParams);
    }

/**
 * 根据id查询商品的详细信息
 */
@PostMapping("detail")
public R detail(@RequestBody @Validated ProductDetailParam productDetailParam, BindingResult result){

    if (result.hasErrors())
    {
        R.fail("商品详细查询失败,参数异常");
    }
    return productService.detail(productDetailParam);

}
    /**
     * 根据id查询商品的图片信息
     *
     * @RequestBody Map<String,Integer> param可以自动解析
     *
     * Integer productID普通传参 key value那种
     *
     * json用RequestBody
     *
     * @RequestBody String productID是
     *{
     * "productID":1
     * }
     * 需要解析
     *
     * 他会自动封装对象。当然map可以自动解析
     *
     */
    @PostMapping("pictures")
    public R pictures( @RequestBody Map<String,Integer> param){
    Integer productID   = param.get("productID");
        if(productID==null)
            return R.fail("商品图片查询失败，参数异常");

        return productService.pictures(productID);
    }

}
