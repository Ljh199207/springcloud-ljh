package com.example.elasearchstudy.documentApi.indexApi;

import com.example.elasearchstudy.client.ElasticsearchClient;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetResponse;
import org.junit.Test;


@Slf4j
public class GetApi extends ElasticsearchClient {

    @Test
    public void getApi01() {
        GetResponse response = client.prepareGet("twitter", "_doc", "1").get();

        log.info(response.getSourceAsString());
    }

}
