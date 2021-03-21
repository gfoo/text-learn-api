package org.gfoo.textlearnapi.service;

import org.gfoo.textlearnapi.model.LearningForm;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LearnMessageService {

	@Value("${amqp.text-learn.exchange}")
	private String exchangeName;

	@Value("${amqp.text-learn.routing.learn}")
	private String learnRoutingKeyName;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public boolean isAvailable() {
		return true;
//		return this.rabbitTemplate.isRunning();
	}

	public void sendLearnMessage(LearningForm learningForm)
	    throws LearnMessageException {
		log.debug("send message:", exchangeName, learnRoutingKeyName,
		    learningForm.toString());
		try {
			this.rabbitTemplate.convertAndSend(exchangeName, learnRoutingKeyName,
			    learningForm.toString());
		} catch (AmqpException e) {
			log.error("Error sending message", e);
			throw new LearnMessageException(e);
		}
	}

}
