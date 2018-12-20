package com.qa.persistence.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Trainee extends User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String username;
	private boolean currentlyHired;
	private boolean tagged;
	private ArrayList<String> emails;

	public Trainee(String userName) {
		super(userName);
		this.type = "Trainee";
		this.emails = new ArrayList<>();
	}

	public Trainee() {
		this.type = "Trainee";
		this.emails = new ArrayList<>();
	}

	public boolean isCurrentlyHired() {
		return currentlyHired;
	}

	public void setCurrentlyHired(boolean currentlyHired) {
		this.currentlyHired = currentlyHired;
	}

	public boolean isTagged() {
		return tagged;
	}

	public void tag(String email) {
		this.emails.add(email);
	}

	public void setTagged(boolean flagged) {
		this.tagged = flagged;
	}

	public List<String> getEmails() {
		return emails;
	}

	public void setEmails(List<String> emails) {
		this.emails = (ArrayList<String>) emails;
	}

	public void update(Trainee updatedTrainee) {
		this.setFirstName(updatedTrainee.getFirstName());
		this.setLastName(updatedTrainee.getLastName());
		this.setCurrentlyHired(updatedTrainee.isCurrentlyHired());
		this.setTagged(updatedTrainee.isTagged());
	}

}