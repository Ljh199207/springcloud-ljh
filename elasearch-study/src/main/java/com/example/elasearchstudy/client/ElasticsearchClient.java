package com.example.elasearchstudy.client;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ElasticsearchClient {

    public TransportClient client;

    @Before
    public void getClient() throws UnknownHostException {
        //如果集群  have to set the cluster name if you use one different than "elasticsearch":
        //In order to enable sniffing, set client.transport.sniff to true
        //client.transport.ignore_cluster_name  true忽略连接节点的集群名称验证
        //client.transport.ping_timeout  等待来自节点的ping响应的时间 5s
        // client.transport.nodes_sampler_interval  对列出和连接的节点进行采样/ ping的频率。默认为5s。
        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch")
                .put("client.transport.sniff", true)
                .build();

        client = new PreBuiltTransportClient(settings).addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));

        // client = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
    }

    @After
    public void CloseClient() {
        if (client != null) {
            client.close();
        }
    }
}
