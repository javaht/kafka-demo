package com.zht.partitioner;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

//生产者的自定义分区
public class MyPartitioner  implements Partitioner {

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {

        String msgValue = value.toString();

        int partition;

        if(msgValue.contains("atguigu")){
            partition =0;
        }else{
            partition=1;
        }
        //Integer integer = cluster.partitionCountForTopic(topic);


        return partition;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
