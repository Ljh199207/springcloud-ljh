package com.example.elasearchstudy.documentApi.serarchApi;

import com.example.elasearchstudy.client.ElasticsearchClient;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;

@Slf4j
public class MultiSearchAPI extends ElasticsearchClient {

    @Test
    public void multiSearch() {
        SearchRequestBuilder sr1 = client.prepareSearch("twitter").setQuery(QueryBuilders.matchQuery("gender", "female"));
        SearchRequestBuilder sr2 = client.prepareSearch("twitter2").setQuery(QueryBuilders.matchQuery("gender", "male"));
        MultiSearchResponse sr = client.prepareMultiSearch().add(sr1).add(sr2).get();
        // You will get all individual responses from MultiSearchResponse#getResponses()
        long nbHits = 0;
        for (MultiSearchResponse.Item item : sr.getResponses()) {
            SearchResponse response = item.getResponse();
            nbHits += response.getHits().getTotalHits().value;
            log.info(response.toString() + "--{}--" + nbHits);
        }
    }
}
