package com.mhc.springboot.common.base.distributedlock.impl;

import com.mhc.springboot.common.base.RedisService;
import com.mhc.springboot.common.base.distributedlock.DistributedLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author ：menghui.cao, menghui.cao@leyantech.com
 * @date ：2019-11-22 11:02
 */
@Component
public class RedisDistributedLock extends DistributedLock {


    private static final Logger LOGGER = LoggerFactory.getLogger(RedisDistributedLock.class);
    private static final Integer CONSTANCE_VALUE = 1;


    @Autowired
    private RedisService redisService;

    @Override
    public Boolean lock(String key) {
        Boolean result = false;
        try {
            return redisService.setIfAbsent(key, CONSTANCE_VALUE);
        } catch (Exception e) {
            LOGGER.error("redis distributedLock lock error, key:" + key, e);
            result = false;
        }
        return result;
    }

    @Override
    public Boolean unlock(String key) {
        Boolean result = false;
        try {
            return redisService.delete(key);
        } catch (Exception e) {
            LOGGER.error("redis distributedLock unlock error,key:" + key, e);
            result = false;
        }
        return result;
    }

    @Override
    public Boolean lock(String key, long timeout, TimeUnit timeUnit) {
        Boolean result = false;
        try {
            return redisService.setIfAbsent(key, CONSTANCE_VALUE, timeout, timeUnit);
        } catch (Exception e) {
            LOGGER.error("redis distributedLock lock error,key:" + key, e);
        }
        return null;
    }

    @Override
    public Boolean tryLock(String key, Long timeout, TimeUnit timeUnit, Long repeatCount) {
        Boolean result = false;
        try {
            Integer initCount = 0;
            while (initCount < repeatCount) {
                if (redisService.setIfAbsent(key, CONSTANCE_VALUE)) {
                    result = true;
                    break;
                }
                timeUnit.sleep(timeout);
                initCount++;
            }
        } catch (Exception e) {
            LOGGER.error("redis distributedLock tryLock,key:" + key, e);
        }
        return result;
    }
}
