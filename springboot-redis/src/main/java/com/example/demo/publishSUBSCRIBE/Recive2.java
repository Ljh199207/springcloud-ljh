package com.example.demo.publishSUBSCRIBE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

@Component
public class Recive2 {
    @Qualifier("redisTemplate")
    @Autowired
    private RedisTemplate template;

    public void onMessage(String jsonMsg) {
        RedisSerializer<String> valueSerializer = template.getStringSerializer();
        // String deserialize = valueSerializer.deserialize(message.getBody());
        System.out.println(("收到的mq2消息" + jsonMsg));
    }
}
