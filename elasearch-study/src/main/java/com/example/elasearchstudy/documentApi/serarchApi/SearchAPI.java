package com.example.elasearchstudy.documentApi.serarchApi;

import com.example.elasearchstudy.client.ElasticsearchClient;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;

@Slf4j
public class SearchAPI extends ElasticsearchClient {


    @Test
    public void search() {
        SearchResponse searchReponse = client.prepareSearch("twitter")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.matchQuery("gender", "male"))
                .setPostFilter(QueryBuilders.rangeQuery("postDate").format("yyyyMMdd").gt("20200101").lt("20201212"))
                .get();
        log.info(searchReponse.toString());
    }
}
