package org.gfoo.textlearnapi;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LearnMessageService {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Value("${amqp.text-learn.exchange}")
	private String exchangeName;

	@Value("${amqp.text-learn.routing.learn}")
	private String learnRoutingKeyName;

	public void sendLearnMessage(LearningForm learningForm) {
		log.debug("send message:", exchangeName, learnRoutingKeyName,
		    learningForm.toString());
		this.rabbitTemplate.convertAndSend(exchangeName, learnRoutingKeyName,
		    learningForm.toString());
	}

}
