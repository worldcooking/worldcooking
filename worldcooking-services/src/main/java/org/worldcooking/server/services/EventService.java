package org.worldcooking.server.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import org.oupsasso.mishk.core.dao.exception.EntityIdNotFountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.worldcooking.server.dao.impl.EventDAOImpl;
import org.worldcooking.server.dao.impl.TaskDAOImpl;
import org.worldcooking.server.entity.event.Event;
import org.worldcooking.server.entity.event.Registration;
import org.worldcooking.server.entity.event.Task;
import org.worldcooking.server.entity.people.Participant;

@Repository
public class EventService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private EventDAOImpl eventDao;

	@Autowired
	private TaskDAOImpl taskDao;

	public void setEventDao(EventDAOImpl eventDao) {
		this.eventDao = eventDao;
	}

	public Event getFullEventById(Long id) {
		return eventDao.findFullEventById(id);
	}

	public Event findById(Long id) throws EntityIdNotFountException {
		return eventDao.findById(id);
	}

	/**
	 * @return
	 */
	@Deprecated
	public Event getLastEvent() {
		Event lastEvent = null;
		SortedSet<Event> allEvents = eventDao.findAllFullEvents();
		if (allEvents != null && !allEvents.isEmpty()) {
			lastEvent = allEvents.last();
		}
		return lastEvent;
	}

	/**
	 * Retrieving the list of participants waiting for a validation.
	 * 
	 * @param eventId
	 *            Event id.
	 * @return List of participant waiting for validation of their registration.
	 * 
	 */
	public List<Participant> getWaitingParticipants(Long eventId) {
		Event event = eventDao.findFullEventById(eventId);
		return getWaitingParticipants(event);
	}

	public List<Task> getAvailableTasks(Long eventId) {
		return taskDao.getAvailableTasks(eventId);
	}

	public List<Task> findAllTasks(Long eventId) {
		return taskDao.findAllTasks(eventId);
	}

	public void resetDb() {
		logger.info("The database will be reset now.");
		eventDao.resetDb();

		// create worldcooking Peru
		Event e = new Event();
		e.setName("Worldcooking Peru");
		e.setDescription("Up to 38 persons will share a peruvian meal in the restaurant La soupe au Caillou.<br>"
				+ "Our chef will be Nidia Torres.<br>"
				+ "<br>"
				+ "To participate in this event you must book and pay in advance.<br>"
				+ "The price for the meal is 15 € per person. This amount is used entirely to cover the cost of the evening.<br>"
				+ "<br>"
				+ "This year we ask each person to help. When registering you have to choose a task from the following ones:<br>"
				+ "- Cooking with Nidia from 4pm" + "<br>- Set the table" + "<br>- Doing the dishes"
				+ "<br>- Cleaning the room<br>");
		e.setMaxParticipants(36);
		eventDao.saveOrUpdate(e);

		Task t1 = new Task();
		t1.setName("Chef");
		t1.setNbMax(1);
		t1.setPricePerParticipant(0d);
		e.addAvailableTask(t1);
		eventDao.saveOrUpdate(t1);

		Task t2 = new Task();
		t2.setName("Cooking");
		t2.setNbMax(7);
		t2.setPricePerParticipant(15d);
		e.addAvailableTask(t2);
		eventDao.saveOrUpdate(t2);

		Task t3 = new Task();
		t3.setName("Setting the table");
		t3.setNbMax(10);
		t3.setPricePerParticipant(15d);
		e.addAvailableTask(t3);
		eventDao.saveOrUpdate(t3);

		Task t4 = new Task();
		t4.setName("Doing the dishes");
		t4.setNbMax(9);
		t4.setPricePerParticipant(15d);
		e.addAvailableTask(t4);
		eventDao.saveOrUpdate(t4);

		Task t5 = new Task();
		t5.setName("Cleaning the room");
		t5.setNbMax(9);
		t5.setPricePerParticipant(15d);
		e.addAvailableTask(t5);
		eventDao.saveOrUpdate(t5);

		eventDao.saveOrUpdate(e);
	}

	/**
	 * Get the participants waiting for confirmation.
	 * 
	 * @param event
	 *            The event.
	 * @return The participants waiting for validation.
	 */
	private List<Participant> getWaitingParticipants(Event event) {
		List<Participant> waitingParticipants = new ArrayList<Participant>();
		Set<Registration> registrations = event.getRegistrations();
		for (Registration registration : registrations) {
			if (!registration.getValidate()) {
				waitingParticipants.addAll(registration.getParticipants());
			}
		}

		return waitingParticipants;
	}

	/**
	 * Participants validated
	 * 
	 * @param id
	 *            Id of the event.
	 * @return List of participants validated for the event.
	 */
	public List<Participant> getValidatedParticipants(Long id) {
		Event event = eventDao.findFullEventById(id);

		return getValidatedParticipants(event);
	}

	/**
	 * Get the participants validated for this event.
	 * 
	 * @param event
	 *            The event.
	 * @return List of the validated participants.
	 */
	private List<Participant> getValidatedParticipants(Event event) {
		List<Participant> validatedParticipants = new ArrayList<Participant>();
		Set<Registration> registrations = event.getRegistrations();
		for (Registration registration : registrations) {
			if (!registration.getValidate()) {
				validatedParticipants.addAll(registration.getParticipants());
			}
		}

		return validatedParticipants;
	}

	public List<Event> findAll() {
		return eventDao.findAll();
	}

	public SortedSet<Event> findAllFullEvents() {
		return eventDao.findAllFullEvents();
	}

}
