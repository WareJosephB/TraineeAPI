package com.qa.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Trainee extends User {

	@Id
	private String username;

	private boolean currentlyHired;
	private boolean flagged;

	public Trainee(String userName) {
		super(userName);
		this.type = "Trainee";
	}

	public Trainee() {
		this.type = "Trainee";
	}

	public boolean isCurrentlyHired() {
		return currentlyHired;
	}

	public void setCurrentlyHired(boolean currentlyHired) {
		this.currentlyHired = currentlyHired;
	}

	public boolean isFlagged() {
		return flagged;
	}

	public void setFlagged(boolean flagged) {
		this.flagged = flagged;
	}

	public void update(Trainee updatedTrainee) {
		this.currentlyHired = updatedTrainee.currentlyHired;
		this.flagged = updatedTrainee.flagged;
		this.setFirstName(updatedTrainee.getFirstName());
		this.setLastName(updatedTrainee.getLastName());

	}

}