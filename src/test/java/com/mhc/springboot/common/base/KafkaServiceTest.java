package com.mhc.springboot.common.base;


import com.mhc.springboot.base.BaseTest;
import com.mhc.springboot.common.base.kafka.KafkaConsumer;
import com.mhc.springboot.common.base.kafka.KafkaService;
import com.mhc.springboot.dao.pojo.UserDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class KafkaServiceTest extends BaseTest {

    @Autowired
    private KafkaService kafkaService;

    @Autowired
    private KafkaConsumer consumer;


    @Test
    public void testKafkaService() throws InterruptedException {

//        for (int i = 0; i < 20; i++) {
//            send();
//            sendTopc2();
//        }

        TimeUnit.MINUTES.sleep(5);


    }

    private void sendTopc2() {
        UserDTO userDTO = new UserDTO();
        userDTO.setPassword("aasdf");
        userDTO.setUserName("jim");
        kafkaService.send("topic2",userDTO);
    }

    private void send() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("name","tom");
        data.put("age",11);
        data.put("address","shanghai");
        kafkaService.send("testTopic", data);
    }

}