package org.worldcooking.server.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.worldcooking.server.entity.event.Event;

/**
 * Plain DAO which provides only
 * {@link org.worldcooking.server.dao.impl.GenericHibernateDAOImpl} methods
 */
@Repository
public class EventDAOImpl extends GenericHibernateDAOImpl<Event, Long> {

	@SuppressWarnings("unchecked")
	public Event findFullEventById(Long id) {
		List<Event> eList = getHibernateTemplate().findByNamedParam(
				"from Event e join fetch e.availableTasks as t "
						+ "left join fetch e.subscriptions as s "
						+ "left join fetch s.participants as p"
						+ " where e.id=:eventId", "eventId", id);
		if (eList != null && !eList.isEmpty()) {
			return eList.get(0);
		}
		return null;
	}

}
