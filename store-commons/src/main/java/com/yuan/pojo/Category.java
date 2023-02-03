package com.yuan.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/3 15:54
 * @Description 类别实体
 */
@Data
@TableName("category")
public class Category {
@TableId(type = IdType.AUTO)
  private  Integer  categoryId;
private String categoryName;
}
