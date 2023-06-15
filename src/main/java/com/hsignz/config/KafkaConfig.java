package com.hsignz.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.hsignz.common.classes.DemoKafkaEvent;
import com.hsignz.common.constant.KafkaConstants;

@Configuration
public class KafkaConfig {

	// Kafka Topic Configuration

	@Bean
	NewTopic topic() {
		return TopicBuilder.name(KafkaConstants.TOPIC_NAME).build();
	}

	@Bean
	KafkaAdmin kafkaAdmin() {
		Map<String, Object> configs = new HashMap<>();
		configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.BOOTSTRAP_ADDRESS);
		return new KafkaAdmin(configs);
	}

	// Kafka Producer Configuration

	@Bean
	ProducerFactory<String, DemoKafkaEvent> producerFactory() {
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.BOOTSTRAP_ADDRESS);
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		configProps.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, "20971520");
		return new DefaultKafkaProducerFactory<>(configProps, new StringSerializer(), new JsonSerializer<>());
	}

	@Bean
	KafkaTemplate<String, DemoKafkaEvent> jsonKafkaTemplate() {
		return new KafkaTemplate<String, DemoKafkaEvent>(producerFactory());
	}

	// Kafka Consumer Configuration

	@Bean
	ConsumerFactory<String, DemoKafkaEvent> consumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.BOOTSTRAP_ADDRESS);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaConstants.GROUP_ID);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>());
	}

	@Bean
	ConcurrentKafkaListenerContainerFactory<String, DemoKafkaEvent> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, DemoKafkaEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
		factory.setConcurrency(3);
		 factory.setBatchListener(false);
		factory.getContainerProperties().setPollTimeout(3000);
		factory.getContainerProperties().setIdleBetweenPolls(1000L); 
		return factory;
	}
//	@Bean("singleKafkaListenerContainerFactoryManualCommit")
//	 ConcurrentKafkaListenerContainerFactory<?, ?> singleKafkaListenerContainerFactoryManualCommit(ConcurrentKafkaListenerContainerFactoryConfigurer configurer, ConsumerFactory<Object, Object> kafkaConsumerFactory) {
//	    ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
//	    configurer.configure(factory, kafkaConsumerFactory);
//	    factory.setConcurrency(3);
//		factory.getContainerProperties().setPollTimeout(3000);
//	    factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
//	    factory.setBatchListener(false);
//	    factory.getContainerProperties().setIdleBetweenPolls(3000L); // <- this is basically it
//	    return factory;
//	}
}
