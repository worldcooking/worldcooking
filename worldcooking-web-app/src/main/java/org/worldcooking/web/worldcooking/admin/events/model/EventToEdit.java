package org.worldcooking.web.worldcooking.admin.events.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventToEdit {

	private String reference;

	private String name;

	private Date dateTime;

	private String status;

	private EventToEditPlace place;

	private EventToEditParticipants participants;

	private List<String> languages = new ArrayList<String>();

	public EventToEdit(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public EventToEditPlace getPlace() {
		return place;
	}

	public void setPlace(EventToEditPlace place) {
		this.place = place;
	}

	public EventToEditParticipants getParticipants() {
		return participants;
	}

	public void setParticipants(EventToEditParticipants participants) {
		this.participants = participants;
	}

	public List<String> getLanguages() {
		return languages;
	}

	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}
}
