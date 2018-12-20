package com.qa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qa.persistence.domain.Trainee;
import com.qa.persistence.domain.User;
import com.qa.persistence.domain.UserRequest;
import com.qa.persistence.domain.UserRequest.requestType;
import com.qa.persistence.repository.UserRepository;
import com.qa.util.Constants;
import com.qa.util.Producer;

@Component
public class TraineeService implements ITraineeService {

	@Autowired
	private Producer producer;

	@Autowired
	private UserRepository repo;

	public String create(Trainee trainee) {
		trainee.setType("Trainee");
		UserRequest thisRequest = new UserRequest(trainee);
		thisRequest.setHowToAct(requestType.CREATE);
		producer.produceMessage(thisRequest);
		repo.save(trainee);
		return Constants.USER_ADDED_MESSAGE;
	}

	public Optional<Trainee> get(String userName) {
		return repo.findById(userName);
	}

	public Iterable<Trainee> getAll() {
		UserRequest thisRequest = new UserRequest();
		thisRequest.setHowToAct(requestType.READALL);
		return repo.findAll();
	}

	public String delete(String userName) {
		User dummyUser = new Trainee(userName);
		UserRequest thisRequest = new UserRequest(dummyUser);
		thisRequest.setHowToAct(requestType.DELETE);
		producer.produceMessage(thisRequest);
		repo.deleteById(userName);
		return Constants.USER_DELETED_MESSAGE;

	}

	public String update(String userName, Trainee updatedTrainee) {
		User dummyUser = new Trainee(userName);
		UserRequest thisRequest = new UserRequest(dummyUser);
		thisRequest.setHowToAct(requestType.UPDATE);
		producer.produceMessage(thisRequest);
		Trainee steve = repo.findById(userName).get();
		steve.update(updatedTrainee);
		return Constants.USER_UPDATE_MESSAGE;
	}

}
