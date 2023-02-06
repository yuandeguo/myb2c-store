package com.yuan.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuan.admin.constants.UserConstants;
import com.yuan.admin.mapper.AdminUserMapper;
import com.yuan.admin.param.AdminUserParam;
import com.yuan.admin.pojo.AdminUser;
import com.yuan.admin.service.AdminUserService;
import com.yuan.utils.MD5Util;
import com.yuan.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/5 23:28
 * @Description null
 */
@Slf4j
@Service
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper,AdminUser> implements AdminUserService {


    @Override
    public AdminUser login(AdminUserParam adminUserParam) {
        //密码加密处理
        //代码加密处理,注意加盐,生成常量
        String newPwd = MD5Util.encode(adminUserParam.getUserPassword() +
                UserConstants.USER_SLAT);

        //数据库登录查询
        QueryWrapper<AdminUser> adminUserQueryWrapper =
                new QueryWrapper<>();

        adminUserQueryWrapper.eq("user_account",adminUserParam.getUserAccount());
        adminUserQueryWrapper.eq("user_password",newPwd);

        AdminUser adminUser = baseMapper.selectOne(adminUserQueryWrapper);
        //结果封装


        return adminUser;
    }
}
