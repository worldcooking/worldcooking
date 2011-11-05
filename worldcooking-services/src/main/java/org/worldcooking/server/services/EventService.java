package org.worldcooking.server.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.worldcooking.server.dao.impl.EventDAOImpl;
import org.worldcooking.server.dao.impl.TaskDAOImpl;
import org.worldcooking.server.entity.event.Event;
import org.worldcooking.server.entity.event.Subscription;
import org.worldcooking.server.entity.event.Task;
import org.worldcooking.server.entity.people.Participant;

@Repository
public class EventService {
	@Autowired
	private EventDAOImpl eventDao;

	@Autowired
	private TaskDAOImpl taskDao;

	public EventDAOImpl getEventDao() {
		return eventDao;
	}

	public void setEventDao(EventDAOImpl eventDao) {
		this.eventDao = eventDao;
	}

	public Event getFullEventById(Long id) {
		return eventDao.findFullEventById(id);
	}

	/**
	 * Retrieving the list of participants waiting for a validation.
	 * 
	 * @param id
	 *            Event id.
	 * @return List of participant waiting for validation of their subscription.
	 * 
	 */
	public List<Participant> getWaitingParticipants(Long id) {
		Event event = eventDao.findFullEventById(id);
		return getWaitingParticipants(event);
	}

	public List<Task> getAvailableTasks(Long eventId) {
		return taskDao.getAvailableTasks(eventId);
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
		Set<Subscription> subscriptions = event.getSubscriptions();
		for (Subscription subscription : subscriptions) {
			if (!subscription.getValidate()) {
				waitingParticipants.addAll(subscription.getParticipants());
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
		Set<Subscription> subscriptions = event.getSubscriptions();
		for (Subscription subscription : subscriptions) {
			if (!subscription.getValidate()) {
				validatedParticipants.addAll(subscription.getParticipants());
			}
		}

		return validatedParticipants;
	}

}
