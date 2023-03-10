package com.yuan.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yuan.pojo.Order;
import lombok.Data;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/5 17:57
 * @Description 查询订单需要返回结果
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class OrderVo extends Order {

    @JsonProperty("product_name")
    private String productName;
    @JsonProperty("product_picture")
    private String productPicture;

}