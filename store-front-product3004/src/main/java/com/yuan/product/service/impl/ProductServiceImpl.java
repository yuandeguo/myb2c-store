package com.yuan.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuan.clients.CategoryClient;
import com.yuan.param.ProductHotParam;
import com.yuan.pojo.Product;
import com.yuan.product.mapper.ProductMapper;
import com.yuan.product.service.ProductService;
import com.yuan.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/3 16:27
 * @Description null
 */

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {


        //引入feign客户端需要,在启动类添加配置注解
        @Resource
        private CategoryClient categoryClient;

        @Resource
        private ProductMapper productMapper;
        /**
         * 单类别名称 查询热门商品 至多7条数据
         *    1. 根据类别名称 调用 feign客户端访问类别服务获取类别的数据
         *    2. 成功 继续根据类别id查询商品数据  [热门 销售量倒序 查询7]
         *    3. 结果封装即可
         * @param categoryName 类别名称
         * @return r
         */
        @Override
        public R promo(String categoryName) {

            R r = categoryClient.byName(categoryName);

            if (r.getCode().equals(R.FAIL_CODE)) {
                log.info("ProductServiceImpl.promo业务结束，结果:{}","类别查询失败!");
                return r;
            }

            //data = category ---feign{json}

            LinkedHashMap<String,Object> map= (LinkedHashMap<String, Object>) r.getData();

            Integer categoryId= (Integer) map.get("categoryId");
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
}



