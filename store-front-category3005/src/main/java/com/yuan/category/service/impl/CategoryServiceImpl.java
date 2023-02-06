package com.yuan.category.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuan.category.mapper.CategoryMapper;
import com.yuan.category.service.CategoryService;
import com.yuan.param.PageParam;
import com.yuan.param.ProductHotParam;
import com.yuan.pojo.Category;
import com.yuan.utils.R;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
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

    /**
     * 查询类别数据进行返回
     *
     * @return
     */
    @Override
    public R list() {
        List<Category> categories = categoryMapper.selectList(null);
 return R.ok("查询所有类别信息成功",categories);
    }

    @Override
    public R listPage(PageParam pageParam) {
        //分页参数
        IPage<Category> page = new Page<>(pageParam.getCurrentPage()
                ,pageParam.getPageSize());
        //查询参数获取
        page = categoryMapper.selectPage(page, null);

        List<Category> records = page.getRecords();
        long total = page.getTotal();

        R r = R.ok("查询类别数据成功!", records, total);

        log.info("CategoryServiceImpl.page业务结束，结果:{}",r);

        return r;



    }

    @Override
    public R save(Category category) {
QueryWrapper<Category> queryWrapper=new QueryWrapper<>();
queryWrapper.eq("category_name",category.getCategoryName());
        Long aLong = categoryMapper.selectCount(queryWrapper);

        if(aLong>0)
        {
            return R.fail("类别存在，添加失败");
        }
int insert=categoryMapper.insert(category);
        log.info("***CategoryServiceImpl.save业务结束，结果:{}",insert );
        return R.ok("添加成功");
    }
}
