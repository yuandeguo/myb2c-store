package com.yuan.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/3 15:51
 * @Description 根据商品类别请求商品信息参数
 */
@Data
public class ProductPromoParam {
    @NotBlank
    private String categoryName;
}
