package com.yuan.admin.controller;

import com.yuan.admin.service.UserService;
import com.yuan.param.PageParam;
import com.yuan.pojo.User;
import com.yuan.utils.R;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/6 0:07
 * @Description 用户模块调用
 */
@Controller
@RequestMapping("/user")
public class UserController {


    @Resource
    private UserService userService;

    @GetMapping("list")
    @ResponseBody
    public R userList(PageParam pageParam){
        return userService.listPage(pageParam);
    }

    @PostMapping("/remove")
    @ResponseBody
    public R remove( Integer userId){

        return userService.remove0(userId);
    }
    @PostMapping("update")
    @ResponseBody
    public R update(User user){

        return userService.update(user);
    }

    @PostMapping("save")
    @ResponseBody
    public R save(User user){

        return userService.save(user);
    }

}
