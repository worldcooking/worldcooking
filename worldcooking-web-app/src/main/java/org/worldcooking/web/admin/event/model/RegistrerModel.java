package org.worldcooking.web.admin.event.model;

public class RegistrerModel {
	private ParticipantModel participant;
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String emailAddress) {
		this.email = emailAddress;
	}

	public ParticipantModel getParticipant() {
		return participant;
	}

	public void setParticipant(ParticipantModel participant) {
		this.participant = participant;
	}
}
