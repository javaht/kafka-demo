package com.zht.producer;

import org.apache.kafka.clients.producer.*;

import java.util.ArrayList;
import java.util.Properties;
//带有callback信息的生产者
public class CallBackProducer {
    public static void main(String[] args) {

        //1.创建配置信息
         Properties properties = new Properties();

         //连接机器的信息
         properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.20.62:9092");

         //3.序列化
         properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
         properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");

          // 二 创建生产者对象

        KafkaProducer<String, String> KafkaProducer = new KafkaProducer<String, String>(properties);



        // 三 发送数据
        for (int i = 0; i < 10; i++) {

            KafkaProducer.send(new ProducerRecord<>("aaa","zht===========" + i), (metadata, exception) -> {
                if (exception==null){
                    System.out.println(metadata.partition()+"zhou======"+metadata.offset());

                }else{
                    exception.printStackTrace();
                }
            });
        }


   KafkaProducer.close();

    }
}
