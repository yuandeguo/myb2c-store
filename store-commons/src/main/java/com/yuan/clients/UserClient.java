package com.yuan.clients;

import com.yuan.param.PageParam;
import com.yuan.pojo.Category;
import com.yuan.pojo.User;
import com.yuan.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/6 0:05
 * @Description null
 */
@FeignClient(value = "user-service3001")
public interface UserClient {

    /**
     * 后台管理,展示用户信息接口
     *
     * @param pageParam
     * @return
     */
    @PostMapping("/user/admin/list")
    R listPage(@RequestBody PageParam pageParam);

    @PostMapping("/user/admin/remove")
    R removeUser(@RequestBody Map<String, Integer> map);

    @PostMapping("/user/admin/save")
    R saveUser(@RequestBody User user);

    @PostMapping("/user/admin/update")
    R updateUser(@RequestBody User user);

}