package org.worldcooking.web.worldcooking.registration.form.model;

import javax.validation.constraints.Min;
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
	@Min(value = 0, message = "Please specify your task")
	private Long subscriberParticipantTask;

	/**
	 * Name of additional participant 1.
	 */
	private String additionalParticipant1Name;

	/**
	 * Task of additional participant 1.
	 */
	private Long additionalParticipant1Task;

	/**
	 * Email address of additional participant 1.
	 */
	private String additionalParticipant1EmailAddress;

	public String getAdditionalParticipant1EmailAddress() {
		return additionalParticipant1EmailAddress;
	}

	public void setAdditionalParticipant1EmailAddress(
			String additionalParticipant1EmailAddress) {
		this.additionalParticipant1EmailAddress = additionalParticipant1EmailAddress;
	}

	public String getAdditionalParticipant2EmailAddress() {
		return additionalParticipant2EmailAddress;
	}

	public void setAdditionalParticipant2EmailAddress(
			String additionalParticipant2EmailAddress) {
		this.additionalParticipant2EmailAddress = additionalParticipant2EmailAddress;
	}

	/**
	 * Name of additional participant 2.
	 */
	private String additionalParticipant2Name;

	/**
	 * Task of additional participant 2.
	 */
	private Long additionalParticipant2Task;

	/**
	 * Email address of additional participant 2.
	 */
	private String additionalParticipant2EmailAddress;

	public String getAdditionalParticipant1Name() {
		return additionalParticipant1Name;
	}

	public void setAdditionalParticipant1Name(String additionalParticipant1Name) {
		this.additionalParticipant1Name = additionalParticipant1Name;
	}

	public Long getAdditionalParticipant1Task() {
		return additionalParticipant1Task;
	}

	public void setAdditionalParticipant1Task(Long additionalParticipant1Task) {
		this.additionalParticipant1Task = additionalParticipant1Task;
	}

	public String getAdditionalParticipant2Name() {
		return additionalParticipant2Name;
	}

	public void setAdditionalParticipant2Name(String additionalParticipant2Name) {
		this.additionalParticipant2Name = additionalParticipant2Name;
	}

	public Long getAdditionalParticipant2Task() {
		return additionalParticipant2Task;
	}

	public void setAdditionalParticipant2Task(Long additionalParticipant2Task) {
		this.additionalParticipant2Task = additionalParticipant2Task;
	}

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

}
