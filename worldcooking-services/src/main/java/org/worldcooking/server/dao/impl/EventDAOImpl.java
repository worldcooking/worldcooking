package org.worldcooking.server.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.worldcooking.server.entity.event.Event;

/**
 * Plain DAO which provides only
 * {@link org.worldcooking.server.dao.impl.GenericHibernateDAOImpl} methods
 */
@Repository
@ContextConfiguration(locations = { "classpath:spring-dao-context.xml" })
public class EventDAOImpl extends GenericHibernateDAOImpl<Event, Long> {

	@Autowired
	public SessionFactory sessionFactory;

	/**
	 * Method returning the event corresponding to the id parameter. <br/>
	 * The event is completed with all his objects.
	 * 
	 * @param id
	 *            Unique id use to retrieve the Event.
	 * @return The event. Return null if the event is not found.
	 */
	@SuppressWarnings("unchecked")
	public Event findFullEventById(Long id) {
		try {
			List<Event> eList = getHibernateTemplate().findByNamedParam(
					"from Event e join fetch e.availableTasks as t "
							+ "left join fetch e.subscriptions as s "
							+ "left join fetch s.participants as p"
							+ " where e.id=:eventId", "eventId", id);
			if (eList != null && !eList.isEmpty()) {
				return eList.get(0);
			}
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void resetDb() {
		Session openSession = sessionFactory.openSession();
		Event event = findFullEventById(10L);
		if (event != null) {
			openSession.delete(event);
		}
		openSession.close();
	}

}
