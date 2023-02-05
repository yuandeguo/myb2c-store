package com.yuan.param;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yuan.vo.CartVo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/5 16:33
 * @Description 订单生成param
 */
//订单参数
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class OrderParam implements Serializable {

    public static final Long serialVersionUID = 1L;

    @JsonProperty("user_id")
    private Integer userId;
    private List<CartVo> products;

}

