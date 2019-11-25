package com.mhc.springboot.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.*;


import java.util.HashMap;
import java.util.Map;

/**
 * @author ：menghui.cao, menghui.cao@leyantech.com
 * @date ：2019-11-25 16:35
 */
@Configuration
public class KafkaConfig {

    @Value("${kafka.bootstrap.servers.config:127.0.0.1:9092}")
    private String bootstrapServersConfig;
    @Value("${kafka.client.id:producerClient1}")
    private String clientId;
    @Value("${kafka.group.id:consumerGroup1}")
    private String groupId;
    @Value("${kafka.ack.config:1}")
    private String ackConfig;
    @Value("${kafka.buffer.memory.config:3276800}")
    private String bufferMemory;
    @Value("${kafka.request.timeout:20000}")
    private String timeout;
    @Value("${kafka.autocommit:true}")
    private String autoCommit;
    @Value("${kafka.autocommit.interval.config:5000}")
    private String commitInterval;
    @Value("${kafka.auto.offset.reset.config:latest}")
    private String autoOffsetReset;


    @Bean
    public ProducerFactory getProducerFactory() {
        Map<String, Object> config = new HashMap<>(8);
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServersConfig);
        config.put(ProducerConfig.ACKS_CONFIG, ackConfig);
        config.put(ProducerConfig.CLIENT_ID_CONFIG, clientId);
        config.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);
        config.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, timeout);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        return new DefaultKafkaProducerFactory<String, Object>(config);
    }


    @Bean
    public ConsumerFactory getConsumerFactory() {
        Map<String, Object> config = new HashMap<>(8);
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServersConfig);
        config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, autoCommit);
        config.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, commitInterval);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        config.put(ConsumerConfig.RECEIVE_BUFFER_CONFIG, bufferMemory);
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        return new DefaultKafkaConsumerFactory<String, Object>(config);
    }

    @Bean
    public KafkaTemplate getKfkaTemplate() {
        return new KafkaTemplate<String, Object>(getProducerFactory());
    }
}
