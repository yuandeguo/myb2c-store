package com.yuan.user.service;

import com.yuan.param.UserCheckParam;
import com.yuan.param.UserLoginParam;
import com.yuan.pojo.User;
import com.yuan.utils.R;

public interface UserService {
/**
 * 检查账号是否可用的接口
 * @param userCheckParam 接收检查的账号实体 内部有参数校验注解
 * @return 检查结果001   004
 **/
    R check(UserCheckParam userCheckParam);

    /**
     * 注册业务
     *   2. 检查账号是否存在
     *   1. 密码加密处理
     *   3. 插入数据库数据
     *   4. 返回结果封装
     * @param user 参数已经校验,但是密码是明文!
     * @return 结果 001 004
     */
    R register(User user);

    /**
     * 用户登录
     * @param userLoginParam
     * @return
     */
    R login(UserLoginParam userLoginParam);
}
