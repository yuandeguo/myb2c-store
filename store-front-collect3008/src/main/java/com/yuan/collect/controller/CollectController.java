package com.yuan.collect.controller;

import com.yuan.collect.service.CollectService;
import com.yuan.pojo.Collect;
import com.yuan.pojo.Product;
import com.yuan.utils.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/4 22:00
 * @Description null
 */
@RestController
@RequestMapping("collect")
public class CollectController {
@Resource
private CollectService collectService;

    @PostMapping("save")
    public R save(@RequestBody Collect collect){

        return collectService.save(collect);
    }
    /**
     * 供收藏服务使用,根据传入的id,查询商品集合!
     * @return
     */
    @PostMapping("list")
    public R list(@RequestBody Map<String,Integer> param){

        return collectService.ids(param.get("user_id"));
    }

    /**
     * 根据用户传递的用户id和商品id删除收藏
     * @return
     */
    @PostMapping("remove")
    public R remove(@RequestBody Map<String,Integer> param){

        return collectService.remove(param.get("user_id"),param.get("product_id"));
    }


}
