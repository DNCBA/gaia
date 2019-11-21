package com.mhc.springboot.common.base;

import java.util.concurrent.TimeUnit;

/**
 * @author ：menghui.cao, menghui.cao@leyantech.com
 * @date ：2019-11-21 17:54
 */
public interface DistributedLock {

    /**
     * 锁key
     * 锁失败立即返回
     */
    Boolean lock(String key);

    /**
     * 解锁key
     */
    Boolean unlock(String key);

    /**
     * 指定时间失效锁
     */
    Boolean lock(String key, long timeout, TimeUnit timeUnit);

    /**
     * 尝试锁,指定等待时间段和重试次数
     */
    Boolean tryLock(String key, Long timeout, TimeUnit timeUnit, Long repeatCount);

}
