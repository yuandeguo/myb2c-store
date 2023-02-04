package com.yuan.search.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuan.param.ProductSearchParam;
import com.yuan.pojo.Product;
import com.yuan.search.service.SearchService;
import com.yuan.utils.R;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/4 15:46
 * @Description null
 */
@Service
@Slf4j
public class SearchServiceImpl  implements SearchService {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    /**
     * 1. 根据关键字和分页参数，进行es索引查询
     * 2. 将结果封装到R中，返回商品服务即可
     * 关键字为空则为查询全部
     *
     * @param productSearchParam
     * @return
     */
    @Override
    public R search(ProductSearchParam productSearchParam) {

        SearchRequest searchRequest = new SearchRequest("product");
        String search = productSearchParam.getSearch();
        if (StringUtils.isEmpty(search))
        {
            //null.不添加all关键字，查询全部
            searchRequest.source().query(QueryBuilders.matchAllQuery());

        }
        else{
            searchRequest.source().query(QueryBuilders.matchQuery("all",search));
        }
        //设置分页参数
        searchRequest.source().from((productSearchParam.getCurrentPage()-1)*productSearchParam.getPageSize());//偏移量，（当前页数-1）*页容量
        searchRequest.source().size(productSearchParam.getPageSize());
        SearchResponse response = null;
        try {
            response=    restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //结果集解析
        // 商品服务： R msg total  符合的数量| data 商品的集合

SearchHits hits=response.getHits();
        //查询符合的数量
     Long value=   hits.getTotalHits().value;
        SearchHit[] items = hits.getHits();

        ObjectMapper objectMapper = new ObjectMapper();
        List<Product> productList = new ArrayList<>();

        for(SearchHit item :items)
        {
            String sourceAsString=item.getSourceAsString();

            Product product = null;
            try {
                //修改product实体类，添加忽略all属性
                product = objectMapper.readValue(sourceAsString, Product.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            productList.add(product);

        }
        log.info("***SearchServiceImpl.search业务结束，结果:{}",productList );
        return R.ok(null,productList,value);
    }

}