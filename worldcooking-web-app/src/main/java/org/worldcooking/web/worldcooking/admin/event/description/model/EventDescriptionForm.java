package org.worldcooking.web.worldcooking.admin.event.description.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class EventDescriptionForm {

	/**
	 * Event id.
	 */
	@NotNull
	private Long eventId;

	/**
	 * Event name.
	 */
	@NotEmpty(message = "Please specify the event name")
	private String eventName;

	@DateTimeFormat(iso = ISO.DATE)
	private Date date;

	@DateTimeFormat(pattern = "HH:mm")
	private Date time;

	/**
	 * Event place id.
	 */
	private Long placeId;

	private String eventRegistrationStatus;

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String name) {
		this.eventName = name;
	}

	public Long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Long placeId) {
		this.placeId = placeId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getEventRegistrationStatus() {
		return eventRegistrationStatus;
	}

	public void setEventRegistrationStatus(String registrationStatus) {
		this.eventRegistrationStatus = registrationStatus;
	}

}
