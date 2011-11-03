package org.worldcooking.server.services.subscription.model;

public class NewSubscriber {
	private String emailAddress;

	public NewSubscriber(String emailAddress) {
		super();
		this.emailAddress = emailAddress;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
}
