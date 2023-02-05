package com.yuan.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/5 0:20
 * @Description 购物车添加参数接收
 */
@Data
public class CartAddParam {

    @JsonProperty("user_id")
    @NotNull
    private Integer userId;
    @JsonProperty("product_id")
    @NotNull
    private Integer productId;

}
