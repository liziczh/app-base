package com.liziczh.mvc.mesh.redis.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Redis并发锁服务
 *
 * @author chenzhehao
 * @version 1.0
 * @description
 * @date 2022/1/16 12:11 上午
 */
@Slf4j
@Service
public class RedisUtils {

    @Resource(name = "redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    public Long incr(String key) {
        if (StringUtils.isBlank(key)) {
            return 0L;
        }

        try {
            return redisTemplate.opsForValue().increment(key);
        } catch (Exception e) {
            log.error("RedisUtils.incr, error", e);
            return 0L;
        }

    }

    /**
     * 加锁
     *
     * @param key
     * @param timeout
     * @return boolean
     * @author chenzhehao
     * @date 2022/1/16 6:22 下午
     */
    public boolean tryLock(String key, int timeout) {
        if (StringUtils.isBlank(key)) {
            return true;
        }

        try {
            Boolean lock = redisTemplate.opsForValue().setIfAbsent(key, 1, timeout, TimeUnit.SECONDS);
            return BooleanUtils.isTrue(lock);
        } catch (Exception e) {
            log.error("RedisUtils.tryLock, error", e);
            return false;
        }

    }

    /**
     * 解锁
     *
     * @param key
     * @return void
     * @author chenzhehao
     * @date 2022/1/16 6:22 下午
     */
    public boolean releaseLock(String key) {

        if (StringUtils.isBlank(key)) {
            return true;
        }
        try {
            Boolean delete = redisTemplate.delete(key);
            return BooleanUtils.isTrue(delete);
        } catch (Exception e) {
            log.error("RedisUtils.releaseLock, error", e);
            return false;
        }

    }

}
