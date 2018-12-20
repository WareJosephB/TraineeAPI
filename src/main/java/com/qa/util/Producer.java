package com.qa.util;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import com.qa.persistence.domain.CV;
import com.qa.persistence.domain.CVRequest;
import com.qa.persistence.domain.Trainee;
import com.qa.persistence.domain.UserRequest;

@Component
public class Producer {

	@Autowired
	private JmsMessagingTemplate jmsTemplate;

	public Optional<Trainee> produceTrainee(UserRequest request) {
		return jmsTemplate.convertSendAndReceive(Constants.INCOMING_TRAINEE_QUEUE_NAME, request, Optional.class);
	}

	public Iterable<Trainee> produceTrainees(UserRequest request) {
		return jmsTemplate.convertSendAndReceive(Constants.INCOMING_TRAINEE_QUEUE_NAME, request, List.class);
	}

	public String produceMessage(UserRequest request) {
		return jmsTemplate.convertSendAndReceive(Constants.INCOMING_TRAINEE_QUEUE_NAME, request, String.class);
	}

	public void produceMessage(CVRequest request) {
		jmsTemplate.convertAndSend(Constants.INCOMING_CV_QUEUE_NAME, request);
	}

	public Iterable<CV> produceCVs(UserRequest request) {
		return jmsTemplate.convertSendAndReceive(Constants.INCOMING_TRAINEE_QUEUE_NAME, request, List.class);

	}

}
