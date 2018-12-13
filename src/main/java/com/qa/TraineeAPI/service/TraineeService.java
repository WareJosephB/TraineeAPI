package com.qa.TraineeAPI.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qa.TraineeAPI.util.Producer;
import com.qa.persistence.domain.Trainee;
import com.qa.persistence.domain.User;
import com.qa.persistence.domain.UserRequest;
import com.qa.persistence.domain.UserRequest.requestType;

@Component
public class TraineeService implements ITraineeService {

	@Autowired
	private Producer producer;

	public String create(Trainee trainee) {
		trainee.setType("Trainee");
		UserRequest thisRequest = new UserRequest(trainee);
		thisRequest.setHowToAct(requestType.CREATE);
		return producer.produceMessage(thisRequest);
	}

	public Optional<Trainee> get(String userName) {
		User dummyUser = new Trainee(userName);
		UserRequest thisRequest = new UserRequest(dummyUser);
		thisRequest.setHowToAct(requestType.READ);
		return producer.produceTrainee(thisRequest);
	}

	public Iterable<Trainee> getAll() {
		UserRequest thisRequest = new UserRequest();
		thisRequest.setHowToAct(requestType.READALL);
		return producer.produceTrainees(thisRequest);
	}

	public String delete(String userName) {
		User dummyUser = new Trainee(userName);
		UserRequest thisRequest = new UserRequest(dummyUser);
		thisRequest.setHowToAct(requestType.DELETE);
		return producer.produceMessage(thisRequest);

	}

	public String update(String userName, Trainee updatedTrainee) {
		User dummyUser = new Trainee(userName);
		UserRequest thisRequest = new UserRequest(dummyUser);
		thisRequest.setHowToAct(requestType.UPDATE);
		return producer.produceMessage(thisRequest);
	}

}
