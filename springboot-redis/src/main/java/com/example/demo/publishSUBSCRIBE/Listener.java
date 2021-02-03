package com.example.demo.publishSUBSCRIBE;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class Listener {

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory connectionFactory,
                                                                       @Qualifier("messageListenerAdapter1") MessageListenerAdapter messageListenerAdapter,
                                                                       @Qualifier("messageListenerAdapter2") MessageListenerAdapter messageListenerAdapter2) {
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(connectionFactory);
        redisMessageListenerContainer.addMessageListener(messageListenerAdapter, new PatternTopic("message1"));
        redisMessageListenerContainer.addMessageListener(messageListenerAdapter2, new PatternTopic("message2"));
        redisMessageListenerContainer.addMessageListener(messageListenerAdapter2, new PatternTopic("message3"));
        return redisMessageListenerContainer;
    }


    /**
     * 消息监听适配器，注入接受消息方法，输入方法名字 反射方法
     * //当没有继承MessageListener时需要写方法名字
     *
     * @param receiver
     * @return
     */
    @Bean(value = "messageListenerAdapter1")
    public MessageListenerAdapter getMessageListenerAdapter(Recive receiver) {
        return new MessageListenerAdapter(receiver, "onMessage");
    }

    @Bean(value = "messageListenerAdapter2")
    public MessageListenerAdapter messageListenerAdapter2(Recive2 recive2) {
        return new MessageListenerAdapter(recive2, "onMessage");
    }
}
