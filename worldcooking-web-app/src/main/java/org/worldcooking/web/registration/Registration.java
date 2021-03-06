package org.worldcooking.web.registration;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.worldcooking.validator.NonEmptyListConstraint;

public class Registration {

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
	 * List of selected tasks (ordered by participant).
	 */
	@NotEmpty
	private List<Long> participantTasks = new ArrayList<Long>();

	/**
	 * List of participants names.
	 */
	@NotEmpty
	@NonEmptyListConstraint(message = "Please specify your name")
	private List<String> participantsNames = new ArrayList<String>();

	public Registration() {
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public List<Long> getParticipantTasks() {
		return participantTasks;
	}

	public void setParticipantTasks(List<Long> participantTasks) {
		this.participantTasks = participantTasks;
	}

	public List<String> getParticipantsNames() {
		return participantsNames;
	}

	public void setParticipantsNames(List<String> participantsNames) {
		this.participantsNames = participantsNames;
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

}
