package com.yuan.admin.service;

import com.yuan.param.PageParam;
import com.yuan.pojo.User;
import com.yuan.utils.R;

public interface UserService {
    R listPage(PageParam pageParam);

    R remove0(Integer userId);

    R save(User user);

    R update(User user);
}
