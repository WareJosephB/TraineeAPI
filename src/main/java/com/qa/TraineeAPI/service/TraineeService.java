package com.qa.TraineeAPI.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.qa.TraineeAPI.util.Producer;
import com.qa.persistence.domain.Trainee;
import com.qa.persistence.domain.User;
import com.qa.persistence.domain.UserRequest;
import com.qa.persistence.domain.UserRequest.requestType;
import com.qa.TraineeAPI.util.Constants;

@Component
public class TraineeService implements ITraineeService {

	@Autowired
	private Producer producer;

	@Autowired
	private JmsTemplate jmsTemplate;

	public String create(Trainee trainee) {
		trainee.setType("Trainee");
		UserRequest thisRequest = new UserRequest(trainee);
		thisRequest.setHowToAct(requestType.CREATE);
		producer.produce(thisRequest);
		return Constants.TRAINEE_QUEUED_MESSAGE;
	}

	public Optional<Trainee> get(String userName) {
		User dummyUser = new Trainee(userName);
		UserRequest thisRequest = new UserRequest(dummyUser);
		thisRequest.setHowToAct(requestType.READ);
		producer.produce(thisRequest);
		return (Optional<Trainee>) jmsTemplate.receiveAndConvert(Constants.OUTGOING_TRAINEE_QUEUE_NAME);
	}

	public Iterable<Trainee> getAll() {
		UserRequest thisRequest = new UserRequest();
		thisRequest.setHowToAct(requestType.READALL);
		producer.produce(thisRequest);
		return (Iterable<Trainee>) jmsTemplate.receiveAndConvert(Constants.OUTGOING_TRAINEE_QUEUE_NAME);
	}

	public String delete(String userName) {
		User dummyUser = new Trainee(userName);
		UserRequest thisRequest = new UserRequest(dummyUser);
		thisRequest.setHowToAct(requestType.DELETE);
		producer.produce(thisRequest);
		return Constants.REQUEST_QUEUED_MESSAGE;

	}

	public String update(String userName, Trainee updatedTrainee) {
		User dummyUser = new Trainee(userName);
		UserRequest thisRequest = new UserRequest(dummyUser);
		thisRequest.setHowToAct(requestType.UPDATE);
		producer.produce(thisRequest);
		return Constants.REQUEST_QUEUED_MESSAGE;
	}

}
