package com.yuan.doc;

import com.yuan.pojo.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/4 0:31
 * @Description 原来存储商品搜索数据的实体类
 */
@Data
@NoArgsConstructor
public class ProductDoc  extends Product {
    /**
     * 用于模糊查询字段,由商品名,标题和描述组成
     */
    private String all;
    public ProductDoc(Product product) {
        super(product.getProductId(),product.getProductName(),
                product.getCategoryId(),product.getProductTitle(),
                product.getProductIntro(),product.getProductPicture(),
                product.getProductPrice(),product.getProductSellingPrice(),
                product.getProductNum(),product.getProductSales());
        this.all = product.getProductName()+product.getProductTitle()+product.getProductIntro();
    }
}
