package com.yuan.admin.service;

import com.yuan.param.PageParam;
import com.yuan.pojo.Category;
import com.yuan.utils.R;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/6 16:59
 * @Description null
 */
public interface CategoryService {
     R pageList(PageParam pageParam) ;

     R saveCategory(Category category);
}
