package com.example.springkafka;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringKafkaApplicationTests {
    @Resource
    private KafkaTemplate defaultKafkaTemplate;
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    public void testBatch() {
        for (int i = 0; i < 12; i++) {
            defaultKafkaTemplate.send("topic.quick.durable", "test batch listener,dataNum-" + i);
        }
    }

    @Test
    public void testBatch1() {
        for (int i = 0; i < 100; i++) {
            defaultKafkaTemplate.send("topic.quick.initial2", "test batch listener,dataNum-" + i);
        }
    }

    @Test
    public void test21() {
        Map map = new HashMap<>();
        map.put(KafkaHeaders.TOPIC, "topic2");
        map.put(KafkaHeaders.MESSAGE_KEY, "abc");
        map.put(KafkaHeaders.PARTITION_ID, 0);
        map.put(KafkaHeaders.TIMESTAMP, System.currentTimeMillis());
        defaultKafkaTemplate.send(new GenericMessage<>("test anno listener", map));
    }

    @Test
    public void test1() {
        defaultKafkaTemplate.send("topic-2", "这就是个测试");
    }


    @Test
    @Transactional
    public void testTransactionalAnnotation() {
        kafkaTemplate.send("topic.quick.tran", "test transactional annotation");
        throw new RuntimeException("fail");
    }


    @Test
    public void testForward() {
        defaultKafkaTemplate.send("topic.quick.target", "test @SendTo");
    }

}
