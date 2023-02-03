package com.yuan.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/3 17:25
 * @Description 热门商品参数接收对象
 */
@Data
public class ProductHotParam {

    @NotEmpty
    private List<String> categoryName;
}

