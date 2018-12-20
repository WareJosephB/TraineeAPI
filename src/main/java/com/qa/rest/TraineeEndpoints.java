package com.qa.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.persistence.domain.CV;
import com.qa.persistence.domain.Trainee;
import com.qa.service.ICVService;
import com.qa.service.ITraineeService;

@RequestMapping("${URL.base}")
@CrossOrigin
@RestController
public class TraineeEndpoints implements EndpointInterface<Trainee> {

	@Autowired
	private ITraineeService traineeServ;

	@Autowired
	private ICVService cvService;

	

	@PostMapping("${URL.method.Trainee.add}")
	public String add(@RequestBody Trainee trainee) {
		return traineeServ.create(trainee);
	}
	
	@PutMapping("${URL.method.Trainee.tag}/{username}")
	public String toggleTag(@PathVariable String username, @RequestBody String email ) {
		return traineeServ.tag(username, email);
	}

	@GetMapping("${URL.method.Trainee.getByUserName}/{username}")
	public Optional<Trainee> get(@PathVariable("username") String username) {
		return traineeServ.get(username);
	}

	@Override
	@GetMapping("${URL.method.Trainee.getAll}")
	public Iterable<Trainee> getAll() {
		return traineeServ.getAll();
	}

	@PutMapping("${URL.method.Trainee.update}/{username}")
	public String update(@PathVariable("username") String username, @RequestBody Trainee trainee) {
		return traineeServ.update(username, trainee);
	}

	@Override
	@DeleteMapping("${URL.method.Trainee.delete}/{username}")
	public String delete(@PathVariable("username") String username) {
		return traineeServ.delete(username);
	}
}
