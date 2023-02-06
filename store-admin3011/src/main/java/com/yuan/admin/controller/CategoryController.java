package com.yuan.admin.controller;

import com.yuan.admin.service.CategoryService;
import com.yuan.param.PageParam;
import com.yuan.pojo.Category;
import com.yuan.utils.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/6 16:56
 * @Description null
 */
@Controller
@RequestMapping("category")
public class CategoryController {

    @Resource
   private CategoryService categoryService;



@GetMapping("list")
@ResponseBody
    public R pagelist(PageParam pageParam)
{

    R r = categoryService.pageList(pageParam);
    return r;
}
    @PostMapping("save")
    @ResponseBody
    public R save(Category category)
    {
        return categoryService.saveCategory(category);
    }


}
