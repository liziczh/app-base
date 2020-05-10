package com.liziczh.base.kafka.listener;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

import com.liziczh.base.kafka.processor.MessageProcessor;
import com.liziczh.base.kafka.producer.MessageProudcer;

public class AppKafkaListener {
	private static final Logger logger = LoggerFactory.getLogger(KafkaListener.class);
	private final String topicName;
	private final String sysCode;
	private MessageProcessor processor;
	private String ExceptionTopicName;
	@Autowired
	private MessageProudcer messageProducer;

	public AppKafkaListener(String topicName, String sysCode) {
		this.topicName = topicName;
		this.sysCode = sysCode;
	}
	@KafkaListener(id = "#{__listener.topicName}", topics = "#{__listener.topicName}", groupId = "#{__listener.sysCode}.#{__listener.topicName}.group", containerFactory = "kafkaListenerContainerFactory")
	public void listen(List<ConsumerRecord<String, String>> consumerRecordList, Acknowledgment ack) {
		for (ConsumerRecord<String, String> record : consumerRecordList) {
			try {
				if (StringUtils.isNotBlank(record.value())) {
					this.processor.executor(record.key(), record.value());
				} else {
					logger.error("消息体为空，key：{}", record.key());
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				try {
					if (StringUtils.isNotBlank(ExceptionTopicName)) {
						messageProducer.sendMessage(ExceptionTopicName, record.key(), record.value());
					}
				} catch (Exception sendError) {
					logger.error(sendError.getMessage(), sendError);
				}
			}
		}
		ack.acknowledge();
	}
}
