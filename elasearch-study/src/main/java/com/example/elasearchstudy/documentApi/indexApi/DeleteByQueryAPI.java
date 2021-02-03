package com.example.elasearchstudy.documentApi.indexApi;

import com.example.elasearchstudy.client.ElasticsearchClient;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.index.reindex.DeleteByQueryRequestBuilder;
import org.junit.Test;


@Slf4j
public class DeleteByQueryAPI extends ElasticsearchClient {

    @Test
    public void deleteByQueryApi() {
        BulkByScrollResponse byScrollResponse = new DeleteByQueryRequestBuilder(client, DeleteByQueryAction.INSTANCE)
                .filter(QueryBuilders.matchQuery("gender", "male"))  //query
                .source("person")    //index
                .get();   //execute the operation
        Long delete = byScrollResponse.getDeleted();
        log.info("delete", delete);
    }

    @Test
    //异步
    public void asyDeleteByQueryApi() {
        new DeleteByQueryRequestBuilder(client, DeleteByQueryAction.INSTANCE)
                .filter(QueryBuilders.matchQuery("gender", "male"))  //query
                .source("person")    //index
                .execute(new ActionListener<BulkByScrollResponse>() {
                    @Override
                    public void onResponse(BulkByScrollResponse bulkByScrollResponse) {
                        long deleted = bulkByScrollResponse.getDeleted();
                    }

                    @Override
                    public void onFailure(Exception e) {
                        // Handle the exception

                    }
                });
    }
}
