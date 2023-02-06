package com.yuan.user.controller;

import com.sun.jndi.cosnaming.CNCtx;
import com.yuan.param.AddressListParam;
import com.yuan.param.AddressParam;
import com.yuan.param.AddressRemoveParam;
import com.yuan.pojo.Address;
import com.yuan.user.service.AddressService;
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
 * @date 2023/2/2 22:13
 * @Description 地址查询的controller
 */
@RestController
@RequestMapping("/user/address/")
public class AddressController {

    @Resource
    private AddressService addressService;


    /**
     * 根据用户id查询 地址数据!
     * @param addressListParam
     * @param result
     * @return
     */
    @PostMapping("list")
    public R list(@RequestBody @Validated AddressListParam addressListParam, BindingResult result){

        if (result.hasErrors()){

            return R.fail("参数异常,查询失败!");
        }


        return  addressService.list(addressListParam.getUserId());
    }


    /**
     *保存地址，最后返回该用户的所有地址
     * @param addressParam
     * @param result
     * @return
     */
    @PostMapping("save")
    public R save(@RequestBody @Validated AddressParam addressParam, BindingResult result)
    {
        if (result.hasErrors())
        {
            return R.fail("参数异常，地址保存失败!");
        }

Address address=addressParam.getAdd();
        address.setUserId(addressParam.getUserId());

    return addressService.save(address);
    }


    /**
     * 根据id删除地址
     * @param addressRemoveParam
     * @param result
     * @return
     */
    @PostMapping("remove")
    public R remove(@RequestBody @Validated AddressRemoveParam addressRemoveParam, BindingResult result){

        if (result.hasErrors()){

            return R.fail("参数异常,删除失败!");
        }

        return addressService.remove(addressRemoveParam.getId());
    }




}
