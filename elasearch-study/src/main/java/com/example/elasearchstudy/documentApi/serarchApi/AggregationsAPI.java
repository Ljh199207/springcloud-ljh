package com.example.elasearchstudy.documentApi.serarchApi;

import com.example.elasearchstudy.client.ElasticsearchClient;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.junit.Test;

@Slf4j
public class AggregationsAPI extends ElasticsearchClient {

    @Test
    public void aggregation() {

        SearchResponse searchResponse = client.prepareSearch("cars")
                .setQuery(QueryBuilders.matchAllQuery())
                .addAggregation(
                        AggregationBuilders.terms("colors").field("color")
                                .subAggregation(AggregationBuilders.avg("avg_price").field("price")))
                .get();

        Terms agg1 = searchResponse.getAggregations().get("colors");
        log.info(agg1.toString());
    }

    @Test
    public void histogram() {
        SearchResponse searchResponse = client.prepareSearch("cars")
                .setQuery(QueryBuilders.matchAllQuery())
                .addAggregation(
                        AggregationBuilders.histogram("price").field("price").interval(20000)
                                .subAggregation(AggregationBuilders.sum("revenue").field("price")))
                .get();
        Histogram agg1 = searchResponse.getAggregations().get("price");
        log.info(agg1.toString());
    }

    @Test
    public void terminateAfter() {
        SearchResponse searchResponse = client.prepareSearch("cars")
                .setQuery(QueryBuilders.matchAllQuery())
                .setTerminateAfter(3)  //每个分片最大文档数
                .get();
        log.info(searchResponse.toString());
    }

}
