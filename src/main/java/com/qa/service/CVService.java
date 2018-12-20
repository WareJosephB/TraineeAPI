package com.qa.service;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;

import com.qa.persistence.domain.CV;
import com.qa.persistence.domain.CVRequest;
import com.qa.persistence.domain.Trainee;
import com.qa.persistence.domain.User;
import com.qa.persistence.repository.CVRepository;
import com.qa.persistence.domain.CVRequest.requestType;
import com.qa.persistence.domain.UserRequest;
import com.qa.util.Constants;
import com.qa.util.Producer;

@Service
public class CVService implements ICVService {

	@Autowired
	private Producer producer;

	@Autowired
	private CVRepository repo;

	public String uploadFile(CV cv, String traineeUserName) {
		CVRequest request = new CVRequest();
		cv.setAuthorName(traineeUserName);
		request.setCv(cv);
		producer.produceMessage(request);
		return repo.save(cv).toString();
	}

	public String uploadFile(MultipartFile cvDoc, String traineeUserName) {
		CV temp;
		try {
			temp = new CV(cvDoc.getOriginalFilename(), cvDoc.getBytes(), traineeUserName);
		} catch (IOException e) {
			return Constants.MALFORMED_REQUEST_MESSAGE;
		}
		temp.setAuthorName(traineeUserName);
		CVRequest request = new CVRequest();
		request.setCv(temp);
		request.setType(requestType.CREATE);
		producer.produceMessage(request);
		repo.save(temp);
		return Constants.CV_ADDED_MESSAGE;
	}

	public Iterable<CV> getCVsForTrainee(String traineeName) {
		return repo.findAllByAuthorName(traineeName);
	}

	public String deleteCV(Long cvID) {
		CVRequest request = new CVRequest();
		request.setcvIDtoActUpon(cvID);
		request.setType(requestType.DELETE);
		producer.produceMessage(request);
		repo.deleteById(cvID);
		return Constants.CV_DELETED_MESSAGE;
	}

	@Override
	public ResponseEntity<ByteArrayResource> getCv(Long id) {
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + repo.findById(id).get().getFileName() + "\"")
				.body(new ByteArrayResource(repo.findById(id).get().getContents()));
}

	@Override
	public Iterable<CV> getAllCVs() {
		return repo.findAll();
	}

	public String getAllCVs(String username) {
		User dummyUser = new Trainee(username);
		UserRequest thisRequest = new UserRequest(dummyUser);
		thisRequest.setHowToAct(UserRequest.requestType.READALL);
		return producer.produceMessage(thisRequest);
	}

	@Override
	public ResponseEntity<ByteArrayResource> downloadFile(Long id) {
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + repo.findById(id).get().getFileName() + "\"")
				.body(new ByteArrayResource(repo.findById(id).get().getContents()));
	}

	@Override
	public ResponseEntity<ByteArrayResource> downloadCv(Long id) {
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + repo.findById(id).get().getFileName() + "\"")
				.body(new ByteArrayResource(repo.findById(id).get().getContents()));
	}

}
