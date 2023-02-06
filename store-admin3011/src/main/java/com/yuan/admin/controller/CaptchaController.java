package com.yuan.admin.controller;

import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/5 23:21
 * @Description 验证码controller
 */
@Controller
@RequestMapping
public class CaptchaController {

    @RequestMapping("/captcha")
    @ResponseBody
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //默认四个字符长度的验证码
        //默认放入session中  key = captcha 
        CaptchaUtil.out(request, response);
    }

}
