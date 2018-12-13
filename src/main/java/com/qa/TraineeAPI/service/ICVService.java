package com.qa.TraineeAPI.service;

import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.qa.persistence.domain.CV;
import com.qa.persistence.domain.Trainee;

public interface ICVService {

	String uploadFile(MultipartFile cvDoc, String traineeUserName);

	String uploadFile(CV cvDoc, String traineeUserName);

	Optional<CV> getCV(Long cvID);

	Iterable<CV> getAllCVs();

	Iterable<CV> getCVsForTrainee(String traineeUsername);

	Iterable<CV> getCVsForTrainee(Trainee trainee);

	String deleteCV(Long cvID);

}
