package com.yuan.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/5 0:55
 * @Description 购物车查询接参数
 */
@Data
public class CartListParam {
@JsonProperty("user_id")
    @NotNull
    private Integer userId;
}
