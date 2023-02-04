package com.yuan.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/3 21:39
 * @Description 图片实体
 */
@Data
@TableName("product_picture")
public class Picture implements Serializable {

    public static final Long serialVersionUID = 1L;

    @TableId(type= IdType.AUTO)
    private Integer id;

    @JsonProperty("product_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer productId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("product_picture")
    private String  productPicture;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String  intro;
}

