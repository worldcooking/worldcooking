package org.worldcooking.server.services.registration.model;

import java.util.ArrayList;
import java.util.List;

public class NewRegistration {
	private Long eventId;
	private NewParticipant subscriber;

	private List<NewParticipant> additionalParticipants;

	private NewRegistrationPaymentMode paymentMode;

	/**
	 * Personne a qui le paiement est effectué.
	 */
	private String paymentTarget;

	public NewRegistration() {
		super();
		this.additionalParticipants = new ArrayList<NewParticipant>();
	}

	public NewRegistration configureWithPaypalPayment(Long eventId, NewParticipant subscriberParticipant) {
		this.eventId = eventId;
		this.subscriber = subscriberParticipant;
		paymentMode = NewRegistrationPaymentMode.PAYPAL;
		return this;
	}

	public NewRegistration configureWithManualPayment(Long eventId, String paymentTarget,
			NewParticipant subscriberParticipant) {
		this.eventId = eventId;
		this.subscriber = subscriberParticipant;
		paymentMode = NewRegistrationPaymentMode.MANUAL;
		this.paymentTarget = paymentTarget;
		return this;
	}

	public NewRegistration addParticipant(String emailAddress, String name, Long taskId) {
		this.additionalParticipants.add(new NewParticipant(emailAddress, name, taskId));
		return this;
	}

	public Long getEventId() {
		return eventId;
	}

	public NewParticipant getSubscriber() {
		return subscriber;
	}

	public List<NewParticipant> getAdditionalParticipants() {
		return additionalParticipants;
	}

	public NewRegistrationPaymentMode getPaymentMode() {
		return paymentMode;
	}

	public String getPaymentTarget() {
		return paymentTarget;
	}
}
