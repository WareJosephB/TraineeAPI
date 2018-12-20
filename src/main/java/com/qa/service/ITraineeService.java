package com.qa.service;

import java.util.Optional;

import com.qa.persistence.domain.Trainee;

public interface ITraineeService {

	String create(Trainee trainee);

	String update(String username, Trainee updatedDetails);

	String delete(String username);

	Optional<Trainee> get(String username);

	Iterable<Trainee> getAll();

	String tag(String username, String email);

}
