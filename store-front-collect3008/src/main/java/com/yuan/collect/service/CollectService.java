package com.yuan.collect.service;

import com.yuan.pojo.Collect;
import com.yuan.utils.R;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/4 22:02
 * @Description null
 */
public interface CollectService {
    R save(Collect collect);


    /**
     * 根据用户id查看收藏商品信息集合
     * @param user_id
     * @return
     */
    R ids(Integer user_id);
    /**
     * 根据用户传递的用户id和商品id删除收藏
     * @return
     */
    R remove(Integer user_id, Integer product_id);
}
