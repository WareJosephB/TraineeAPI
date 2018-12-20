package com.qa.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import com.qa.persistence.domain.CVRequest;
import com.qa.persistence.domain.UserRequest;

@Component
public class Producer {

	@Autowired
	private JmsMessagingTemplate jmsTemplate;

	public void produceTrainee(UserRequest request) {
		jmsTemplate.convertAndSend(Constants.INCOMING_TRAINEE_QUEUE_NAME, request);
	}

	public void produceTrainees(UserRequest request) {
		jmsTemplate.convertAndSend(Constants.INCOMING_TRAINEE_QUEUE_NAME, request);
	}

	public void produceMessage(UserRequest request) {
		jmsTemplate.convertAndSend(Constants.INCOMING_TRAINEE_QUEUE_NAME, request);
	}

	public void produceCV(CVRequest request) {
		jmsTemplate.convertAndSend(Constants.INCOMING_CV_QUEUE_NAME, request);
	}

	public void produceCVs(CVRequest request) {
		jmsTemplate.convertAndSend(Constants.INCOMING_CV_QUEUE_NAME, request);
	}

	public void produceCVs(UserRequest request) {
		jmsTemplate.convertAndSend(Constants.INCOMING_CV_QUEUE_NAME, request);
	}

	public void produceMessage(CVRequest request) {
		jmsTemplate.convertAndSend(Constants.INCOMING_CV_QUEUE_NAME, request);
	}

}
