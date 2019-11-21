package com.mhc.springboot.common.base;

import com.alibaba.fastjson.JSON;
import com.mhc.springboot.base.BaseTest;
import com.mhc.springboot.dao.pojo.UserDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class RedisServiceTest extends BaseTest {

    @Autowired
    private RedisService redisService;

    @Test
    public void testSet() throws InterruptedException {
        System.out.println(redisService.set("testKey", "abcd"));
        String result = redisService.get("testKey");
        System.out.println(result);

        UserDTO userDTO = new UserDTO();
        userDTO.setUserName("username");
        userDTO.setPassword("password");
        System.out.println(redisService.set("userDto", userDTO));
        UserDTO userDto = redisService.get("userDto");
        System.out.println(JSON.toJSONString(userDto));

        for (int i = 0; i < 10; i++) {
            System.out.println(redisService.incr("incrKey"));
        }

        for (int i = 0; i < 12; i++) {
            System.out.println(redisService.decr("incrKey"));
        }


        System.out.println(redisService.set("exprie", "abc", 10L, TimeUnit.SECONDS));
        System.out.println(redisService.expire("testKey", 10L, TimeUnit.SECONDS));

        TimeUnit.SECONDS.sleep(15);

        System.out.println((String) redisService.get("exprie"));
        new CountDownLatch(1);

    }

}