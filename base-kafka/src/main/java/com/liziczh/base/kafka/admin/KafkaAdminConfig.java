package com.liziczh.base.kafka.admin;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaAdminConfig {
	@Value("${spring.kafka.bootstrap-servers}")
	private String kafkaHost;

	@Bean
	public KafkaAdmin admin() {
		Map<String, Object> configs = new HashMap<>();
		configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaHost);
		KafkaAdmin admin = new KafkaAdmin(configs);
		admin.setFatalIfBrokerNotAvailable(true);
		return admin;
	}
}
