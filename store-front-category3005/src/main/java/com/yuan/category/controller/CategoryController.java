package com.yuan.category.controller;

import com.yuan.category.service.CategoryService;
import com.yuan.param.ProductHotParam;
import com.yuan.utils.R;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/3 15:57
 * @Description 类别处理
 */
@RestController
@RequestMapping("/category/")
public class CategoryController {

    /**
     * 根据类别名称，查询类别实体
     */
    @Resource
private CategoryService categoryService;

    @GetMapping("promo/{categoryName}")
    public R byName(@PathVariable String categoryName){
            if (StringUtils.isEmpty(categoryName))
            {
                return R.fail("类别参数为空，错误");
            }


return categoryService.byName(categoryName);

    }

    /**
     * 热门类别id查询!
     * @param productHotParam
     * @param result
     * @return
     */
    @PostMapping("hots")
    public R hotsCategory(@RequestBody @Validated ProductHotParam productHotParam, BindingResult result){

        if (result.hasErrors()){
            return R.fail("类别集合查询失败!");
        }

        return categoryService.hotsCategory(productHotParam);
    }
    @GetMapping("list")
    public R list(){

        return categoryService.list();
    }


}
