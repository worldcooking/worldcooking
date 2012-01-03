package org.worldcooking.web.worldcooking.admin.event.participants.model;

public class WorldcookingAdminEventRegistrer {
	private WorldcookingAdminEventParticipant participant;
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String emailAddress) {
		this.email = emailAddress;
	}

	public WorldcookingAdminEventParticipant getParticipant() {
		return participant;
	}

	public void setParticipant(WorldcookingAdminEventParticipant participant) {
		this.participant = participant;
	}
}
