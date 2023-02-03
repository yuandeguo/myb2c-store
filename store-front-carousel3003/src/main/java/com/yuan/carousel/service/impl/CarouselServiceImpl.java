package com.yuan.carousel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuan.carousel.mapper.CarouselMapper;
import com.yuan.carousel.service.CarouselService;
import com.yuan.pojo.Carousel;
import com.yuan.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/3 0:07
 * @Description null
 */
@Service
@Slf4j
public class CarouselServiceImpl implements CarouselService {

@Resource
   private CarouselMapper carouselMapper;
    /**
     * 查询优先级最高的四条轮播图数据
     * @return
     */
    @Override
    public R list() {

        //声明数量
        int limit = 4 ; //至多查询四条
        QueryWrapper<Carousel> carouselQueryWrapper = new QueryWrapper<>();
        carouselQueryWrapper.orderByDesc("priority");

        /**
         * 切割方法一
         */
        //        IPage<Carousel> iPage = new Page<>(1,limit);
    //        IPage<Carousel> page = carouselMapper.selectPage(iPage, carouselQueryWrapper);
    //
    //        List<Carousel> carouselList = page.getRecords();
        /**
         * 切割方法二
         */
        List<Carousel> carouselList=carouselMapper.selectList(carouselQueryWrapper);
        carouselList.stream().limit(limit).collect(Collectors.toList());
log.info("*******轮播图："+carouselList.toString());
        return R.ok(carouselList);
    }
}
