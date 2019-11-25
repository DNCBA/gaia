package com.mhc.springboot.common.base.distributedlock;

import java.util.concurrent.TimeUnit;

/**
 * @author ：menghui.cao, menghui.cao@leyantech.com
 * @date ：2019-11-21 17:54
 */
public abstract class DistributedLock {

    /**
     * 锁key
     * 锁失败立即返回
     */
    public abstract Boolean lock(String key);

    /**
     * 解锁key
     */
    public  abstract Boolean unlock(String key);

    /**
     * 指定时间失效锁
     */
    public  abstract Boolean lock(String key, long timeout, TimeUnit timeUnit);

    /**
     * 尝试锁,指定间隔时间段和重试次数
     */
    public  abstract Boolean tryLock(String key, Long timeout, TimeUnit timeUnit, Long repeatCount);

}
