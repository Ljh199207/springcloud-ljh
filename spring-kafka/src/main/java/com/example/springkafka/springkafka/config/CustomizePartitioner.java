package com.example.springkafka.springkafka.config;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class CustomizePartitioner implements Partitioner {
    @Override
    public int partition(String s, Object o, byte[] bytes, Object o1, byte[] bytes1, Cluster cluster) {
        List<PartitionInfo> partitions = cluster.partitionsForTopic(s);
        int numPartitions = partitions.size();
        if (o == null) {
            Random rand = new Random();
            return rand.nextInt(numPartitions);
        }
        int floorMod = Math.floorMod(o.hashCode(), numPartitions);
        return floorMod;
    }

    @Override
    public void close() {
    }

    @Override
    public void configure(Map<String, ?> map) {
    }
}
