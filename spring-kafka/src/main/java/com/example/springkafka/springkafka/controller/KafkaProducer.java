package com.example.springkafka.springkafka.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class KafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final AdminClient adminClient;
    private final ObjectMapper objectMapper;
    @Resource
    private KafkaTemplate defaultKafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, Object> kafkaTemplate, AdminClient adminClient) {
        this.kafkaTemplate = kafkaTemplate;
        this.adminClient = adminClient;
    }

    // 发送消息
    @GetMapping("/kafka/normal/{message}")
    public void sendMessage1(@PathVariable("message") String normalMessage) {
        kafkaTemplate.send("topic1", normalMessage);
    }

    /**
     * 带默认的主题的topic
     *
     * @param normalMessage
     */
    @GetMapping("/kafka/default/{message}")
    public void defaultSendMessage(@PathVariable("message") String normalMessage) {
        defaultKafkaTemplate.sendDefault(normalMessage);
    }

    /**
     * 往指定分区发送消息
     *
     * @param normalMessage
     */
    @GetMapping("/kafka/default2/{message}")
    public void defaultSendMessage2(@PathVariable("message") String normalMessage) {
        send(normalMessage);
    }

    @Transactional
    public void send(String message) {
        defaultKafkaTemplate.sendDefault(2, "2", message).addCallback(success -> {
            System.out.println("发送消息成功:" + success.toString());
        }, fail -> {
            System.out.println("发送消息失败:" + fail.getMessage());
        });
    }


    /**
     * 带时间戳的消息
     *
     * @param normalMessage
     */
    @GetMapping("/kafka/time/{message}")
    public void timestampSendMessage(@PathVariable("message") String normalMessage) throws ExecutionException, InterruptedException {
        Future future = defaultKafkaTemplate.sendDefault(0, System.currentTimeMillis(), "1", normalMessage);
        // send 都是异步，可以用future.get来实现同步
        System.out.println(future.get().toString());
    }


    //带回调方法的
    @GetMapping("/kafka/callback/{message}")
    @Transactional
    public void sendMessage2(@PathVariable("message") String message) {
        kafkaTemplate.send("topic1", message).addCallback(success -> {
            // 消息发送到的topic
            String topic = success.getRecordMetadata().topic();
            // 消息发送到的分区
            int partition = success.getRecordMetadata().partition();
            // 消息在分区内的offset
            long offset = success.getRecordMetadata().offset();
            System.out.println("发送消息成功:" + topic + "-" + partition + "-" + offset);
        }, failure -> {
            System.out.println("发送消息失败:" + failure.getMessage());
        });
    }

    //带回调方法的2
    @GetMapping("/kafka/callback2/{message}")
    public void sendMessage3(@PathVariable("message") String message) {
        kafkaTemplate.send("topic1", message).addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("发送消息失败：" + throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                System.out.println("发送消息成功：" + stringObjectSendResult.getRecordMetadata().topic() + "-"
                        + stringObjectSendResult.getRecordMetadata().partition() + "-" + stringObjectSendResult.getRecordMetadata().offset());

            }
        });
    }

    /**
     * 创建topic
     *
     * @throws InterruptedException
     */
    @GetMapping("/kafka/createTopic")
    public void createTopic() throws InterruptedException {
        NewTopic topic = new NewTopic("topic.quick.initial2", 1, (short) 1);
        adminClient.createTopics(Arrays.asList(topic));
        Thread.sleep(1000);
    }

    /**
     * 查询topic
     *
     * @throws InterruptedException
     */
    @GetMapping("/kafka/{name}")
    public void selectTopic(@PathVariable("name") String name) throws ExecutionException, InterruptedException {
        DescribeTopicsResult result = adminClient.describeTopics(Arrays.asList(name));
        result.all().get().forEach((k, v) -> System.out.println("k: " + k + " ,v: " + v.toString() + "\n"));

    }


    /**
     * 创建事务的方法  本地事务
     */
    @GetMapping("/kafka/transaction/{message}")
    public void executeInTransaction(@PathVariable("message") String message) {
        kafkaTemplate.executeInTransaction(new KafkaOperations.OperationsCallback<String, Object, Object>() {
            @Override
            public Object doInOperations(KafkaOperations<String, Object> operations) {
                operations.send("topic.quick.tran", message);
                //throw new RuntimeException("fail");
                return true;
            }
        });

    }

    /**
     * 创建事务的方法  本地事务
     */
    @GetMapping("/kafka/transactionAnnotation/{message}")
    @Transactional
    public void testTransactionalAnnotation(@PathVariable("message") String message) {
        kafkaTemplate.executeInTransaction(new KafkaOperations.OperationsCallback<String, Object, Object>() {
            @Override
            public Object doInOperations(KafkaOperations<String, Object> operations) {
                operations.send("topic.quick.tran", message);
                //throw new RuntimeException("fail");
                return true;
            }
        });

    }

}
