package com.yuan.clients;

import com.yuan.param.PageParam;
import com.yuan.param.ProductHotParam;
import com.yuan.pojo.Category;
import com.yuan.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/3 16:13
 * @Description 类别的调用接口
 */
@FeignClient("category-service3005")
public interface CategoryClient {

    @GetMapping("/category/promo/{categoryName}")
    R byName(@PathVariable String categoryName);

    @PostMapping("/category/hots")
    R hotsCategory(@RequestBody @Validated ProductHotParam productHotParam);

    @GetMapping("/category/list")
    R list();

    @PostMapping("/category/admin/list")
    R list(@RequestBody PageParam pageParam);

    @PostMapping("/category/admin/save")
    R save(@RequestBody Category category);

}