package com.yuan.admin.service.impl;

import com.yuan.admin.service.CategoryService;
import com.yuan.clients.CategoryClient;
import com.yuan.param.PageParam;
import com.yuan.pojo.Category;
import com.yuan.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/6 17:00
 * @Description null
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

   @Resource
    CategoryClient categoryClient;



   @Cacheable(value = "list.category",key="#pageParam.currentPage+'-'+#pageParam.pageSize")
    @Override
    public R pageList(PageParam pageParam) {

       R list = categoryClient.list(pageParam);
       log.info("***CategoryServiceImpl.pageList业务结束，结果:{}", list);
       return list;


   }
    @CacheEvict(value = "list.category",allEntries = true)
    @Override
    public R saveCategory(Category category) {
        R r = categoryClient.save(category);

        log.info("CategoryServiceImpl.save业务结束，结果:{}",r);

        return r;
    }
}
