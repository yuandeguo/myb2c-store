package com.yuan.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/4 21:46
 * @Description 收藏实体类
 */
@Data
@TableName("collect")
public class Collect implements Serializable {
     public static final Long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;


    @JsonProperty("user_id")
    private Integer userId;

    @JsonProperty("product_id")
    private Integer productId;

    @JsonProperty("collect_time")
    private Long collectTime;


}
