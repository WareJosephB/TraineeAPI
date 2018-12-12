package com.qa.TraineeAPI.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.qa.persistence.domain.CVRequest;
import com.qa.persistence.domain.UserRequest;

@Component
public class Producer {

	@Autowired
	private JmsTemplate jmsTemplate;

	public String produce(UserRequest request) {
		jmsTemplate.convertAndSend(Constants.INCOMING_TRAINEE_QUEUE_NAME, request);
		return Constants.REQUEST_QUEUED_MESSAGE;
	}

	public String produce(CVRequest request) {
		jmsTemplate.convertAndSend(Constants.INCOMING_CV_QUEUE_NAME, request);
		return Constants.REQUEST_QUEUED_MESSAGE;

	}
}
