package com.zht.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class MyConsumer {

    public static void main(String[] args) {
        //1.创建消费者的配置信息
        Properties properties = new Properties();
        //2.给配置信息赋值

        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.20.102:9092");
         //开启自动提交
       properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,true);
        //properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,false);

        //自动提交的延迟
         properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"1000");

         //反序列化
         properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
         properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");

          //消费者组
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"bigdata2");

         //重置消费者的offset
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");




        //创建消费者
        KafkaConsumer<String, String>  KafkaConsumer = new KafkaConsumer<String, String>(properties);


        //订阅主题
        KafkaConsumer.subscribe(Arrays.asList("four"));

    while (true){
    //获取数据
    ConsumerRecords<String, String> consumeRecords = KafkaConsumer.poll(100);
    //解析并打印consumeRecords
    for ( ConsumerRecord<String, String> consumeRecord :consumeRecords ) {
        System.out.println(consumeRecord.key()+"-----"+consumeRecord.value());

    }

}




    }
}
