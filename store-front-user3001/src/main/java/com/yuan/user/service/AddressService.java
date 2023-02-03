package com.yuan.user.service;

import com.yuan.pojo.Address;
import com.yuan.utils.R;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/2 22:15
 * @Description null
 */
public interface AddressService {

    /**
     * 根据用户id查询 地址数据!
     *   1.直接进行数据库查询
     *   2.结果封装即可
     * @param userId 用户id 已经校验完毕
     * @return 001 004
     */
    R list(Integer userId);


    /**
     * 插入地址数据，插入功能返回新的数据集合
     * @param address  地址数据已经校验完毕哦!
     * @return 001 004 数据集合
     */
    R save(Address address);

    /**
     * 根据id 删除地址数据
     *
     * @param id 地址id
     * @return 结果 001  004
     */
    R remove(Integer id);
}
