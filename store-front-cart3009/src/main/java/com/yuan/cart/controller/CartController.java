package com.yuan.cart.controller;

import com.yuan.cart.service.CartService;
import com.yuan.param.CartAddParam;
import com.yuan.param.CartListParam;
import com.yuan.pojo.Cart;
import com.yuan.utils.R;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/5 0:19
 * @Description null
 */
@RestController
@RequestMapping("cart")
public class CartController {

@Resource
private CartService cartService;
    @PostMapping("save")
    public R save(@RequestBody @Validated  CartAddParam cartAddParam, BindingResult result){
if (result.hasErrors())
{
    return R.fail("参数异常，错误");
}
        return cartService.save(cartAddParam);
    }
    @PostMapping("list")
    public R list(@RequestBody @Validated CartListParam cartListParam, BindingResult result){
        if (result.hasErrors())
        {
            return R.fail("参数异常，错误");
        }
        return cartService.list(cartListParam.getUserId());
    }

    @PostMapping("update")
    public R update(@RequestBody Cart cart){

        return cartService.update(cart);
    }

    @PostMapping("remove")
    public R remove(@RequestBody Cart cart){

        return cartService.remove(cart);
    }

}
