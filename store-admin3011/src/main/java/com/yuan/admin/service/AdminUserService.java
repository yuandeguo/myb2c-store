package com.yuan.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuan.admin.param.AdminUserParam;
import com.yuan.admin.pojo.AdminUser;
import com.yuan.utils.R;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/5 23:28
 * @Description null
 */
public interface AdminUserService extends IService<AdminUser> {


    AdminUser login(AdminUserParam adminUserParam);
}
