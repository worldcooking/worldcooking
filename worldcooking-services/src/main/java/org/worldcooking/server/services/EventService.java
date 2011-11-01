package org.worldcooking.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.worldcooking.server.dao.impl.EventDAOImpl;
import org.worldcooking.server.entity.event.Event;

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
}
