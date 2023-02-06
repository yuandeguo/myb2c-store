package com.yuan.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yuan.pojo.Address;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/5 18:24
 * @Description 地址参数接收
 */
@Data
public class AddressParam {

    @NotNull
    @JsonProperty("user_id")
    private Integer userId;
    private Address add;

}
