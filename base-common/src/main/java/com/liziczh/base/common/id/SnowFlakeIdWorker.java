package com.liziczh.base.common.id;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhehao.chen
 * @name 雪花算法：生成分布式唯一ID
 * @structure 数据结构：0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000
 * @element 标识位：1位；
 * @element 时间戳差值：41位（当前时间截 - 开始时间截）；
 * @element 数据机器位：10位（5位datacenterId 和 5位workerId）；
 * @element 序列位：12位（）；
 */

/**
 * 雪花
 *
 * @author chenzhehao
 * @date 2021/6/24 3:13 下午
 */
@Slf4j
public class SnowFlakeIdWorker {
    /**
     * 开始时间截 (2015-01-01)
     */
    private static final long FIRST_TIMESTAMP = 1420041600000L;
    /**
     * 机器ID所占位数
     */
    private static final long MACHINE_ID_BITS = 5L;
    /**
     * 数据标识ID所占位数
     */
    private static final long DATA_CENTER_ID_BITS = 5L;
    /**
     * 序列ID所占位数
     */
    private static final long SEQUENCE_BITS = 12L;
    /**
     * 最大机器ID：31
     */
    private static final long MAX_MACHINE_ID = ~(-1L << MACHINE_ID_BITS);
    /**
     * 最大数据标识ID：31
     */
    private static final long MAX_DATA_CENTER_ID = ~(-1L << DATA_CENTER_ID_BITS);
    /**
     * 最大序列掩码：4095
     */
    private static final long MAX_SEQUENCE = ~(-1L << SEQUENCE_BITS);
    /**
     * 机器ID左移位数：12位
     */
    private static final long MACHINE_ID_LEFT_SHIFT = SEQUENCE_BITS;
    /**
     * 数据标识ID左移位数：17位（12+5）
     */
    private static final long DATA_CENTER_ID_LEFT_SHIFT = SEQUENCE_BITS + MACHINE_ID_BITS;
    /**
     * 时间戳差值左移位数：22位（12+5+5）
     */
    private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + MACHINE_ID_BITS;
    /**
     * 毫秒内序列（0~4095）
     */
    private static long sequence = 0L;
    /**
     * 上次生成ID的时间截
     */
    private static long lastTimestamp = -1L;
    /**
     * 工作机器ID（0~31）
     */
    private long workerId;
    /**
     * 数据中心ID（0~31）
     */
    private long dataCenterId;

    /**
     * 构造方法，初始化机器ID和数据中心ID
     *
     * @param workerId
     * @param dataCenterId
     */
    public SnowFlakeIdWorker(long workerId, long dataCenterId) {
        if (workerId < 0 || workerId > MAX_MACHINE_ID) {
            throw new IllegalArgumentException("workerId is invalid");
        }
        if (dataCenterId < 0 || dataCenterId > MAX_DATA_CENTER_ID) {
            throw new IllegalArgumentException("dataCenterId is invalid");
        }
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
    }

    /**
     * 雪花算法生成分布式ID
     *
     * @return ID
     */
    public synchronized long nextId() {
        long timestamp = this.getNewTimestamp();
        // 如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards, Refusing to generate id.");
        }
        // 如果是同一时间生成的，则进行毫秒内序列
        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0) {
                timestamp = this.getNextMill();
            }
        } else {
            sequence = 0L;
        }
        lastTimestamp = timestamp;
        return ((timestamp - FIRST_TIMESTAMP) << TIMESTAMP_LEFT_SHIFT)
                | (dataCenterId << DATA_CENTER_ID_LEFT_SHIFT)
                | (workerId << MACHINE_ID_LEFT_SHIFT)
                | sequence;
    }

    private long getNextMill() {
        long newTimestamp = this.getNewTimestamp();
        while (newTimestamp <= lastTimestamp) {
            newTimestamp = this.getNewTimestamp();
        }
        return newTimestamp;
    }

    private long getNewTimestamp() {
        return System.currentTimeMillis();
    }
}
