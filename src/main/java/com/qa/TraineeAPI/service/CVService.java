package com.qa.TraineeAPI.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.qa.TraineeAPI.util.Producer;
import com.qa.persistence.domain.CV;
import com.qa.persistence.domain.CVRequest;
import com.qa.persistence.domain.Trainee;
import com.qa.persistence.domain.UserRequest;
import com.qa.persistence.domain.CVRequest.requestType;

@Service
public class CVService implements ICVService {

	@Autowired
	private Producer producer;

	public String uploadFile(CV cv, String traineeUserName) {
		Trainee author = new Trainee(traineeUserName);
		author.setCvList(new ArrayList<CV>());
		author.getCvList().add(cv);
		UserRequest request = new UserRequest();
		request.setUserToAddOrUpdate(author);
		request.setHowToAct(com.qa.persistence.domain.UserRequest.requestType.ADDCV);
		return producer.produceMessage(request);
	}

	public String uploadFile(MultipartFile cvDoc, String traineeUserName) {
		CV thisCV = new CV();
		thisCV.setFiles(cvDoc);
		return uploadFile(thisCV, traineeUserName);
	}

	public Iterable<CV> getCVsForTrainee(Trainee trainee) {
		UserRequest request = new UserRequest();
		request.setUserToAddOrUpdate(trainee);
		request.setHowToAct(com.qa.persistence.domain.UserRequest.requestType.READALL);
		return producer.produceCVs(request);
	}

	public Iterable<CV> getCVsForTrainee(String traineeUserName) {
		Trainee thisTrainee = new Trainee(traineeUserName);
		return getCVsForTrainee(thisTrainee);
	}

	public String deleteCV(Long cvID) {
		CVRequest request = new CVRequest();
		request.setcvIDtoActUpon(cvID);
		request.setType(requestType.DELETE);
		return producer.produceMessage(request);
	}

	@Override
	public Optional<CV> getCV(Long cvID) {
		CVRequest request = new CVRequest();
		request.setcvIDtoActUpon(cvID);
		request.setType(requestType.READ);
		return producer.produceCV(request);
	}

	@Override
	public Iterable<CV> getAllCVs() {
		CVRequest request = new CVRequest();
		request.setType(requestType.READALL);
		return producer.produceCVs(request);
	}

}
