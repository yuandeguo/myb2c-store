package com.yuan.category.service;

import com.yuan.param.ProductHotParam;
import com.yuan.utils.R;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/3 16:01
 * @Description null
 */
public interface CategoryService {
    /**
     * 根据类别名称，查询类别对象
     * @param categoryName
     * @return
     */
    R byName(String categoryName);

    /**
     * 根据传入的热门类别名称集合!返回类别对应的id集合
     * @param productHotParam
     * @return
     */
    R hotsCategory(ProductHotParam productHotParam);
}
