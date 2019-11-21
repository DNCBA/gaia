package com.mhc.springboot.common.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author ：menghui.cao, menghui.cao@leyantech.com
 * @date ：2019-11-21 17:28
 */
@Service
public class RedisService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisService.class);

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 存储对应的k-v
     */
    public Boolean set(String key, Object value) {
        Boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value);
            result = true;
        } catch (Exception e) {
            LOGGER.error("RedisService putValue error key:" + key, e);
            result = false;
        }
        return result;
    }

    public Boolean set(String key, Object value, long timeout, TimeUnit unit) {
        Boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value, timeout, unit);
            result = true;
        } catch (Exception e) {
            LOGGER.error("RedisService putValue error key:" + key, e);
            result = false;
        }
        return result;
    }


    /**
     * 获取对应的k-v
     */
    public <T> T get(String key) {
        T result = null;
        try {
            return (T) redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            LOGGER.error("RedisService getValue error key:" + key, e);
            result = null;
        }
        return result;
    }

    /**
     * 删除指定key
     */
    public Boolean delete(String key) {
        Boolean result = false;
        try {
            return redisTemplate.delete(key);
        } catch (Exception e) {
            LOGGER.error("RedisService delete error key:" + key, e);
            result = false;
        }
        return result;
    }

    /**
     * 指定缓存时间
     */
    public Boolean expire(String key, long timeout, TimeUnit unit) {
        Boolean result = false;
        try {
            return redisTemplate.expire(key, timeout, unit);
        } catch (Exception e) {
            LOGGER.error("RedisService expire error key:" + key, e);
            result = false;
        }
        return result;
    }


    /**
     * incr
     */
    public Long incr(String key) {
        return redisTemplate.opsForValue().increment(key);
    }

    public Long incr(String key, Long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * decr
     */
    public Long decr(String key) {
        return redisTemplate.opsForValue().decrement(key);
    }

    public Long decr(String key, Long delta) {
        return redisTemplate.opsForValue().decrement(key, delta);
    }


}
