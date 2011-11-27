package org.worldcooking.server.services.registration.model;


public class NewSubscriber {
	private String emailAddress;

	private NewParticipant subscriberParticipant;

	public NewSubscriber(String emailAddress,
			NewParticipant subscriberParticipant) {
		super();
		this.emailAddress = emailAddress;
		this.subscriberParticipant = subscriberParticipant;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public NewParticipant getSubscriberParticipant() {
		return subscriberParticipant;
	}

	public void setSubscriberParticipant(NewParticipant subscriberParticipant) {
		this.subscriberParticipant = subscriberParticipant;
	}
}
