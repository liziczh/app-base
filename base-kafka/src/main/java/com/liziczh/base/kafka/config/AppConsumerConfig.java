package com.liziczh.base.kafka.config;

import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConsumerConfig {
	private Properties props = new Properties();
	@Value("${spring.kafka.bootstrap-servers}")
	private String kafkaHost;
	@Value("${spring.kafka.consumer.group-id:#{null}}")
	private String groupId;
	@Value("${spring.kafka.consumer.enable-auto-commit:#{null}}")
	private String enableAutoCommit;
	@Value("${spring.kafka.consumer.auto-commit-interval-ms:#{null}}")
	private String autoCommitIntervalMs;
	@Value("${spring.kafka.consumer.auto-offset-reset:#{null}}")
	private String autoOffsetReset;
	@Value("${spring.kafka.consumer.valueDeserializer:org.apache.kafka.common.serialization.StringDeserializer}")
	private String keyDeserializer;
	@Value("${spring.kafka.consumer.valueDeserializer:org.apache.kafka.common.serialization.StringDeserializer}")
	private String valueDeserializer;
	@Value("${spring.kafka.schemaRegistryUrl:#{null}}")
	private String schemaRegistryUrl;

	@PostConstruct
	public void initConfig() {
		props.put(org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaHost);
		props.put("key.deserializer", keyDeserializer);
		props.put("value.deserializer", valueDeserializer);
		if (StringUtils.isNotBlank(groupId)) {
			props.put(org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG, groupId);
		}
		if (StringUtils.isNotBlank(enableAutoCommit)) {
			props.put(org.apache.kafka.clients.consumer.ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enableAutoCommit);
		}
		if (StringUtils.isNotBlank(autoCommitIntervalMs)) {
			props.put(org.apache.kafka.clients.consumer.ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, autoCommitIntervalMs);
		}
		if (StringUtils.isNotBlank(autoOffsetReset)) {
			props.put(org.apache.kafka.clients.consumer.ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
		}
		if (StringUtils.isNotBlank(schemaRegistryUrl)) {
			props.put("schema.registry.url", schemaRegistryUrl);
		}
	}
	public Map<String, Object> getPropsMap() {
		return props.entrySet().stream().collect(
				Collectors.toMap(
						e -> e.getKey().toString(),
						e -> e.getValue().toString()
				)
		);
	}
}
