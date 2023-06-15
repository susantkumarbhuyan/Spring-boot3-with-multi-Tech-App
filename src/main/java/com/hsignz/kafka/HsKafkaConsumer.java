package com.hsignz.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.hsignz.common.classes.DemoKafkaEvent;
import com.hsignz.common.constant.KafkaConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HsKafkaConsumer {

	@KafkaListener(topics = KafkaConstants.TOPIC_NAME, groupId = KafkaConstants.GROUP_ID)
	public void consumeEvent(DemoKafkaEvent demoKafkaEvent) {
		log.debug("Json message recieved -> {}", demoKafkaEvent.toString());
	}
}
