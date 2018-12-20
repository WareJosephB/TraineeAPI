package com.qa.service;

import java.util.Optional;

import com.qa.persistence.domain.Trainee;

public interface ITraineeService {

	String create(Trainee trainee);

	Optional<Trainee> get(String username);

	Iterable<Trainee> getAll();

	String update(String username, Trainee updatedDetails);

	String delete(String username);

}
