package com.example.elasearchstudy.documentApi.serarchApi;

import com.example.elasearchstudy.client.ElasticsearchClient;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.junit.Test;

@Slf4j
public class UsingScrollsInJava extends ElasticsearchClient {

    @Test
    public void scroll() {
        QueryBuilder qb = QueryBuilders.termQuery("gender", "male");
        SearchResponse response = client.prepareSearch("twitter")
                .setQuery(qb)
                .setSize(1)
                .setScroll(new TimeValue(60000))
                .get();
        do {
            for (SearchHit hit : response.getHits().getHits()) {
                //Handle the hit...
                log.info(hit.toString());
            }
            response = client.prepareSearchScroll(response.getScrollId()).setScroll(new TimeValue(60000)).execute().actionGet();
        }
        while (response.getHits().getHits().length != 0);
    }
}
