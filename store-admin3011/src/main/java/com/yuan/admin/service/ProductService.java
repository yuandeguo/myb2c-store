package com.yuan.admin.service;

import com.yuan.param.ProductSearchParam;
import com.yuan.utils.R;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/6 22:08
 * @Description null
 */
public interface ProductService {
    R search(ProductSearchParam productSearchParam);
}
