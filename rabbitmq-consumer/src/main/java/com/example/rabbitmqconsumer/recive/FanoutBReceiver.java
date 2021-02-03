package com.example.rabbitmqconsumer.recive;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "fanout.B")
public class FanoutBReceiver {

    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("FanoutB消费者收到消息  : " + testMessage.toString());
    }

}
