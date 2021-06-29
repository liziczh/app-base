/**
 * Baidu.com Inc.
 * Copyright (c) 2021 All Rights Reserved.
 */
package com.liziczh.base.common.id;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

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

    private long workId;

    private long dataCenterId;

    @PostConstruct
    public void init() {
        this.workId = this.getWorkId();
        this.dataCenterId = this.getDataCenterId();
    }

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

    /**
     * 获取ID
     *
     * @author chenzhehao
     * @date 2021/6/29 5:19 下午
     */
    public long getId() {
        return this.getInstance().nextId();
    }

    private Long getWorkId() {
        return 0L;
    }

    private Long getDataCenterId() {
        return 0L;
    }

}
