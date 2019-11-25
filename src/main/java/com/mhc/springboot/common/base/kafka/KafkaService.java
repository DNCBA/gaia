package com.mhc.springboot.common.base.kafka;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author ：menghui.cao, menghui.cao@leyantech.com
 * @date ：2019-11-25 17:35
 */
@Service
public class KafkaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaService.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    public <T> T send(String topic, T value) {
        String key = UUID.randomUUID().toString();
        String data = JSON.toJSONString(value);
        LOGGER.info(String.format("kafkaService send topic:%s,key:%s,value:%s", topic, key, data));
        kafkaTemplate.send(topic, key, data);
        return value;
    }


}
