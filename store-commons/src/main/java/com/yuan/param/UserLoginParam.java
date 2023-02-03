package com.yuan.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/2 21:13
 * @Description 登录信息的参数初步校验
 */
@Data
public class UserLoginParam {
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
}
