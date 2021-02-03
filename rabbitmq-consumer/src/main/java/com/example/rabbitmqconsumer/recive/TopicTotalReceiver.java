package com.example.rabbitmqconsumer.recive;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "topic.woman")
public class TopicTotalReceiver {
    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("第一个TopicTotalReceiver消费者收到消息  : " + testMessage.toString());
    }
}
