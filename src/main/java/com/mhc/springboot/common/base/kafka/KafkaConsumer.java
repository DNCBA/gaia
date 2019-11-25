package com.mhc.springboot.common.base.kafka;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author ：menghui.cao, menghui.cao@leyantech.com
 * @date ：2019-11-25 17:56
 */
@Component
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);


    @KafkaListener(topics = {"testTopic"})
    public void listTestTopic(ConsumerRecord record) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        Object key = record.key();
        Object value = record.value();
        String topic = record.topic();
        int partition = record.partition();
        LOGGER.info(String.format("topic1:%s,partition:%s,key:%s,value:%s,offset:%s", topic, partition, JSON.toJSONString(key), JSON.toJSONString(value), record.offset()));
    }

    @KafkaListener(topics = {"topic2"})
    public void listTestTopic4(ConsumerRecord record) throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        Object key = record.key();
        Object value = record.value();
        String topic = record.topic();
        int partition = record.partition();
        LOGGER.info(String.format("topic4:%s,partition:%s,key:%s,value:%s,offset:%s", topic, partition, JSON.toJSONString(key), JSON.toJSONString(value), record.offset()));
    }

}
