package com.yuan.param;

import lombok.Data;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/4 15:39
 * @Description 搜索关键字和分页参数集合
 */
@Data
public class ProductSearchParam extends PageParam {
    private String search;
}
