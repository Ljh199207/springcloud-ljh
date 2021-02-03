package com.example.springkafka.springcomsumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableScheduling
public class KafkaConsumer {
    @Autowired
    private KafkaListenerEndpointRegistry registry;

    // 消费监听
    @KafkaListener(topics = {"topic1"})
    public void onMessage1(ConsumerRecord<?, ?> record) {
        // 消费的哪个topic、partition的消息,打印出消息内容
        System.out.println("简单消费：" + record.topic() + "-" + record.partition() + "-" + record.value());
    }

    /**
     * 监听Topic中指定的分区
     *
     * @param record
     */
    @KafkaListener(id = "demo", topics = "topic.quick.tran", topicPartitions = {
            @TopicPartition(topic = "topic.quick.initial2", partitions = {"0", "1"}),
            @TopicPartition(topic = "topic.quick.initial2", partitions = {"3", "4"},
                    partitionOffsets = @PartitionOffset(partition = "2", initialOffset = "100"))
    })
    public void listen(ConsumerRecord<?, ?> record) {
        System.out.println("topic.quick.tran :" + record.toString());
    }

    /**
     * Payload 消息体
     *
     * @param data
     */
    @KafkaListener(id = "demo1", topics = {"topic2"})
    public void listHead(@Payload String data,
                         @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                         @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                         @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                         @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts) {

        System.out.println("--------------------" + "topic.quick.anno receive : \n" +
                "data : " + data + "\n" +
                "key : " + key + "\n" +
                "partitionId : " + partition + "\n" +
                "topic : " + topic + "\n" +
                "timestamp : " + ts + "\n");
    }


    @KafkaListener(id = "ack-1", topics = {"topic-2"}, containerFactory = "ackContainerFactory")
    public void ackListener(ConsumerRecord record, Acknowledgment ack) {
        System.out.println("topic.quick.ack receive : " + record.value());
        ack.acknowledge();
    }

    /**
     * 批量获取数据
     *
     * @param data
     */
    @KafkaListener(id = "batch", clientIdPrefix = "batch", topics = {"topic.quick.batch"}, containerFactory = "batchContainerFactory")
    public void batchListener(List<Object> data) {
        System.out.println("topic.quick.batch  receive : ");
        for (Object s : data) {
            System.out.println(s.toString());
        }
    }


    @KafkaListener(id = "forward", topics = "topic.quick.target")
    @SendTo("topic.quick.real")
    public String forward(String data) {
        System.out.println("topic.quick.target  forward " + data + " to  topic.quick.real");
        return "topic.quick.target send msg : " + data;
    }

    /**
     * 定时任务启动监听
     *
     * @param data
     */

    @KafkaListener(id = "durable", topics = "topic.quick.durable", containerFactory = "delayContainerFactory")
    public void durableListener(String data) {
        //这里做数据持久化的操作
        System.out.println("topic.quick.durable receive : " + data);
    }

    // @Scheduled(cron = "0 24 16 * * ?")
    // @Scheduled(cron = "0 0 0 * * ?")
    //定时器，每天凌晨0点开启监听
    @Scheduled(cron = "0 52 9 * * ?")
    public void startListener() {
        System.out.println("开启监听");
        //判断监听容器是否启动，未启动则将其启动
        if (!registry.getListenerContainer("durable").isRunning()) {
            registry.getListenerContainer("durable").start();
        }
        registry.getListenerContainer("durable").resume();
    }

    //定时器，每天早上10点关闭监听
    @Scheduled(cron = "0 55 9 * * ?")
    public void shutDownListener() {
        System.out.println("关闭监听");
        registry.getListenerContainer("durable").pause();
    }
}
