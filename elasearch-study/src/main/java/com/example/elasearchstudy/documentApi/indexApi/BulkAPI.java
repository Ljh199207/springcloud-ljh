package com.example.elasearchstudy.documentApi.indexApi;

import com.example.elasearchstudy.client.ElasticsearchClient;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class BulkAPI extends ElasticsearchClient {

    @Test
    public void indexBulk() {
        BulkRequestBuilder bulkRequest = client.prepareBulk();
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

        bulkRequest.add(client.prepareIndex("person", "_doc", "1").setSource(json));
        bulkRequest.add(client.prepareIndex("person", "_doc", "2").setSource(json1));
        bulkRequest.add(client.prepareIndex("person", "_doc", "3").setSource(json2));

        BulkResponse bulkResponse = bulkRequest.get();
        if (bulkResponse.hasFailures()) {
            // process failures by iterating through each bulk response item
            log.info("bulk index fail");
        }
    }
}
