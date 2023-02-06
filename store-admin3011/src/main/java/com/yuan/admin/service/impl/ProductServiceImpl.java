package com.yuan.admin.service.impl;

import com.aliyun.oss.common.comm.ServiceClient;
import com.yuan.admin.service.ProductService;
import com.yuan.clients.ProductClient;
import com.yuan.clients.SearchClient;
import com.yuan.param.ProductSearchParam;
import com.yuan.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/6 22:13
 * @Description null
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Resource
    private SearchClient searchClient;


    @Override
    public R search(ProductSearchParam productSearchParam) {
        R r = searchClient.search(productSearchParam);

        log.info("ProductServiceImpl.list业务结束，结果:{}",r);
        return r;
    }
}
