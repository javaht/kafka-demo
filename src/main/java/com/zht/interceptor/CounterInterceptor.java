package com.zht.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

public class CounterInterceptor implements ProducerInterceptor<String,String> {
    int sucess;
    int error;

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        //这里面动数据

        return record;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
      //
        if(metadata!=null){
            sucess++;
        }else{
            error++;
        }


    }

    @Override
    public void close() {
        System.out.println("sucess"+sucess);
        System.out.println("error"+error);
    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
