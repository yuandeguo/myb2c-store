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
 * @date 2023/2/3 0:00
 * @Description 轮播图实体
 */
@Data
@TableName("carousel")
public class Carousel implements Serializable {
    public static final Long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @JsonProperty("carousel_id")  //json格式化的别名
    private Integer carouselId;

    private String  imgPath;
    private String  describes;

    @JsonProperty("product_id")
    private Integer productId;
    //优先级
    private Integer priority;
}
