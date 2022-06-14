package com.zht.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class MyProducer {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
/*
* ProducerConfig(生产者)
* CommonClientConfigs(公共的)
* */

         //首先创建kafka生产者的配置信息
        Properties props = new Properties();
        //指定连接的集群
       // props.put("bootstrap.servers","192.168.20.102:9092");
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.20.102:9092");


         //ACK应答级别 三个参数  0  1 -1
        props.put(ProducerConfig.ACKS_CONFIG, "all");
          //重试次数
        props.put("retries", 3);


        //提高生产者吞吐量的四个参数
         //RecordAccumulator 缓冲区大小
        props.put("buffer.memory", 33554432);
        //批次大小
        props.put("batch.size", 16384);
        //等待时间
        props.put("linger.ms", 1);
        //压缩
        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG,"snappy");



         //8.序列化方面的
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");


         //9.创建生产者对象
        KafkaProducer<String,String> producer = new KafkaProducer<>(props);

        for (int i = 0; i < 10; i++) {
            //10.发送数据
            producer.send(new ProducerRecord<>("four", "zheshikey","tao-----------" + i));




        }

         //Thread.sleep(100);
        //11.关闭资源.会把资源回收
        producer.close();


    }
}
