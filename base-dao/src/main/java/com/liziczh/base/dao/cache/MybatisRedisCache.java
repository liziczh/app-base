package com.liziczh.base.dao.cache;

import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * MybatisRedisCache
 *
 * @author chenzhehao
 * @version 1.0
 * @description
 * @date 2022/1/16 12:11 上午
 */
public class MybatisRedisCache implements Cache {
    private final String cacheId;
    private final RedisTemplate<Object, Object> redisTemplate = SpringContextHolder.getBean("redisTemplate");
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public MybatisRedisCache(final String cacheId) {
        if (cacheId == null) {
            throw new IllegalArgumentException("缓存实例ID不可为空");
        }
        this.cacheId = cacheId;
    }

    public String getId() {
        return cacheId;
    }

    public void putObject(Object key, Object value) {
        redisTemplate.boundHashOps(cacheId).put(key, value);
    }

    public Object getObject(Object key) {
        return redisTemplate.boundHashOps(cacheId).get(key);
    }

    public Object removeObject(Object key) {
        return redisTemplate.boundHashOps(cacheId).delete(key);
    }

    public void clear() {
        redisTemplate.delete(cacheId);
    }

    public int getSize() {
        Map<Object, Object> entries = redisTemplate.boundHashOps(cacheId).entries();
        if (entries != null) {
            return entries.size();
        } else {
            return 0;
        }
    }

    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }
}
