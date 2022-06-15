package com.liziczh.mvc.mesh.redis.service.impl;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.liziczh.mvc.mesh.redis.service.LockService;

import lombok.extern.slf4j.Slf4j;

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
public class LockServiceImpl implements LockService {

    @Resource(name = "redisStringTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean tryLock(String key, int timeout) {
        if (StringUtils.isBlank(key)) {
            return true;
        }

        try {
            Boolean lock = redisTemplate.opsForValue().setIfAbsent(key, 1, timeout, TimeUnit.SECONDS);
            return BooleanUtils.isTrue(lock);
        } catch (Exception e) {
            log.error("LockServiceImpl.tryLock, error", e);
            return false;
        }

    }

    @Override
    public boolean releaseLock(String key) {
        if (StringUtils.isBlank(key)) {
            return true;
        }
        try {
            Boolean delete = redisTemplate.delete(key);
            return BooleanUtils.isTrue(delete);
        } catch (Exception e) {
            log.error("LockServiceImpl.releaseLock, error", e);
            return false;
        }
    }
}
