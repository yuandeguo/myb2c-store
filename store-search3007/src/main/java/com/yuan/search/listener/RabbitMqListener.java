package com.yuan.search.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuan.doc.ProductDoc;
import com.yuan.pojo.Product;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/4 17:27
 * @Description 监听消息通知
 */
@Component
public class RabbitMqListener {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    /**
     * 演示，注解方式，在消息消费者位置完成 交换机队列关系绑定！
     * @param product
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "insert.queue"),
            exchange = @Exchange("topic.ex"),
            key = "insert.product"
    ))
    public void insert(Product product){
        IndexRequest indexRequest = new IndexRequest("product").id(product.getProductId().toString());
        ProductDoc productDoc = new ProductDoc(product);
        ObjectMapper objectMapper = new ObjectMapper();
        String json  = null;
        try {
            json = objectMapper.writeValueAsString(productDoc);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        indexRequest.source(json, XContentType.JSON);
        try {
            restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除数据
     * @param productId
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "remove.queue"),
            exchange = @Exchange("topic.ex"),
            key = "delete.product"
    ))
    public void remove(Integer productId) throws IOException {

        DeleteRequest request = new DeleteRequest("product")
                .id(productId.toString());

        restHighLevelClient.delete(request,RequestOptions.DEFAULT);
    }
}
