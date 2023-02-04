package com.yuan.search.service;

import com.yuan.param.ProductSearchParam;
import com.yuan.utils.R;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/4 15:45
 * @Description null
 */
public interface SearchService {
    /**
     * 1. 根据关键字和分页参数，进行es索引查询
     * 2. 将结果封装到R中，返回商品服务即可
     * @param productSearchParam
     * @return
     */
    R search(ProductSearchParam productSearchParam);
}
