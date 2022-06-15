package com.liziczh.mvc.mesh.redis.service;


/**
 * Redis并发锁服务
 *
 * @author chenzhehao
 * @version 1.0
 * @description
 * @date 2022/1/16 12:11 上午
 */
public interface LockService {

    /**
     * 加锁
     *
     * @param lock
     * @param timeout
     * @return boolean
     * @author chenzhehao
     * @date 2022/1/16 6:22 下午
     */
    boolean tryLock(String lock, int timeout);

    /**
     * 解锁
     *
     * @param lock
     * @return void
     * @author chenzhehao
     * @date 2022/1/16 6:22 下午
     */
    boolean releaseLock(String lock);
}
