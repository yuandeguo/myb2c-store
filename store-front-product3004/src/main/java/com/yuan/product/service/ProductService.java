package com.yuan.product.service;

import com.yuan.param.ProductHotParam;
import com.yuan.utils.R;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/3 16:26
 * @Description null
 */
public interface ProductService {

    /**
     * 根据类别名称查找商品
     * @param categoryName
     * @return
     */
    R promo(String categoryName);
    /**
     * 多类别热门商品查询 根据类别名称集合! 至多查询7条!
     *   1. 调用类别服务
     *   2. 类别集合id查询商品
     *   3. 结果集封装即可
     * @param productHotParam 类别名称集合
     * @return r
     */
    R hots(ProductHotParam productHotParam);
}
