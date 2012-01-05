package org.worldcooking.web.worldcooking.registration.form.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class WorldcookingRegistrationFormDetail {

	/**
	 * Event id.
	 */
	@NotNull
	private Long eventId;

	/**
	 * Register email address.
	 */
	@NotEmpty(message = "Please specify your email")
	@Email(message = "Please specify a valid email")
	// make sure emailAddress is not empty
	private String emailAddress;

	@NotEmpty
	private String paymentMode;

	/**
	 * Register name
	 */
	@NotEmpty(message = "Please specify your name")
	private String subscriberParticipantName;

	/**
	 * Register task
	 */
	@NotNull(message = "Please specify your task")
	private Long subscriberParticipantTask;

	/**
	 * List of additional participants names.
	 */
	private List<String> additionalParticipantsNames = new ArrayList<String>();

	/**
	 * List of additional participants taks.
	 */
	private List<Long> additionalParticipantsTasks = new ArrayList<Long>();

	/**
	 * List of additional participants adresses.
	 */
	private List<String> additionalParticipantsEmailAddresses = new ArrayList<String>();

	public WorldcookingRegistrationFormDetail() {
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getSubscriberParticipantName() {
		return subscriberParticipantName;
	}

	public void setSubscriberParticipantName(String subscriberParticipantName) {
		this.subscriberParticipantName = subscriberParticipantName;
	}

	public Long getSubscriberParticipantTask() {
		return subscriberParticipantTask;
	}

	public void setSubscriberParticipantTask(Long subscriberParticipantTask) {
		this.subscriberParticipantTask = subscriberParticipantTask;
	}

	public List<String> getAdditionalParticipantsNames() {
		return additionalParticipantsNames;
	}

	public void setAdditionalParticipantsNames(List<String> additionalParticipantsNames) {
		this.additionalParticipantsNames = additionalParticipantsNames;
	}

	public List<Long> getAdditionalParticipantsTasks() {
		return additionalParticipantsTasks;
	}

	public void setAdditionalParticipantsTasks(List<Long> additionalParticipantsTasks) {
		this.additionalParticipantsTasks = additionalParticipantsTasks;
	}

	public List<String> getAdditionalParticipantsEmailAddresses() {
		return additionalParticipantsEmailAddresses;
	}

	public void setAdditionalParticipantsEmailAddresses(List<String> additionalParticipantsEmailAddresses) {
		this.additionalParticipantsEmailAddresses = additionalParticipantsEmailAddresses;
	}

}
