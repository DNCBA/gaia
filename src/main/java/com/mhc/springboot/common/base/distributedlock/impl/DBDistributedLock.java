package com.mhc.springboot.common.base.distributedlock.impl;

import com.mhc.springboot.common.base.distributedlock.DistributedLock;

import java.util.concurrent.TimeUnit;

/**
 * @author ：menghui.cao, menghui.cao@leyantech.com
 * @date ：2019-11-22 11:03
 * 利用数据库中唯一索引来完成锁的实现
 * 通过控制版本信息乐观锁来控制时间控制
 */
public class DBDistributedLock extends DistributedLock {
    @Override
    public Boolean lock(String key) {
        return null;
    }

    @Override
    public Boolean unlock(String key) {
        return null;
    }

    @Override
    public Boolean lock(String key, long timeout, TimeUnit timeUnit) {
        return null;
    }

    @Override
    public Boolean tryLock(String key, Long timeout, TimeUnit timeUnit, Long repeatCount) {
        return null;
    }
}
