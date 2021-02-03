package com.example.elasearchstudy.documentApi.aggregations;

import com.example.elasearchstudy.client.ElasticsearchClient;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.junit.Test;

@Slf4j
public class Structuringaggregations extends ElasticsearchClient {


    @Test
    public void struct() {

        SearchResponse sr = client.prepareSearch("cars")
                .addAggregation(
                        AggregationBuilders.histogram("price").field("price").interval(20000)
                                .subAggregation(AggregationBuilders.sum("revenue").field("price"))
                ).get();
    }

}
