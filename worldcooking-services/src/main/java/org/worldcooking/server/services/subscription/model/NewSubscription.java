package org.worldcooking.server.services.subscription.model;

import java.util.ArrayList;
import java.util.List;

public class NewSubscription {
	private Long eventId;
	private NewSubscriber subscriber;
	private List<NewParticipant> participants;

	private NewSubscriptionPaymentMode paymentMode;

	/**
	 * Personne a qui le paiement est effectué.
	 */
	private String paymentTarget;

	public NewSubscription() {
		super();
		this.participants = new ArrayList<NewParticipant>();
	}

	public NewSubscription configureWithPaypalPayment(Long eventId,
			String subscriberEmailAddress) {
		this.eventId = eventId;
		this.subscriber = new NewSubscriber(subscriberEmailAddress);
		paymentMode = NewSubscriptionPaymentMode.PAYPAL;
		return this;
	}

	public NewSubscription configureWithManualPayment(Long eventId,
			String subscriberEmailAddress, String paymentTarget) {
		this.eventId = eventId;
		this.subscriber = new NewSubscriber(subscriberEmailAddress);
		paymentMode = NewSubscriptionPaymentMode.MANUAL;
		this.paymentTarget = paymentTarget;
		return this;
	}

	public NewSubscription addParticipant(String name, Long taskId) {
		this.participants.add(new NewParticipant(name, taskId));
		return this;
	}

	public Long getEventId() {
		return eventId;
	}

	public NewSubscriber getSubscriber() {
		return subscriber;
	}

	public List<NewParticipant> getParticipants() {
		return participants;
	}

	public NewSubscriptionPaymentMode getPaymentMode() {
		return paymentMode;
	}

	public String getPaymentTarget() {
		return paymentTarget;
	}
}
