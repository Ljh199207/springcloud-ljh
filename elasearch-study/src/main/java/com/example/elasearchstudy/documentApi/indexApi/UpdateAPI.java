package com.example.elasearchstudy.documentApi.indexApi;

import com.example.elasearchstudy.client.ElasticsearchClient;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.Test;

import java.io.IOException;

@Slf4j
public class UpdateAPI extends ElasticsearchClient {

    @Test
    public void updateApi() throws IOException {
        UpdateRequest request = new UpdateRequest();
        request.index("person");
        request.id("2");
        request.doc(XContentFactory.jsonBuilder().startObject()
                .field("gender", "male")
                .endObject()
        );
        client.update(request, new ActionListener<UpdateResponse>() {
            @Override
            public void onResponse(UpdateResponse updateResponse) {
                log.info(updateResponse.getGetResult().toString());
            }
            @Override
            public void onFailure(Exception e) {
            }
        });
    }


}
