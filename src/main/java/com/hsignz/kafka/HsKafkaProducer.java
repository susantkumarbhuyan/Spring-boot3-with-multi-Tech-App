package com.hsignz.kafka;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.hsignz.common.classes.DemoKafkaEvent;
import com.hsignz.common.constant.KafkaConstants;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class HsKafkaProducer {

	@Autowired
	private KafkaTemplate<String, DemoKafkaEvent> kafkaTemplate;

	public String produceEvent(DemoKafkaEvent demoKafkaEvent) {
		String response = "Produced Event Success";
		// create message
		Message<DemoKafkaEvent> message = MessageBuilder.withPayload(demoKafkaEvent)
				.setHeader(KafkaHeaders.TOPIC, KafkaConstants.TOPIC_NAME).build();
		CompletableFuture<SendResult<String, DemoKafkaEvent>> future = kafkaTemplate.send(message);
		future.whenComplete((result, ex) -> {
			if (ex == null) {
				log.debug("Sent message=[ {} ] with offset=[ {} ]", message, result.getRecordMetadata().offset());
			} else {
				log.error("Unable to send message=[ {} ] due to : {}", message, ex.getMessage());
			}
		});
		return response;
	}
}
