package com.qa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qa.persistence.domain.Trainee;
import com.qa.persistence.domain.User;
import com.qa.persistence.domain.UserRequest;
import com.qa.persistence.domain.UserRequest.requestType;
import com.qa.persistence.repository.TraineeRepository;
import com.qa.util.Constants;
import com.qa.util.Producer;

@Component
public class TraineeService implements ITraineeService {

	@Autowired
	private Producer producer;

	@Autowired
	private TraineeRepository repo;

	public String create(Trainee trainee) {
		trainee.setType("Trainee");
		UserRequest thisRequest = new UserRequest(trainee);
		thisRequest.setHowToAct(requestType.CREATE);
		return producer.produceMessage(thisRequest);
	}

	public String delete(String userName) {
		User dummyUser = new Trainee(userName);
		UserRequest thisRequest = new UserRequest(dummyUser);
		thisRequest.setHowToAct(requestType.DELETE);
		producer.produceMessage(thisRequest);
		repo.deleteById(userName);
		return Constants.TRAINEE_DELETED_MESSAGE;

	}

	public String update(String userName, Trainee updatedTrainee) {
		User dummyUser = new Trainee(userName);
		UserRequest thisRequest = new UserRequest(dummyUser);
		thisRequest.setHowToAct(requestType.UPDATE);
		producer.produceMessage(thisRequest);
		Trainee toUpdate = repo.findById(userName).get();
		toUpdate.update(updatedTrainee);
		return Constants.TRAINEE_UPDATED_MESSAGE;

	}

	public String tag(String username, String email) {
		Trainee dummyUser = new Trainee(username);
		dummyUser.tag(email);
		UserRequest thisRequest = new UserRequest(dummyUser);
		thisRequest.setHowToAct(requestType.TAG);
		return producer.produceMessage(thisRequest);
	}

	@Override
	public Optional<Trainee> get(String username) {
		return repo.findById(username);
	}

	@Override
	public Iterable<Trainee> getAll() {
		return repo.findAll();

	}

}
