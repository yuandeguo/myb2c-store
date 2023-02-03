package com.yuan.carousel.controller;
import com.yuan.carousel.service.CarouselService;
import com.yuan.utils.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/3 0:02
 * @Description null
 */
@RestController
@RequestMapping("/carousel/")
public class CarouselController {

    @Resource
private CarouselService carouselService;
    /**
     * 查询首页数据,查询优先级最高的四条
     * @return
     */
    @PostMapping("list")
    public R list(){

        return carouselService.list();
    }

}
