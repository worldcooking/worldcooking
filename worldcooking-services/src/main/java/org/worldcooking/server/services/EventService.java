package org.worldcooking.server.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.worldcooking.server.dao.impl.EventDAOImpl;
import org.worldcooking.server.dao.impl.MultiEntitiesHibernateDAOImpl;
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

	@Autowired
	private MultiEntitiesHibernateDAOImpl dao;

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

	public void resetDb() {
		try {
			eventDao.resetDb();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Event e = new Event();
		e.setName("Worldcooking Peru");
		e.setDescription("Up to 38 persons will share a peruvian meal in the restaurant La soupe au Caillou.<br>"
				+ "Our chef will be Nidia Torres!<br>"
				+ "<br>"
				+ "To participate in this event you must book and pay in advance.<br>"
				+ "The price for the meal is 15 € per person. This amount is used entirely to cover the cost of the evening.<br>"
				+ "<br>"
				+ "This year we ask each person to help. When registering you have to choose a task from the following ones:<br>"
				+ "- Cooking with Nidia from 4pm"
				+ "<br>- Set the table"
				+ "<br>- Doing the dishes" + "<br>- Cleaning the room<br>");
		e.setMaxParticipants(36);
		dao.makePersistent(e);

		Task t1 = new Task();
		t1.setName("Chef");
		t1.setNbMax(1);
		t1.setPricePerParticipant(0d);
		e.addAvailableTask(t1);
		dao.makePersistent(t1);

		Participant p1 = new Participant();
		p1.setName("Nidia Torres");
		t1.addParticipant(p1);
		dao.makePersistent(p1);

		Task t2 = new Task();
		t2.setName("Cooking");
		t2.setNbMax(7);
		t2.setPricePerParticipant(15d);
		e.addAvailableTask(t2);
		dao.makePersistent(t2);

		Participant p2 = new Participant();
		p2.setName("Matthieu Gaudet");
		t2.addParticipant(p2);
		dao.makePersistent(p2);

		Task t3 = new Task();
		t3.setName("Setting the table");
		t3.setNbMax(10);
		t3.setPricePerParticipant(15d);
		e.addAvailableTask(t3);
		dao.makePersistent(t3);

		Participant p3 = new Participant();
		p3.setName("Maya Rouvneska");
		t3.addParticipant(p3);
		dao.makePersistent(p3);

		Task t4 = new Task();
		t4.setName("Doing the dishes");
		t4.setNbMax(9);
		t4.setPricePerParticipant(15d);
		e.addAvailableTask(t4);
		dao.makePersistent(t4);

		Participant p4 = new Participant();
		p4.setName("Nicolas Toublanc");
		t4.addParticipant(p4);
		dao.makePersistent(p4);

		Task t5 = new Task();
		t5.setName("Cleaning the room");
		t5.setNbMax(9);
		t5.setPricePerParticipant(15d);
		e.addAvailableTask(t5);
		dao.makePersistent(t5);

		Participant p5 = new Participant();
		p5.setName("Nicolas Gruyer");
		t5.addParticipant(p5);
		dao.makePersistent(p5);

		Participant p6 = new Participant();
		p6.setName("Peter Tosh");
		t5.addParticipant(p6);
		dao.makePersistent(p6);

		Subscription s0 = new Subscription();
		s0.setValidate(true);
		s0.addParticipant(p1);
		s0.addParticipant(p2);
		s0.addParticipant(p3);
		s0.addParticipant(p4);
		s0.addParticipant(p5);
		e.addSubscription(s0);
		dao.makePersistent(s0);

		Subscription s1 = new Subscription();
		s1.setValidate(false);
		s1.addParticipant(p6);
		e.addSubscription(s1);
		dao.makePersistent(s1);

		dao.makePersistent(e);
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
