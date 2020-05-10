package com.liziczh.base.kafka.config;

import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppProducerConfig {
	private Properties props = new Properties();
	@Value("${spring.kafka.bootstrap-servers}")
	private String kafkaHost;
	@Value("${spring.kafka.producer.acks:#{null}}")
	private String kafkaAcks;
	@Value("${spring.kafka.producer.buffer-memory:#{null}}")
	private String kafkaBufferMemory;
	@Value("${spring.kafka.producer.retries:#{null}}")
	private String kafkaRetries;
	@Value("${spring.kafka.producer.batch-size:#{null}}")
	private String kafkaBatchSize;
	@Value("${spring.kafka.producer.linger-ms:#{null}}")
	private String kafkaLingertime;
	@Value("${spring.kafka.producer.client-Id:#{null}}")
	private String kafkaClientId;
	@Value("${spring.kafka.producer.key-serializer:org.apache.kafka.common.serialization.StringSerializer}")
	private String keySerializer;
	@Value("${spring.kafka.producer.value-serializer:org.apache.kafka.common.serialization.StringSerializer}")
	private String valueSerializer;

	@PostConstruct
	public void initConfig() {
		props.put(org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaHost);
		if (StringUtils.isNotBlank(kafkaAcks)) {
			props.put(org.apache.kafka.clients.producer.ProducerConfig.ACKS_CONFIG, kafkaAcks);
		}
		if (StringUtils.isNotBlank(kafkaRetries)) {
			props.put(org.apache.kafka.clients.producer.ProducerConfig.RETRIES_CONFIG, kafkaRetries);
		}
		if (StringUtils.isNotBlank(kafkaBatchSize)) {
			props.put(org.apache.kafka.clients.producer.ProducerConfig.BATCH_SIZE_CONFIG, kafkaBatchSize);
		}
		if (StringUtils.isNotBlank(kafkaLingertime)) {
			props.put(org.apache.kafka.clients.producer.ProducerConfig.LINGER_MS_CONFIG, kafkaLingertime);
		}
		if (StringUtils.isNotBlank(kafkaBufferMemory)) {
			props.put(org.apache.kafka.clients.producer.ProducerConfig.BUFFER_MEMORY_CONFIG, kafkaBufferMemory);
		}
		if (StringUtils.isNotBlank(keySerializer)) {
			props.put(org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
		}
		if (StringUtils.isNotBlank(valueSerializer)) {
			props.put(org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);
		}
	}
	public Map<String, Object> getPropsMap() {
		return props.entrySet().stream().collect(
				Collectors.toMap(
						e -> e.getKey().toString(),
						e -> e.getValue().toString()));
	}
}
