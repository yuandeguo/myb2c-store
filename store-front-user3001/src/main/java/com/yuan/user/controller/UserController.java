package com.yuan.user.controller;

import com.yuan.param.UserCheckParam;
import com.yuan.param.UserLoginParam;
import com.yuan.pojo.User;
import com.yuan.user.service.UserService;
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
 * @date 2023/2/2 17:10
 * @Description null
 */
@RestController   //返回json数据
@RequestMapping("/user")
public class UserController {


    @Resource
    private UserService userService;
    /**
     * 检查账号是否可用的接口
     * @param userCheckParam 接收检查的账号实体 内部有参数校验注解
     * @param result 获取校验结果的实体对象
     * @return 返回封装结果R对象即可
     *
     * BindingResult 获取userCheckParam的校验结果，  必须紧挨着userCheckParam
     * //检查是否符合检验注解的规则  符合 false  不符合 true
     *     boolean b = result.hasErrors();
     *
     */
@PostMapping("check")
    public R check(@RequestBody @Validated UserCheckParam userCheckParam, BindingResult result)
{
    boolean errors = result.hasErrors();
    if (errors){
        return R.fail("账号为null,不可使用!");
    }

    return userService.check(userCheckParam);

}

    /**
     * 用户注册
     * Validated是使User实体属性上的注解生效，可以校验
     * @param user
     * @param result
     * @return
     */
@PostMapping("register")
    public R register(@RequestBody @Validated User user, BindingResult result)
{
if(result.hasErrors())
{//存在异常，证明请求参数不符合注解要求

    return R.fail("参数异常,不可注册!");
}
    return userService.register(user);


}

    /**
     * 用户登录
     * @param userLoginParam
     * @param result
     * @return
     */
    @PostMapping("login")
    public R login(@RequestBody @Validated UserLoginParam userLoginParam, BindingResult result){

        if (result.hasErrors()){
            //如果存在异常,证明请求参数不符合注解要求
            return  R.fail("参数异常,不可登录!");
        }

        return userService.login(userLoginParam);
    }




}
