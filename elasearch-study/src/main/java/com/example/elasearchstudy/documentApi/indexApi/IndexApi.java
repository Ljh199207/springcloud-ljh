package com.example.elasearchstudy.documentApi.indexApi;

import com.example.elasearchstudy.client.ElasticsearchClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class IndexApi extends ElasticsearchClient{

    @Test
    //string
    public void indexApi01() {
        String json = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";

        IndexResponse response = client.prepareIndex("twitter", "_doc", "1")
                .setSource(json, XContentType.JSON).get();
        System.out.println("testForUseStr twitter 创建成功");
        log.info("response", response.getResult().toString());

        GetResponse getResponse = client.prepareGet("twitter", "_doc", "1").get();
        log.info("getResponse", getResponse.getSource());
    }

    @Test
    //Map
    public void indexApi02() {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("user", "kimchy");
        json.put("postDate", new Date());
        json.put("message", "trying out Elasticsearch");

        IndexResponse indexResponse = client.prepareIndex("twitter", "_doc", "2")
                .setSource(json).get();
        log.info("testForUseMap twitter 创建成功", indexResponse);
    }

    @Test
    //use Jackson to serialize your beans to JSON
    public void indexApi03() throws JsonProcessingException {

        // instance a json mapper
        ObjectMapper mapper = new ObjectMapper();
        // generate json
        byte[] json = mapper.writeValueAsBytes(new Object());
    }

    @Test
    //use elasticsearch helpers
    public void indexApi04() throws IOException {

        XContentBuilder builder = XContentFactory.jsonBuilder().startObject()
                .field("user", "kimchy")
                .field("postDate", new Date())
                .field("message", "trying out Elasticsearch")
                .endObject();
        IndexResponse indexResponse = client.prepareIndex("twitter", "_doc", "3").setSource(builder).get();
        String index = indexResponse.getIndex();
        String type = indexResponse.getType();
        String id = indexResponse.getId();
        long version = indexResponse.getVersion();
        RestStatus status = indexResponse.status();
        log.info(index + type + id + version + status);
        log.info(indexResponse.toString());
    }


}
