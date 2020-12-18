package com.liziczh.base.common.util;

import lombok.extern.slf4j.Slf4j;

/**
 * @name 雪花算法：生成分布式唯一ID
 * @structure 数据结构：0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000
 * @element 标识位：1位；
 * @element 时间戳差值：41位（当前时间截 - 开始时间截）；
 * @element 数据机器位：10位（5位datacenterId 和 5位workerId）；
 * @element 序列位：12位（）；
 * @author zhehao.chen
 */
@Slf4j
public class SnowFlakeIdWorker {
	/** 开始时间截 (2015-01-01) */
	private final long twepoch = 1420041600000L;
	/** 机器ID所占位数 */
	private final long workerIdBits = 5L;
	/** 数据标识ID所占位数 */
	private final long dataCenterIdBits = 5L;
	/** 序列ID所占位数 */
	private final long sequenceBits = 12L;
	/** 最大机器ID：31 */
	private final long maxWorkerId = ~(-1L << workerIdBits);
	/** 最大数据标识ID：31 */
	private final long maxDataCenterId = ~(-1L << dataCenterIdBits);
	/** 机器ID左移位数：12位 */
	private final long workerIdLeftShift = sequenceBits;
	/** 数据标识ID左移位数：17位（12+5） */
	private final long dataCenterIdLeftShift = sequenceBits + workerIdBits;
	/** 时间戳差值左移位数：22位（12+5+5） */
	private final long timestampLeftShift = sequenceBits + workerIdBits;
	/** 序列掩码：4095 */
	private final long sequenceMask = ~(-1L << sequenceBits);
	/** 工作机器ID（0~31） */
	private long workerId;
	/** 数据中心ID（0~31） */
	private long dataCenterId;
	/** 毫秒内序列（0~4095） */
	private long sequence = 0L;
	/** 上次生成ID的时间截 */
	private long lastTimestamp = -1L;

	public SnowFlakeIdWorker(long workerId, long dataCenterId) {
		if (workerId < 0 || workerId > maxWorkerId) {
			throw new IllegalArgumentException("workerId is invalid");
		}
		if (dataCenterId < 0 || dataCenterId > maxDataCenterId) {
			throw new IllegalArgumentException("dataCenterId is invalid");
		}
		this.workerId = workerId;
		this.dataCenterId = dataCenterId;
	}
	public synchronized long nextId() {
		long timestamp = System.currentTimeMillis();
		// 如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
		if (timestamp < lastTimestamp) {
			throw new RuntimeException("系统时间回退，无法生成ID");
		}
		// 如果是同一时间生成的，则进行毫秒内序列
		if (timestamp == lastTimestamp) {
			sequence = (sequence + 1) & sequenceMask;
			if (sequence == 0) {
				long nextTimestamp = System.currentTimeMillis();
				while (nextTimestamp <= lastTimestamp) {
					nextTimestamp = System.currentTimeMillis();
				}
				timestamp = nextTimestamp;
			}
		} else {
			sequence = 0L;
		}
		lastTimestamp = timestamp;
		return ((timestamp - twepoch) << timestampLeftShift)
				| (dataCenterId << dataCenterIdLeftShift)
				| (workerId << workerIdLeftShift)
				| sequence;
	}
}
