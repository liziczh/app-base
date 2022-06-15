package com.liziczh.mvc.mesh.redis.service;

/**
 * DemoRedis服务
 *
 * @author chenzhehao
 * @version 1.0
 * @description
 * @date 2022/1/16 12:11 上午
 */
public interface DemoRedisService {
    /**
     * 设置值
     *
     * @param key
     * @param value
     * @return void
     * @author chenzhehao
     * @date 2022/1/16 6:23 下午
     */
    void setValue(String key, String value);

    /**
     * 获取值
     *
     * @param key
     * @return java.lang.String
     * @author chenzhehao
     * @date 2022/1/16 6:23 下午
     */
    String getValue(String key);

    /**
     * key是否存在
     *
     * @param key
     * @return java.lang.Boolean
     * @author chenzhehao
     * @date 2022/1/16 6:24 下午
     */
    Boolean hasKey(String key);

    /**
     * 删除key
     *
     * @param key
     * @return void
     * @author chenzhehao
     * @date 2022/1/16 6:24 下午
     */
    void delKey(String key);
}
