package com.yuan.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/2 22:07
 * @Description 地址实体类
 *
 * NotEmpty 用在集合类上面
 * NotBlank 用在String上面
 * NotNull 用在基本类型上
 *
 */
@Data
public class Address implements Serializable {
    public static final Long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    @NotBlank
    private String linkman;
    @NotBlank
    private String phone;
    @NotBlank
    private String address;

    @NotNull
    @TableField("user_id")//数据库名称对应，这里可以不写
    @JsonProperty("user_id")  //对应的json的key
    private Integer userId;
}
