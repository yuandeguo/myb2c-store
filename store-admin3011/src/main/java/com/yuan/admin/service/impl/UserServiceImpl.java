package com.yuan.admin.service.impl;

import com.yuan.admin.service.UserService;
import com.yuan.clients.UserClient;
import com.yuan.param.PageParam;
import com.yuan.pojo.User;
import com.yuan.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/6 0:08
 * @Description null
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserClient userClient;


    @Cacheable(value = "list.user",key = "#pageParam.currentPage+'-'+#pageParam.pageSize")
    @Override
    public R listPage(PageParam pageParam) {

        R r = userClient.listPage(pageParam);

        log.info("UserServiceImpl.listPage业务结束，结果:{}",r);

        return r;
    }
@CacheEvict(value = "list.user",allEntries = true)
    @Override
    public R remove0(Integer userId) {
        Map<String,Integer> map=new HashMap<>();
        map.put("userId",userId);
     return    userClient.removeUser(map);

    }
    @CacheEvict(value = "list.user",allEntries = true)
    @Override
    public R save(User user) {
        return userClient.saveUser(user);

    }
    @CacheEvict(value = "list.user",allEntries = true)
    @Override
    public R update(User user) {
       return  userClient.updateUser(user);
    }


}
