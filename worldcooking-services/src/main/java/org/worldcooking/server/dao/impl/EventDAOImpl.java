package org.worldcooking.server.dao.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import org.oupsasso.mishk.core.dao.GenericDao;
import org.springframework.stereotype.Repository;
import org.worldcooking.server.entity.event.Event;

/**
 * Plain DAO which provides only
 * {@link org.worldcooking.server.dao.impl.GenericHibernateDAOImpl} methods
 */
@Repository
public class EventDAOImpl extends GenericDao<Event, Long> {

	/**
	 * Method returning the event corresponding to the id parameter. <br/>
	 * The event is completed with all his objects.
	 * 
	 * @param id
	 *            Unique id use to retrieve the Event.
	 * @return The event. Return null if the event is not found.
	 */
	public Event findFullEventById(Long id) {
		Set<Event> eList = findAllFullEvents(id);
		if (eList != null && !eList.isEmpty()) {
			return eList.iterator().next();
		}
		return null;
	}

	public SortedSet<Event> findAllFullEvents() {
		return findAllFullEvents(null);
	}

	@SuppressWarnings("unchecked")
	private SortedSet<Event> findAllFullEvents(Long id) {
		String queryString = "from Event e" + " left join fetch e.availableTasks as t"
				+ " left join fetch e.registrations as s" + " left join fetch s.participants as p";
		List<Event> eList;
		if (id != null) {
			queryString += " where e.id=:eventId";
			eList = getHibernateTemplate().findByNamedParam(queryString, "eventId", id);
		} else {
			eList = getHibernateTemplate().find(queryString);
		}
		return toTreeSet(eList, new Comparator<Event>() {

			@Override
			public int compare(Event e1, Event e2) {
				// TODO use CompareToBuilder
				// TODO compare in more fields to avoid to erase events with
				// identical name
				return e1.getName().compareTo(e2.getName());
			}
		});
	}

	public void resetDb() {
		List<Event> allEvents = findAll();
		getHibernateTemplate().deleteAll(allEvents);
	}

}
