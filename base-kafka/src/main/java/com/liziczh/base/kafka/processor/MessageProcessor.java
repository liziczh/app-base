package com.liziczh.base.kafka.processor;

public abstract class MessageProcessor {
	public abstract void executor(String key, String messageContent) throws Exception;
}
