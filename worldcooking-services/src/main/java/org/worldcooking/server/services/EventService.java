package org.worldcooking.server.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.worldcooking.server.dao.impl.EventDAOImpl;
import org.worldcooking.server.entity.event.Event;
import org.worldcooking.server.entity.event.Subscription;
import org.worldcooking.server.entity.people.Participant;

@Repository
public class EventService {
	@Autowired
	private EventDAOImpl eventDao;

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
		List<Participant> waitingParticipants = new ArrayList<Participant>();

		Event event = eventDao.findFullEventById(id);
		return getWaitingParticipants(waitingParticipants, event);
	}

	/**
	 * @param waitingParticipants
	 * @param event
	 * @return
	 */
	private List<Participant> getWaitingParticipants(
			List<Participant> waitingParticipants, Event event) {
		List<Subscription> subscriptions = event.getSubscriptions();
		for (Subscription subscription : subscriptions) {
			if (!subscription.getValidate()) {
				waitingParticipants.addAll(subscription.getParticipants());
			}
		}

		return waitingParticipants;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public List<Participant> getValidatedParticipants(Long id) {
		List<Participant> validatedParticipants = new ArrayList<Participant>();

		return validatedParticipants;
	}
}
