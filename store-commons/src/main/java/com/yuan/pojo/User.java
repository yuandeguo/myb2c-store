package com.yuan.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/2 17:00
 * @Description 用户的实体类，，，Serializable可序列化的
Serializable可序列化：
 * 提供一种简单又可扩展的对象保存恢复机制。
 * 对于远程调用，能方便对对象进行编码和解码，就像实现对象直接传输。
 * 可以将对象持久化到介质中，就像实现对象直接存储。
 * 允许对象自定义外部存储的格式。
 *
 * 而Java 中的包装类几乎都实现了这个接口
 */
@Data
@TableName("user")   //指明对应的表
public class User implements Serializable {
    public static final Long serialVersionUID = 1L;


    @TableId(type = IdType.AUTO)//主键
    @JsonProperty("user_id")//jackson的注解，用于进行属性格式化，json数据就是user_id
    private Integer userId;

    @Length(min =6)
    private String  userName;

    @NotBlank
    //如果null，就忽略，不生成json，不影响接收。    @JsonIgnore对接收也影响，一般不用
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String  password;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotBlank
    private String  userPhonenumber;
}
