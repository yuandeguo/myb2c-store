package com.yuan.user.controller;

import com.yuan.param.PageParam;
import com.yuan.pojo.User;
import com.yuan.user.service.UserService;
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
 * @date 2023/2/5 23:59
 * @Description 后台管理系统调用
 */
@RestController
@RequestMapping("user")
public class UserAdminController {

@Resource
    UserService userService;
    @PostMapping("admin/list")
    public R listPage(@RequestBody PageParam pageParam)
    {
      return   userService.getUserList(pageParam);
    }


    @PostMapping("admin/remove")
    public R removeUser(@RequestBody Map<String,Integer> map)
    {



        return   userService.removeUser(map.get("userId"));
    }

    @PostMapping("admin/update")
    public R updateUser(@RequestBody User user)
    {



        return   userService.update(user);
    }


    @PostMapping("admin/save")
    public R saveUser(@RequestBody User user)
    {



        return   userService.register(user);
    }

}
