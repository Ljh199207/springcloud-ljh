package com.example.elasearchstudy.documentApi.serarchApi;

import com.example.elasearchstudy.client.ElasticsearchClient;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.io.stream.InputStreamStreamInput;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.script.mustache.SearchTemplateRequestBuilder;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class SearchTemplate extends ElasticsearchClient {

    @Test
    public void searchTemplate() throws IOException {

        Map<String, Object> template_params = new HashMap<>();
        template_params.put("param_gender", "male");
        Resource resource = new ClassPathResource("template_gender.mustache");
        InputStreamStreamInput streamStreamInput = new InputStreamStreamInput(resource.getInputStream());

        SearchResponse sr = new SearchTemplateRequestBuilder(client)
                .setScript("template_gender")
                .setScriptType(ScriptType.readFrom(streamStreamInput))
                .setScriptParams(template_params)
                .setRequest(new SearchRequest("twitter"))
                .get()
                .getResponse();
        log.info(sr.toString());
    }
}
