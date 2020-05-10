package com.liziczh.base.kafka.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties.AckMode;
import org.springframework.kafka.support.LogIfLevelEnabled;

import com.liziczh.base.kafka.config.AppConsumerConfig;

@Configuration
public class KafkaConsumerInit {
	@Value("${spring.kafka.consumer.concurrency:1}")
	private String concurrency;
	@Autowired
	private AppConsumerConfig consumerConfig;

	@Bean
	KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
		factory.setConsumerFactory(consumerFactory());
		factory.setConcurrency(Integer.valueOf(concurrency));
		factory.setBatchListener(true);
		factory.getContainerProperties().setCommitLogLevel(LogIfLevelEnabled.Level.INFO);
		factory.getContainerProperties().setAckMode(AckMode.MANUAL);
		return factory;
	}
	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		return new DefaultKafkaConsumerFactory<String, String>(consumerConfig.getPropsMap());
	}
}
