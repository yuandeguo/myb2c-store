package com.yuan.user.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuan.param.PageParam;
import com.yuan.param.UserCheckParam;
import com.yuan.param.UserLoginParam;
import com.yuan.pojo.User;
import com.yuan.user.constants.UserConstants;
import com.yuan.user.mapper.UserMapper;
import com.yuan.user.service.UserService;
import com.yuan.utils.MD5Util;
import com.yuan.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/2 17:21
 * @Description 用户业务实现类
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    /**
     * 检查账号是否可用的接口
     * @param userCheckParam 接收检查的账号实体 内部有参数校验注解
     * @return 检查结果001   004
     **/
    @Override
    public R check(UserCheckParam userCheckParam) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_name",userCheckParam.getUserName());
        Long total = userMapper.selectCount(queryWrapper);
        //查询结果处理
        if (total == 0){
            //数据库中不存在,可用
            log.info("UserServiceImpl.check业务结束，结果:{}","账号可以使用!");
            return R.ok("账号不存在,可以使用!");
        }

        log.info("UserServiceImpl.check业务结束，结果:{}","账号不可使用!");

        return R.fail("账号已经存在,不可注册!");

    }

    /**
     * 注册业务
     * 2. 检查账号是否存在
     * 1. 密码加密处理
     * 3. 插入数据库数据
     * 4. 返回结果封装
     *
     * @param user 参数已经校验,但是密码是明文!
     * @return 结果 001 004
     */
    @Override
    public R register(User user) {
        //1.检查账号是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",user.getUserName());
        //数据库查询
        Long total = userMapper.selectCount(queryWrapper);

        if (total > 0){
            log.info("UserServiceImpl.register业务结束，结果:{}","账号存在,注册失败!");
            return R.fail("账号已经存在,不可注册!");
        }
        //2.密码加密处理,注意要加盐
        /**
         * MD5 一种不可逆转加密方式, 只能加密不能解密!
         *     固定的明文加密以后的密文是固定!
         *     123456  --> 加密 ---> 1111111
         *     注册是加密以后存在密文!
         *     登录实加密以后,用密文进行数据库对比!
         * MD5可以暴力破解:
         *     穷举法
         *     简单的字符串都是不安全!
         *     提示用户密码复杂度!
         *     加盐处理    用户的密码 1 + 字符串[盐] 9999 = 10000
         */

        String newPwd = MD5Util.encode(user.getPassword() + UserConstants.USER_SLAT);
        user.setPassword(newPwd);

        //3.插入数据库数据
        int rows = userMapper.insert(user);
        //4.返回封装结果
        if (rows == 0){
            log.info("UserServiceImpl.register业务结束，结果:{}","数据插入失败!注册失败!");
            return R.fail("注册失败!请稍后再试!");
        }

        log.info("UserServiceImpl.register业务结束，结果:{}","注册成功!");

        return R.ok("注册成功!");
    }

    /**
     * 用户登录
     *   1. 密码的加密和加盐处理
     *   2. 账号和密码进行数据库查询.返回一个完整的数据库user对象
     *   3. 判断返回结果
     * @param userLoginParam 账号和密码 已经校验 但是密码是明文!
     * @return 结果 001 004
     */
    @Override
    public R login(UserLoginParam userLoginParam) {
        //1.密码处理
        String newPwd = MD5Util.encode(userLoginParam.getPassword() + UserConstants.USER_SLAT);

        //2.数据库查询
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userLoginParam.getUserName());
        queryWrapper.eq("password",newPwd);

        User user = userMapper.selectOne(queryWrapper);

        //3.结果处理
        if (user == null) {
            log.info("UserServiceImpl.login业务结束，结果:{}","账号和密码错误!");
            return R.fail("账号或者密码错误!");
        }

        log.info("UserServiceImpl.login业务结束，结果:{}","登录成功!");
        //不返回password属性!
        user.setPassword(null);
        return R.ok("登录成功!",user);
    }

    @Override
    public R getUserList(PageParam pageParam) {
        IPage<User> page=new Page<>(pageParam.getCurrentPage(),pageParam.getPageSize());
        IPage<User> page1 = userMapper.selectPage(page, null);
        List<User> records = page1.getRecords();
        Long total=page1.getTotal();
        return R.ok("用户数据查询成功",records,total);


    }

    @Override
    public R removeUser(Integer userId) {
        int i = userMapper.deleteById(userId);
        log.info("***UserServiceImpl.removeUser业务结束，结果:{}", i);
        return R.ok("用户数据删除成功");
    }

    @Override
    public R update(User user) {

        //检查密码,如果和数据库一致 不需要加密! 证明密码没有修改!
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",user.getUserId());
        queryWrapper.eq("password",user.getPassword());
        Long total = userMapper.selectCount(queryWrapper);
        if (total == 0){
            //密码不同,已经修改! 新密码需要加密
            user.setPassword(MD5Util.encode(user.getPassword()+ UserConstants.USER_SLAT));
        }
        int rows = userMapper.updateById(user);
        if (rows == 0){
            return R.fail("用户修改失败!");
        }
        return R.fail("用户修改成功");
    }


}
