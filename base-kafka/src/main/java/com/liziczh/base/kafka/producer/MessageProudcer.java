package com.liziczh.base.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageProudcer {
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(String topic, String key, String value) throws Exception {
		kafkaTemplate.send(topic, key, value);
	}
	public void sendMessage(String topic, String value) throws Exception {
		kafkaTemplate.send(topic, value);
	}
	public void sendMessage(String topic, Integer partition, String key, String value) throws Exception {
		kafkaTemplate.send(topic, partition, key, value);
	}
}
