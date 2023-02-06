package com.yuan.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuan.param.OrderParam;
import com.yuan.pojo.Order;
import com.yuan.utils.R;

/**
 * 尝试使用IService的crud接口
 */
public interface OrderService extends IService<Order> {
    R save(OrderParam orderParam);

    R orderList(Integer user_id);
}
