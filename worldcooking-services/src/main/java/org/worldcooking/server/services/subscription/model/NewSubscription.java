package org.worldcooking.server.services.subscription.model;

import java.util.ArrayList;
import java.util.List;

public class NewSubscription {
	private Long eventId;
	private NewSubscriber subscriber;

	private List<NewParticipant> additionalParticipants;

	private NewSubscriptionPaymentMode paymentMode;

	/**
	 * Personne a qui le paiement est effectué.
	 */
	private String paymentTarget;

	public NewSubscription() {
		super();
		this.additionalParticipants = new ArrayList<NewParticipant>();
	}

	public NewSubscription configureWithPaypalPayment(Long eventId,
			String subscriberEmailAddress, NewParticipant subscriberParticipant) {
		this.eventId = eventId;
		this.subscriber = new NewSubscriber(subscriberEmailAddress,
				subscriberParticipant);
		paymentMode = NewSubscriptionPaymentMode.PAYPAL;
		return this;
	}

	public NewSubscription configureWithManualPayment(Long eventId,
			String subscriberEmailAddress, String paymentTarget,
			NewParticipant subscriberParticipant) {
		this.eventId = eventId;
		this.subscriber = new NewSubscriber(subscriberEmailAddress,
				subscriberParticipant);
		paymentMode = NewSubscriptionPaymentMode.MANUAL;
		this.paymentTarget = paymentTarget;
		return this;
	}

	public NewSubscription addParticipant(String name, Long taskId) {
		this.additionalParticipants.add(new NewParticipant(name, taskId));
		return this;
	}

	public Long getEventId() {
		return eventId;
	}

	public NewSubscriber getSubscriber() {
		return subscriber;
	}

	public List<NewParticipant> getAdditionalParticipants() {
		return additionalParticipants;
	}

	public NewSubscriptionPaymentMode getPaymentMode() {
		return paymentMode;
	}

	public String getPaymentTarget() {
		return paymentTarget;
	}
}
