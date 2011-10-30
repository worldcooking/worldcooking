package org.worldcooking.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.worldcooking.server.dao.impl.EventDAOImpl;
import org.worldcooking.server.entity.Event;


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

	public List<Event> getAllEvents() {
		
		Event e1 = new Event();
		e1.setName("Worldcooking 1");
		eventDao.makePersistent(e1);
		
		Event e2 = new Event();
		e2.setName("Worldcooking 2");
		eventDao.makePersistent(e2);
		
		return eventDao.findAll();
	}
}
