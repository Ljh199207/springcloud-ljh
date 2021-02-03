package com.example.elasearchstudy.documentApi.indexApi;

import com.example.elasearchstudy.client.ElasticsearchClient;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.junit.Test;

@Slf4j
public class MultiGetAPI extends ElasticsearchClient {


    @Test
    public void multiGetApi() {

        MultiGetResponse responses = client.prepareMultiGet()
                .add("twitter", "_doc", "1")
                .add("twitter", "_doc", "2", "3")
                .get();

        for (MultiGetItemResponse itemResponse : responses) {
            GetResponse response = itemResponse.getResponse();
            if (response.isExists()) {
                String json = response.getSourceAsString();
                log.info(json);
            }
        }
    }
}
