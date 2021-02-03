package com.example.elasearchstudy.documentApi.indexApi;

import com.example.elasearchstudy.client.ElasticsearchClient;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.ReindexAction;
import org.elasticsearch.index.reindex.ReindexRequestBuilder;
import org.junit.Test;

@Slf4j
public class ReindexApi extends ElasticsearchClient {


    @Test
    public void reindex() {
        BulkByScrollResponse reponse = new ReindexRequestBuilder(client, ReindexAction.INSTANCE)
                .source("twitter") //目标索引
                .destination("twitter2") //目的索引
                .filter(QueryBuilders.matchQuery("gender", "male")) //过滤
                .get();
        log.info(reponse.toString());
    }
}
