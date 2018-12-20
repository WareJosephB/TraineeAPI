package com.qa.service;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.qa.persistence.domain.CV;

public interface ICVService {

	String uploadFile(MultipartFile cvDoc, String traineeUserName);

	String uploadFile(CV cvDoc, String traineeUserName);
	
	ResponseEntity<ByteArrayResource> downloadCv(Long id);

	Iterable<CV> getAllCVs();

	Iterable<CV> getCVsForTrainee(String traineeUsername);
	
	String deleteCV(Long cvID);

	ResponseEntity<ByteArrayResource> downloadFile(Long cvID);

	String getAllCVs(String username);

	ResponseEntity<ByteArrayResource> getCv(Long id);

}
