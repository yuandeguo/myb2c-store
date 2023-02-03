package com.yuan.category.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuan.category.mapper.CategoryMapper;
import com.yuan.category.service.CategoryService;
import com.yuan.param.ProductHotParam;
import com.yuan.pojo.Category;
import com.yuan.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/3 16:01
 * @Description null
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
@Resource
private CategoryMapper categoryMapper;

    /**
     * 根据类别名称，查询类别对象
     *
     * @param categoryName
     * @return
     */
    @Override
    public R byName(String categoryName) {
        //封装查询参数
        QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
        categoryQueryWrapper.eq("category_name",categoryName);
        //查询数据库
        Category category = categoryMapper.selectOne(categoryQueryWrapper);
        //结果封装
        if (category == null){
            log.info("CategoryServiceImpl.byName业务结束，结果:类别查询失败");
            return R.fail("类别查询失败!");
        }
        log.info("CategoryServiceImpl.byName业务结束，结果:{}","类别查询成功");
        return R.ok("类别查询成功!",category);

    }

    /**
     * 根据传入的热门类别名称集合!返回类别对应的id集合
     *
     * @param productHotParam
     * @return
     */
    @Override
    public R hotsCategory(ProductHotParam productHotParam) {
        //封装查询参数
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();

      //in表示多值
        queryWrapper.in("category_name",productHotParam.getCategoryName());
        queryWrapper.select("category_id");


        //查询数据库
        List<Object> ids = categoryMapper.selectObjs(queryWrapper);

        R ok = R.ok("类别集合查询成功", ids);
        log.info("CategoryServiceImpl.hotsCategory业务结束，结果:{}",ok);
        return ok;


    }
}
