package com.qa.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class CV {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long _id;
	private String errorMessage;
	@Lob
	private byte[] contents;
	private String fileName;
	private String authorName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public byte[] getContents() {
		return contents;
	}

	public void setContents(byte[] contents) {
		this.contents = contents;
	}

	public CV(String string, byte[] contents, String traineeUsername) {
		this.fileName = string;
		this.contents = contents;
		this.authorName = traineeUsername;
	}

	public CV() {
	}

	public Long getCvID() {
		return _id;
	}

	public void setCvID(Long cvID) {
		this._id = cvID;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
