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
import org.worldcooking.server.dao.impl.PlaceDAOImpl;
import org.worldcooking.server.dao.impl.TaskDAOImpl;
import org.worldcooking.server.entity.event.Event;
import org.worldcooking.server.entity.event.Registration;
import org.worldcooking.server.entity.event.Task;
import org.worldcooking.server.entity.people.Participant;
import org.worldcooking.server.entity.place.Place;

@Repository
public class EventService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private EventDAOImpl eventDao;

	@Autowired
	private TaskDAOImpl taskDao;

	@Autowired
	private PlaceDAOImpl placeDAOImpl;

	public void setEventDao(EventDAOImpl eventDao) {
		this.eventDao = eventDao;
	}

	public Event getFullEventById(Long id) {
		return eventDao.findFullEventById(id);
	}

	public Event findById(Long id) throws EntityIdNotFountException {
		return eventDao.findById(id);
	}

	public Event findByReference(String reference) throws EntityIdNotFountException {
		return eventDao.findByReference(reference);
	}

	/**
	 * @return
	 */
	public Event getLastEvent() {
		return eventDao.findFullLastEvent();
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

	public List<Place> findAllPlacesOrderByName(Long eventId) {
		return placeDAOImpl.findAllOrderByName(eventId);
	}

	public void saveOrUpdate(Object entity) {
		eventDao.saveOrUpdate(entity);
	}

}
