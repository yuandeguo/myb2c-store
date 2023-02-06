package com.yuan.order.controller;

import com.yuan.order.service.OrderService;
import com.yuan.param.OrderParam;
import com.yuan.utils.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/5 15:32
 * @Description null
 */
@RequestMapping("/order")
@RestController
public class OrderController {


    @Resource
    private OrderService orderService;

    /**
     * 订单数据保存
     * @param orderParam
     * @return
     */
    @PostMapping("save")
    public R save(@RequestBody OrderParam orderParam){


        return orderService.save(orderParam);
    }

    /**
     * 订单集合查询,注意,按照类别查询!
     * @param orderParam
     * @return
     */
    @PostMapping("/list")
    public R list(@RequestBody Map<String,Integer> orderParam){
if(orderParam.get("user_id")==null)
{
    return R.fail("参数异常");
}



        return orderService.orderList(orderParam.get("user_id"));
    }


}
