package com.qa.service;

import java.util.Optional;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.qa.persistence.domain.CV;

public interface ICVService {

	String uploadFile(MultipartFile cvDoc, String traineeUserName);

	String uploadFile(CV cvDoc, String traineeUserName);

	Optional<CV> getCV(Long cvID);

	Iterable<CV> getAllCVs();

	Iterable<CV> getCVsForTrainee(String traineeUsername);
	
	String deleteCV(Long cvID);

	ResponseEntity<ByteArrayResource> downloadCV(Long cvID);

}
