package com.yuan.cart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuan.param.CartAddParam;
import com.yuan.pojo.Cart;
import com.yuan.utils.R;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/5 0:24
 * @Description null
 */
public interface CartService extends IService<Cart> {
    /**
     * 添加购物车
     * @param cartAddParam
     * @return
     */
    R save(CartAddParam cartAddParam);

    R list(Integer userId);

    R update(Cart cart);

    R remove(Cart cart);
}
