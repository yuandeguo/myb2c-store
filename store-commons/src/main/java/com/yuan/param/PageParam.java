package com.yuan.param;

import lombok.Data;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/4 15:41
 * @Description 分页属性
 */
@Data
public class PageParam {
    private int    currentPage = 1;
    private int    pageSize = 15;
}
