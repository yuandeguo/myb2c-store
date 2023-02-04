package com.yuan.param;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/3 20:58
 * @Description 根据类别集合查询商品信息。没有类别就是查询所有的
 */
@Data
public class ProductIdsParams extends PageParam{
    @NotNull  //不能为null，可以为空
    private List<Integer> categoryID;
}
