package com.yuan.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/4 22:30
 * @Description 收藏调用商品传递的参数
 */
@Data
public class ProductCollectParam {
    @NotEmpty
    private List<Integer> productIds;
}
