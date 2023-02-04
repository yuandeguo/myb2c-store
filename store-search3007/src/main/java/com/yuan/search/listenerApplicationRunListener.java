package com.yuan.search;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuan.clients.ProductClient;
import com.yuan.doc.ProductDoc;
import com.yuan.pojo.Product;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/4 0:02
 * @Description 监控程序启动,初始化es数据
 */
@Component
@Slf4j
public class listenerApplicationRunListener implements ApplicationRunner {
    @Resource
    private RestHighLevelClient restHighLevelClient;

    @Resource
    private ProductClient productClient;
    private String createIndex = "{\n" +
            "  \"mappings\": {\n" +
            "    \"properties\": {\n" +
            "      \"productId\":{\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"productName\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_smart\",\n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"categoryId\":{\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"productTitle\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_smart\",\n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"productIntro\":{\n" +
            "        \"type\":\"text\",\n" +
            "        \"analyzer\": \"ik_smart\",\n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"productPicture\":{\n" +
            "        \"type\": \"keyword\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"productPrice\":{\n" +
            "        \"type\": \"double\",\n" +
            "        \"index\": true\n" +
            "      },\n" +
            "      \"productSellingPrice\":{\n" +
            "        \"type\": \"double\"\n" +
            "      },\n" +
            "      \"productNum\":{\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"productSales\":{\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"all\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_max_word\"\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";


    /**
     * 在此方法实现es的数据同步
     * 1、判断es中的product索引是否存在
     * 2、不存在，java代码创建一个
     * 3、存在，删除原来的
     * 4、进行es库的更新
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
//数据库数据初始化
        //判断是否存在product索引
        GetIndexRequest getIndexRequest = new GetIndexRequest("product");
        boolean exists = restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);

        if (exists){
            DeleteByQueryRequest queryRequest=new DeleteByQueryRequest("product");
            queryRequest.setQuery(QueryBuilders.matchAllQuery());//全部删除
            restHighLevelClient.deleteByQuery(queryRequest,RequestOptions.DEFAULT);

        }
else {
    //不存在则创建，索引表product
            CreateIndexRequest createIndexRequest=new CreateIndexRequest("product");
            createIndexRequest.source(createIndex,XContentType.JSON);
            restHighLevelClient.indices().create(createIndexRequest,RequestOptions.DEFAULT);

        }
        //查询全部数据
        List<Product> list = productClient.list();
//插入全部数据
        BulkRequest bulkRequest=new BulkRequest();
        ObjectMapper objectMapper = new ObjectMapper();
        for (Product product : list) {
            ProductDoc productDoc = new ProductDoc(product);//用户插入数据
            IndexRequest indexRequest = new IndexRequest("product").id(productDoc.getProductId().toString());
            //将productDoc转为json
            String json=objectMapper.writeValueAsString(productDoc);
            indexRequest.source(json,XContentType.JSON);
            bulkRequest.add(indexRequest);

        }
        restHighLevelClient.bulk(bulkRequest,RequestOptions.DEFAULT);


    }
}
