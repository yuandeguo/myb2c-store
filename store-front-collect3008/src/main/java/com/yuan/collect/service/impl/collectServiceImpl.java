package com.yuan.collect.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuan.clients.ProductClient;
import com.yuan.collect.mapper.CollectMapper;
import com.yuan.collect.service.CollectService;
import com.yuan.param.ProductCollectParam;
import com.yuan.pojo.Collect;
import com.yuan.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/4 22:02
 * @Description null
 */
@Service
@Slf4j
public class collectServiceImpl implements CollectService {
    @Resource
    private CollectMapper collectMapper;
@Resource
private ProductClient productClient;
    @Override
    public R save(Collect collect) {
        //分解参数
        Integer userId = collect.getUserId();
        Integer productId = collect.getProductId();
        //数据库查询
        QueryWrapper<Collect> queryWrapper
                = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("product_id",productId);
        Long count = collectMapper.selectCount(queryWrapper);

        if ( count> 0){
            log.info("CollectServiceImpl.save业务结束，结果:{}",count);
            return R.fail("商品已在收藏夹! 无需二次添加!");
        }
        //实体类封装

        collect.setCollectTime(System.currentTimeMillis());
        //数据库插入
        int rows = collectMapper.insert(collect);
        log.info("***collectServiceImpl.save业务结束，结果:{}", rows);
        //结果封装
        return R.ok("商品添加成功!");
    }

    /**
     * 根据用户id查看收藏商品信息集合
     *
     * @param user_id
     * @return
     */
    @Override
    public R ids(Integer user_id) {

QueryWrapper<Collect> queryWrapper=new QueryWrapper<>();
queryWrapper.eq("user_id",user_id);
queryWrapper.select("product_id");
        List<Object> collects = collectMapper.selectObjs(queryWrapper);
        List<Integer> ids=new ArrayList<>();

        collects.forEach(s->ids.add((Integer) s));


        ProductCollectParam productCollectParam=new ProductCollectParam();
        productCollectParam.setProductIds(ids);

        R r = productClient.productIds(productCollectParam);
        log.info("***collectServiceImpl.ids业务结束，结果:{}",r );
return r;
    }

    /**
     * 根据用户传递的用户id和商品id删除收藏
     *
     * @param user_id
     * @param product_id
     * @return
     */
    @Override
    public R remove(Integer user_id, Integer product_id) {

        QueryWrapper<Collect> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",user_id);
        queryWrapper.eq("product_id",product_id);
        int delete = collectMapper.delete(queryWrapper);
        log.info("***collectServiceImpl.remove业务结束，结果:{}",delete );
return R.ok("删除成功");
    }
}
