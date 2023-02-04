package com.yuan.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/3 21:24
 * @Description 详细商品信息参数
 */
@Data
public class ProductDetailParam {
    @NotNull
    private Integer productID;
}
