package com.yuan.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/2 22:10
 * @Description 地址集合查询参数接收
 */
@Data
public class AddressListParam {

        @JsonProperty("user_id")  //对应的json的key
        private Integer userId;


}
