package com.yuan.clients;

import com.yuan.param.ProductSearchParam;
import com.yuan.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/4 16:58
 * @Description 搜索服务调用客户端
 *
 */
@FeignClient(name = "search-service3007")
public interface SearchClient {

    /**
     * 搜索服务 商品查询
     * @param ProductSearchParam
     * @return
     */
    @PostMapping("/search/product")
    R search(@RequestBody ProductSearchParam ProductSearchParam);
}
