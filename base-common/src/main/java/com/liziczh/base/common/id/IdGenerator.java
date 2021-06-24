/**
 * Baidu.com Inc.
 * Copyright (c) 2021 All Rights Reserved.
 */
package com.liziczh.base.common.id;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * ID生成器
 *
 * @author chenzhehao
 * @version 1.0
 * @description
 * @date 2021/6/24 4:07 下午
 */
@Slf4j
@Component
public class IdGenerator {

    @Value("${server.workId}")
    private long workId;

    @Value("${server.datacenterId}")
    private long dataCenterId;

    private static volatile SnowFlakeIdWorker instance;

    public SnowFlakeIdWorker getInstance() {
        if (instance == null) {
            synchronized (SnowFlakeIdWorker.class) {
                if (instance == null) {
                    log.info("when instance, workId = {}, datacenterId = {}", workId, dataCenterId);
                    instance = new SnowFlakeIdWorker(workId, dataCenterId);
                }
            }
        }
        return instance;
    }

    public long getId(){
        return this.getInstance().nextId();
    }
}
