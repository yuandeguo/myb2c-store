package com.yuan.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuan.clients.CategoryClient;
import com.yuan.clients.SearchClient;
import com.yuan.param.ProductDetailParam;
import com.yuan.param.ProductHotParam;
import com.yuan.param.ProductIdsParams;
import com.yuan.param.ProductSearchParam;
import com.yuan.pojo.Picture;
import com.yuan.pojo.Product;
import com.yuan.product.mapper.PictureMapper;
import com.yuan.product.mapper.ProductMapper;
import com.yuan.product.service.ProductService;
import com.yuan.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/3 16:27
 * @Description 缓存说明：value是一个分片，缓存分区，key是这个缓存空间的唯一表示。
 *              存的值是函数返回值
 */

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {


        //引入feign客户端需要,在启动类添加配置注解
        @Resource
        private CategoryClient categoryClient;
        @Resource
        private SearchClient searchClient;
        @Resource
        private ProductMapper productMapper;


    /**
     * 单类别名称 查询热门商品 至多7条数据
     *    1. 根据类别名称 调用 feign客户端访问类别服务获取类别的数据
     *    2. 成功 继续根据类别id查询商品数据  [热门 销售量倒序 查询7]
     *    3. 结果封装即可
     *    cacheManagerHour是在配置类上的
     *
     *
         * @param categoryName 类别名称
         * @return r
    */
    @Override
    @Cacheable(value = "list.product",key = "#categoryName",cacheManager = "cacheManagerHour")
    public R promo(String categoryName) {

            R r = categoryClient.byName(categoryName);

            if (r.getCode().equals(R.FAIL_CODE)) {
                log.info("ProductServiceImpl.promo业务结束，结果:{}","类别查询失败!");
                return r;
            }

            //data = category ---feign{json}

            LinkedHashMap<String,Object> map= (LinkedHashMap<String, Object>) r.getData();

            Integer categoryId= (Integer) map.get("category_id");
            //封装查询参数
            QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("category_id",categoryId);
            queryWrapper.orderByDesc("product_sales");
            IPage<Product> page = new Page<>(1,7);

            //返回的是包装数据! 内部有对应的商品集合,也有分页的参数 例如: 总条数 总页数等等
            page = productMapper.selectPage(page, queryWrapper);
            List<Product> productList = page.getRecords(); //指定页的数据
            long total = page.getTotal(); //获取总条数

            log.info("ProductServiceImpl.promo业务结束，结果:{}",productList);

            return R.ok("数据查询成功",productList);

        }

    /**
     * 多类别热门商品查询 根据类别名称集合! 至多查询7条!
     * 1. 调用类别服务
     * 2. 类别集合id查询商品
     * 3. 结果集封装即可
     *
     * @param productHotParam 类别名称集合
     * @return r
     */
    @Override
    @Cacheable(value = "list.product",key = "#productHotParam.categoryName")
    public R hots(ProductHotParam productHotParam) {
        R r = categoryClient.hotsCategory(productHotParam);

        if(r.getCode().equals(R.FAIL_CODE)){
            log.info("ProductServiceImpl.hots业务结束，结果:{}",r.getMsg());
            return r;
        }

        List<Object> ids = (List<Object>) r.getData();

        //进行商品数据查询
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("category_id",ids);
        queryWrapper.orderByDesc("product_sales");

        IPage<Product> page = new Page<>(1,7);

        page = productMapper.selectPage(page,queryWrapper);

        List<Product> records = page.getRecords();

        R ok = R.ok("多类别热门商品查询成功!", records);

        log.info("ProductServiceImpl.hots业务结束，结果:{}",ok);

        return ok;
    }

    /**
     * 查询类别商品集合
     *
     * @return
     */
    @Override
    public R clist() {
        R r = categoryClient.list();
        log.info("ProductServiceImpl.clist业务结束，结果:{}",r);
            return r;
    }

    /**
     * 根据类别集合查询商品信息
     * 空集合为全部商品
     * @param productIdsParams
     * @return
     */
    @Override
    @Cacheable(value = "list.product",key="#productIdsParams.categoryID+'-'+#productIdsParams.currentPage+'-'+#productIdsParams.pageSize")
    public R byCategory(ProductIdsParams productIdsParams) {
        List<Integer> categoryId=productIdsParams.getCategoryID();
      QueryWrapper<Product> queryWrapper=new QueryWrapper<>();
        if (!categoryId.isEmpty())
        {
            queryWrapper.in("category_id",categoryId);
        }
        IPage<Product> page=new Page<>(productIdsParams.getCurrentPage(),productIdsParams.getPageSize());
       page= productMapper.selectPage(page, queryWrapper);
        R ok = R.ok("查询成功", page.getRecords(), page.getTotal());

        log.info("ProductServiceImpl.byCategory业务结束，结果:{}",ok);
        return ok;

    }



    /**
     * 根据id查询商品的详细信息
     *
     * @param productDetailParam
     * @return 返回商品详细信息
     */
    @Override
    @Cacheable(value = "product",key = "#productDetailParam.productID")
    public R detail(ProductDetailParam productDetailParam) {

       Product product=productMapper.selectById(productDetailParam.getProductID());

        R ok = R.ok(product);

        log.info("ProductServiceImpl.detail业务结束，结果:{}",ok);
        return ok;

    }


    @Resource
  private   PictureMapper pictureMapper;
    /**
     * 根据id查询商品的图片信息
     *
     * @param productID
     * @return 返回商品图片信息
     */
    @Override
    @Cacheable(value = "picture",key = "#productID")
    public R pictures(Integer productID) {

        //参数封装
        QueryWrapper<Picture> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id",productID);
        queryWrapper.select("id","product_picture");
        //数据库查询
        List<Picture> pictureList = pictureMapper.selectList(queryWrapper);
        //结果封装
        R r = R.ok(pictureList);

        log.info("ProductServiceImpl.pictures业务结束，结果:{}",r);

        return r;




    }

    /**
     * 查询全部商品信息,供search服务
     * 进行数据的同步
     *
     * @return
     */
    @Override
    @Cacheable(value = "list.category",key = "#root.methodName")
    public List<Product> list() {
        List<Product> products = productMapper.selectList(null);

        log.info("ProductServiceImpl。list  业务" +products);
        return  products;

    }

    /**
     * 根据search参数查询商品
     *
     * @param productSearchParam
     * @return
     */
    @Override
    public R search(ProductSearchParam productSearchParam) {

R r=searchClient.search(productSearchParam);

        return r;
    }

    /**
     * 根据商品id的集合查询商品
     *
     * @param productIds
     * @return
     */
    @Cacheable(value = "list.product",key = "#productIds")
    @Override
    public R ids(List<Integer> productIds) {

        QueryWrapper<Product> queryWrapper=new QueryWrapper<>();
        queryWrapper.in("product_id",productIds);
        List<Product> products = productMapper.selectList(queryWrapper);

        R r = R.ok("根据id集合信息查询成功", products);
        return r;

    }

    /**
     * 根据商品id的集合查询商品
     *
     * @param productIds
     * @return
     */
    @Override
    public List<Product> cartList(List<Integer> productIds) {
        QueryWrapper<Product> queryWrapper=new QueryWrapper<>();
        queryWrapper.in("product_id",productIds);
        List<Product> products = productMapper.selectList(queryWrapper);

        log.info("***ProductServiceImpl.cartList业务结束，结果:{}", products);
        return products;
    }


}



