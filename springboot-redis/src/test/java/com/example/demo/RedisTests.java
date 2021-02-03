package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class RedisTests {

    @Autowired
    @Qualifier("scheduleRedisTemplate")
    private RedisTemplate redisTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    public void testMulti() {
        //开启事务
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.multi();
        redisTemplate.opsForValue().set("StringKey", "StringValue");
        String key = (String) redisTemplate.opsForValue().get("StringKey");
        System.out.println("key " + key);
        List<Object> exec = redisTemplate.exec();
        String key1 = (String) redisTemplate.opsForValue().get("StringKey");
        System.out.println("key1 " + key1);
        exec.forEach(e -> System.out.println(e.toString()));

    }

    @Test
    @Transactional
    public void rollBack() {
        try {
            redisTemplate.setEnableTransactionSupport(true);
            redisTemplate.multi();
            redisTemplate.opsForValue().set("key1", "StringValue1");
            redisTemplate.opsForValue().set("key2", "StringValue1");
            redisTemplate.opsForValue().increment("key1");
            redisTemplate.delete("key2");
            List exec = redisTemplate.exec();
            System.out.println(exec.toString());
            System.out.println(redisTemplate.opsForValue().get("key2"));
        } catch (Exception e) {
            System.out.println(" 1 " + redisTemplate.opsForValue().get("key2"));
            System.out.println("2  " + redisTemplate.opsForValue().get("key1"));
            redisTemplate.discard();
        }
    }

    @Test
    public void testPipeline() {

        List list = redisTemplate.executePipelined(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                //开启管道
                redisConnection.openPipeline();
                for (int i = 0; i < 100; i++) {
                    String key = "123" + i;
                    redisConnection.zCount(key.getBytes(), 2, Integer.MAX_VALUE);
                }
                return null;
            }
        });
        list.forEach(e -> System.out.println(e.toString()));
    }

    /**
     * 使用Java API测试流水线的性能
     */
    @SuppressWarnings({"unused", "resource"})
    @Test
    public void testPipelinedByJavaAPI() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(20);
        jedisPoolConfig.setMaxTotal(10);
        jedisPoolConfig.setMaxWaitMillis(20000);

        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "localhost", 6379);
        Jedis jedis = jedisPool.getResource();
        long start = System.currentTimeMillis();
        // 开启流水线
        Pipeline pipeline = jedis.pipelined();
        // 测试10w条数据读写
        for (int i = 0; i < 100000; i++) {
            int j = i + 1;
            pipeline.set("key" + j, "value" + j);
            pipeline.get("key" + j);
        }
        // 只执行同步但不返回结果
        //pipeline.sync();
        // 以list的形式返回执行过的命令的结果
        List<Object> result = pipeline.syncAndReturnAll();
        long end = System.currentTimeMillis();
        // 计算耗时
        System.out.println("耗时" + (end - start) + "毫秒");
    }

    /**
     * 使用RedisTemplate测试流水线
     */
    @SuppressWarnings({"resource", "rawtypes", "unchecked", "unused"})
    @Test
    public void testPipelineBySpring() {
        Long start = System.currentTimeMillis();
        List List = redisTemplate.executePipelined(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                for (int i = 1000000; i < 2000000; i++) {
                    String key = "pipeline_" + i;
                    String value = "value_" + i;
                    redisTemplate.opsForValue().set(key, value);
                }
                return null;
            }
        });
        Long end = System.currentTimeMillis();
        System.out.println("耗时" + (end - start) + "毫秒");
        //耗时1344375毫秒
    }

    @Test
    public void testSingle() {
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            String key = "pipeline_" + i;
            String value = "value_" + i;
            redisTemplate.opsForValue().set(key, value);
        }
        Long end = System.currentTimeMillis();
        System.out.println("耗时" + (end - start) + "毫秒");
        //1604397
    }


    @Test
    public void tes() {
        Map map = new HashMap();
        map.put("id", 60);
        String   params = null;
        try {
            params = objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Integer id = 60;
        String key = "*" ;
        Set<String> keys = redisTemplate.keys(key);
        System.out.println(new GenericJackson2JsonRedisSerializer(params));

        System.out.println(keys);
    }

    @Test
    public void publish() {

        String mess1 = "abcd1";
        String mess2 = "abcd2";
        String mess3 = "abcd3";

        redisTemplate.convertAndSend("message1", mess1);
        redisTemplate.convertAndSend("message2", mess2);
        redisTemplate.convertAndSend("message3", mess3);
    }


    @Test
    public void testLua()  {
        DefaultRedisScript<String> defaultRedisScript = new DefaultRedisScript<>();
        defaultRedisScript.setResultType(String.class);
        defaultRedisScript.setLocation( new ClassPathResource("/test.lua"));
        String key ="d";
        redisTemplate.opsForValue().set(key, "1234");
        Object d = redisTemplate.execute(defaultRedisScript, Collections.singletonList(key));
        System.out.println(d.toString());
    }



    public void nextId(){
       
    }
}
