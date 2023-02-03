package com.yuan.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/2 17:06
 * @Description 使用jsr303的注解，进行校验，而不用程序代码
 * @NotBlank 字符串 不能为null 和 空字符串 ""
 * @NotNull  字符串 不能为null
 * @NotEmpty 集合类型 集合长度不能为0
 */
@Data
public class UserCheckParam {
    @NotBlank
    private String userName; //注意: 参数名称要等于前端传递的JSON key的名称!


}
