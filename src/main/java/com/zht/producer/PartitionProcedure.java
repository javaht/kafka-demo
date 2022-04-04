package com.zht.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class PartitionProcedure {
    public static void main(String[] args) {

        //首先创建kafka生产者的配置信息
        Properties props = new Properties();
        //指定连接的集群
        // props.put("bootstrap.servers","192.168.20.102:9092");
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.20.102:9092");


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


       //添加自定义的分区器
        props.put("partitioner.class","com.zht.partitioner.MyPartitioner");

        //9.创建生产者对象
        KafkaProducer<String,String> producer = new KafkaProducer<>(props);


        // 三 发送数据
        for (int i = 0; i < 10; i++) {

            producer.send(new ProducerRecord<>("aaa","zht===========" + i), (metadata, exception) -> {
                        if (exception==null){
                            System.out.println(metadata.partition()+"zhou======"+metadata.offset());

                        }else{
                            exception.printStackTrace();
                        }
                    });
        }




        //Thread.sleep(100);
        //11.关闭资源.会把资源回收
        producer.close();


    }
}
