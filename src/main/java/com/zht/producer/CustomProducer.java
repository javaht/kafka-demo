package com.zht.producer;

import org.apache.kafka.clients.KafkaClient;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
//最简单的生产者代码编写
public class CustomProducer {
    public static void main(String[] args) {


        Properties properties = new Properties();


         //首先是连接集群
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.20.62:9092,192.168.20.63:9092");

        //接着是配置序列化
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());


        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);


        for (int i = 0; i < 6; i++) {
            //发送数据
            kafkaProducer.send(new ProducerRecord<>("first","atguigu"+i));
        }

        //关闭资源

           kafkaProducer.close();
    }
}
