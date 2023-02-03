package com.yuan.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/2 22:31
 * @Description null
 */
@Data
public class AddressRemoveParam {
    @NotNull
    private Integer id;
}
