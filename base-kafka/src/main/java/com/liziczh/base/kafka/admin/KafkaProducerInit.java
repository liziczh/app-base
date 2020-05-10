package com.liziczh.base.kafka.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.ProducerListener;

import com.liziczh.base.kafka.config.AppProducerConfig;

@Configuration
public class KafkaProducerInit {
	private static final Logger logger = LoggerFactory.getLogger(KafkaProducerInit.class);
	@Value("${spring.kafka.producer.createTopics:#{null}}")
	private String createTopics;
	@Value("${spring.kafka.producer.numOfPartition:#{null}}")
	private String numOfPartition;
	@Value("${spring.kafka.producer.replicationFactor:#{null}}")
	private String replicationFactor;
	@Autowired
	private KafkaAdmin admin;
	@Autowired
	private AppProducerConfig producerConfig;
	@Autowired
	private ProducerListener<String, String> producerListener;

	@PostConstruct
	private void createTopic() {
		if (StringUtils.isNoneBlank(createTopics, numOfPartition, replicationFactor)) {
			String[] topics = createTopics.split(",");
			AdminClient client = AdminClient.create(admin.getConfig());
			List<NewTopic> newTopics = new ArrayList<NewTopic>();
			for (String topicName : topics) {
				logger.info("create topic :" + topicName);
				newTopics.add(new NewTopic(topicName, Integer.parseInt(numOfPartition), Short.parseShort(replicationFactor)));
			}
			client.createTopics(newTopics);
			client.close();
		}
	}
	@Bean
	public ProducerFactory<String, String> producerFactory() {
		return new DefaultKafkaProducerFactory<>(producerConfig.getPropsMap());
	}
	@Bean
	public KafkaTemplate<String, String> kafkaTemplate() {
		KafkaTemplate<String, String> kafkaTemplate = new KafkaTemplate<String, String>(producerFactory());
		kafkaTemplate.setProducerListener(producerListener);
		return kafkaTemplate;
	}
}
