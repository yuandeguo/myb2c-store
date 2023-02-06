package com.yuan.category.controller;

import com.yuan.category.service.CategoryService;
import com.yuan.param.PageParam;
import com.yuan.pojo.Category;
import com.yuan.utils.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/6 16:44
 * @Description null
 */
@RestController
@RequestMapping("category")
public class CategoryAdminController {
    @Resource
private CategoryService categoryService;
    @PostMapping("/admin/list")
    public R list(@RequestBody PageParam pageParam){

        return  categoryService.listPage(pageParam);
    }

    @PostMapping("/admin/save")
    public R save(@RequestBody Category category){

        return  categoryService.save(category);
    }



}
