package com.example.elasearchstudy.documentApi.indexApi;

import com.example.elasearchstudy.client.ElasticsearchClient;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.delete.DeleteResponse;
import org.junit.Test;


@Slf4j
public class DeleteApi extends ElasticsearchClient {

    @Test
    public  void deleteApi() {
        DeleteResponse deleteResponse = client.prepareDelete("twitter", "_doc", "2").get();
        log.info(deleteResponse.toString());
    }
}
