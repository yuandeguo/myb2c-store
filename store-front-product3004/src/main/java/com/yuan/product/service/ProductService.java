package com.yuan.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuan.param.ProductDetailParam;
import com.yuan.param.ProductHotParam;
import com.yuan.param.ProductIdsParams;
import com.yuan.param.ProductSearchParam;
import com.yuan.pojo.Product;
import com.yuan.to.OrderToProduct;
import com.yuan.utils.R;

import java.util.List;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/3 16:26
 * @Description null
 */
public interface ProductService extends IService<Product> {

    /**
     * 根据类别名称查找商品
     * @param categoryName
     * @return
     */
    R promo(String categoryName);
    /**
     * 多类别热门商品查询 根据类别名称集合! 至多查询7条!
     *   1. 调用类别服务
     *   2. 类别集合id查询商品
     *   3. 结果集封装即可
     * @param productHotParam 类别名称集合
     * @return r
     */
    R hots(ProductHotParam productHotParam);
    /**
     * 查询类别商品集合
     *
     * @return
     */
    R clist();
    /**
     * 根据类别集合查询商品信息
     * @param productIdsParams
     * @return
     */
    R  byCategory(ProductIdsParams productIdsParams);

    /**
     * 根据id查询商品的详细信息
     * @param productDetailParam
     * @return  返回商品详细信息
     */
    R detail(ProductDetailParam productDetailParam);
    /**
     * 根据id查询商品的图片信息
     * @param productID
     * @return  返回商品图片信息
     */
    R pictures(Integer productID);

    /**
     * 查询全部商品信息,供search服务
     * 进行数据的同步
     * @return
     */
    List<Product> list();

    /**
     * 根据search参数查询商品
     * @param productSearchParam
     * @return
     */
    R search(ProductSearchParam productSearchParam);

    /**
     * 根据商品id的集合查询商品
     * @param productIds
     * @return
     */
    R ids(List<Integer> productIds);
    /**
     * 根据商品id的集合查询商品
     * @param productIds
     * @return
     */
    List<Product> cartList(List<Integer> productIds);

    /**
     * 修改库存，增加销售量
     * @param orderToProductList
     */
    void subNumber(List<OrderToProduct> orderToProductList);
}
