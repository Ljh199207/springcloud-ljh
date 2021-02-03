package com.example.elasearchstudy.documentApi.indexApi;

import com.example.elasearchstudy.client.ElasticsearchClient;
import org.elasticsearch.action.bulk.*;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class UsingBulkProcessor extends ElasticsearchClient {

    @Test
    public void bulkProcessor() throws InterruptedException {

        BulkProcessor bulkProcessor = BulkProcessor.builder(
                client,
                new BulkProcessor.Listener() {
                    @Override
                    public void beforeBulk(long executionId, BulkRequest request) {
                    }

                    @Override
                    public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {
                    }

                    @Override
                    public void afterBulk(long executionId, BulkRequest request, Throwable failure) {
                    }
                }
        )
                .setBulkActions(1000)  ///每次10000请求
                .setBulkSize(new ByteSizeValue(5, ByteSizeUnit.MB)) //拆成5mb一块
                .setFlushInterval(TimeValue.timeValueSeconds(5))  //无论请求数量多少，每5秒钟请求一次。
                .setConcurrentRequests(0) //设置并发请求的数量。值为0意味着只允许执行一个请求。值为1意味着允许1并发请求。
                .setBackoffPolicy(BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(100), 3))
                //设置自定义重复请求机制，最开始等待100毫秒，之后成倍更加，重试3次，当一次或多次重复请求失败后因为计算资源不够抛出
                // EsRejectedExecutionException 异常，可以通过BackoffPolicy.noBackoff()方法关闭重试机制
                .build();

        Map<String, Object> json = new HashMap<String, Object>();
        json.put("user", "kimchy");
        json.put("postDate", new Date());
        json.put("message", "trying out Elasticsearch");
        json.put("gender", "male");
        Map<String, Object> json1 = new HashMap<String, Object>();
        json1.put("user1", "jake");
        json1.put("postDate", new Date());
        json1.put("message", "trying out PhP");
        json1.put("gender", "female");
        Map<String, Object> json2 = new HashMap<String, Object>();
        json2.put("user1", "tom");
        json2.put("postDate", new Date());
        json2.put("message", "trying out Java");
        json2.put("gender", "male");
        bulkProcessor.add(new IndexRequest().index("twitter").source(json));
        bulkProcessor.add(new IndexRequest().index("twitter").source(json1));
        bulkProcessor.add(new IndexRequest().index("twitter").source(json2));

        bulkProcessor.flush();

        bulkProcessor.awaitClose(10, TimeUnit.SECONDS);
        //bulkProcessor.close();
        client.admin().indices().prepareRefresh().get();
        client.prepareSearch().get();
    }
}
