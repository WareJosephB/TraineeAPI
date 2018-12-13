package com.qa.persistence.domain;

import org.springframework.web.multipart.MultipartFile;

public class CV {

	private Long cvID;
	private MultipartFile file;
	private String errorMessage;

	public CV(Long cvID, MultipartFile file) {
		this.cvID = cvID;
		this.file = file;
	}

	public CV() {
	}

	public Long getCvID() {
		return cvID;
	}

	public void setCvID(Long cvID) {
		this.cvID = cvID;
	}

	public MultipartFile getFiles() {
		return file;
	}

	public void setFiles(MultipartFile cvDoc) {
		this.file = cvDoc;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
