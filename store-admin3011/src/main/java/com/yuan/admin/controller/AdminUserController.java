package com.yuan.admin.controller;

import com.yuan.admin.param.AdminUserParam;
import com.yuan.admin.pojo.AdminUser;
import com.yuan.admin.service.AdminUserService;
import com.yuan.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/5 23:27
 * @Description null
 */
@Slf4j
@Controller
@RequestMapping("/user")
public class AdminUserController {


    @Resource
    private AdminUserService adminUserService;

    /**
     * 后台登录功能实现
     * @param adminUserParam
     * @return
     */
    @PostMapping("login")
    @ResponseBody
    public Object login(@Validated AdminUserParam adminUserParam, BindingResult bindingResult, HttpSession session){

        //参数校验
        if (bindingResult.hasErrors()) {
            log.info("AdminUserController.login业务,参数异常!");
            return R.fail("登录失败,核心参数为null");
        }
        //校验验证码
        String varCode = adminUserParam.getVerCode();
        String captcha = (String) session.getAttribute("captcha");
        if (!varCode.equalsIgnoreCase(captcha)){

            return R.fail("登录失败,验证码错误!");
        }


        //获取数据存储到session共享域,后期登录访问判断
        AdminUser adminUser = adminUserService.login(adminUserParam);
        if (adminUser==null)
            return R.fail("登录失败,账号或密码!");

        //存储到session共享域  key = userInfo 其他页面固定读取
        session.setAttribute("userInfo",adminUser);
        return R.ok("登录成功",adminUser);
    }
    @GetMapping("logout")
    @ResponseBody
    public Object logout(HttpSession session){
        //清空session
        session.invalidate();

        return R.ok("退出登录成功!");
    }



}
