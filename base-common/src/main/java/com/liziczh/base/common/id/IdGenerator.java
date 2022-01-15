package com.liziczh.base.common.id;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.liziczh.base.common.util.HostUtils;

import lombok.extern.slf4j.Slf4j;

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

    private static volatile SnowFlakeIdWorker instance;
    private long workId;
    private long dataCenterId;

    public static void main(String[] args) {
        IdGenerator idGenerator = new IdGenerator();
        idGenerator.init();
        System.out.println(idGenerator.getId());
    }

    @PostConstruct
    public void init() {
        this.workId = this.getWorkId();
        this.dataCenterId = this.getDataCenterId();
    }

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

    private Integer getWorkId() {
        int hashCode = HostUtils.getHostname().hashCode();
        return Math.abs(hashCode % 10000);
    }

    private Integer getDataCenterId() {
        int hashCode = HostUtils.getIp().hashCode();
        return Math.abs(hashCode % 10000);
    }

}
