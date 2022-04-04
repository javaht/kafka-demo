package com.zht.producer;

import org.apache.kafka.clients.producer.*;

import java.util.ArrayList;
import java.util.Properties;

public class InterceptorProducer {

    public static void main(String[] args) throws InterruptedException {


         Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.20.62:9092, 192.168.20.63.9092");




        //ACK应答级别 三个参数  0  1 -1
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        //重试次数
        props.put("retries", 3);
        //批次大小
        props.put("batch.size", 16384);
        //等待时间
        props.put("linger.ms", 1);
        //RecordAccumulator 缓冲区大小
        props.put("buffer.memory", 33554432);

        //8.序列化方面的
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");


        ArrayList<String> interceptorList = new ArrayList<>();
        interceptorList.add("com.zht.interceptor.TimeInterceptor");
        interceptorList.add("com.zht.interceptor.CounterInterceptor");

        //添加拦截器(可以放list)
        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG,interceptorList);



        //9.创建生产者对象
        KafkaProducer<String,String> producer = new KafkaProducer<>(props);

        for (int i = 0; i < 10; i++) {
            //10.发送数据
            producer.send(new ProducerRecord<>("first", "zheshikey","tao-----------" + i), new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    System.out.println("这个是分区"+metadata.partition());
                }
            });

        }

        Thread.sleep(100);
        System.out.println("**************************");

        //11.关闭资源.会把资源回收
        producer.close();
    }
}
