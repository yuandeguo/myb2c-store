package com.yuan.to;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/5 16:36
 * @Description 订单发送商品服务的实体
 */
@Data
public class OrderToProduct implements Serializable {
     public static final Long serialVersionUID = 1L;
    //商品id
    private Integer productId;
    //购买数量
    private Integer num;

}
