package com.liziczh.base.kafka.producer;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;

@Component
public abstract class MessageSendCallback implements ProducerListener<String, String> {
	private static final Logger logger = LoggerFactory.getLogger(MessageSendCallback.class);

	@Override
	public void onSuccess(ProducerRecord<String, String> producerRecord, RecordMetadata recordMetadata) {
		try {
			logger.debug("主题{},分区{},偏移量{}发送成功", recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset());
		} catch (Exception e) {
			logger.error("处理发送成功消息出错", e);
		}
	}
	@Override
	public void onError(ProducerRecord<String, String> producerRecord, Exception exception) {
		try {
			logger.error("主题:{},分区:{},Key:{}发送失败", producerRecord.topic(), producerRecord.partition(), producerRecord.value());
			logger.error(exception.getMessage(), exception);
		} catch (Exception e) {
			logger.error("处理发送失败消息出错", e);
		}
	}
}
