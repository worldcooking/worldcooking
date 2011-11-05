package org.worldcooking.web.registration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class Registration {

	/**
	 * Event id.
	 */
	@NotNull
	private Long eventId;

	/**
	 * Register email address.
	 */
	@NotEmpty
	@Email
	// make sure emailAddress is not empty
	private String emailAddress;

	@NotEmpty
	private String paymentMode;

	/**
	 * List of selected tasks (ordered by participant).
	 */
	@NotEmpty
	private List<Long> participantTasks = Arrays.asList(2L, 1L, 3L);

	/**
	 * List of participants names.
	 */
	@NotEmpty
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
