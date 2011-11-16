package org.worldcooking.server.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.worldcooking.server.entity.event.Task;

/**
 * Plain DAO which provides only
 * {@link org.worldcooking.server.dao.impl.GenericHibernateDAOImpl} methods
 */
@Repository
public class TaskDAOImpl extends GenericHibernateDAOImpl<Task, Long> {
	/**
	 * Method returning the available tasks corresponding to the event id
	 * parameter. <br/>
	 * 
	 * @param eventId
	 *            Unique id use to retrieve the Event.
	 * @return The list of available tasks.
	 */
	@SuppressWarnings("unchecked")
	public List<Task> getAvailableTasks(Long eventId) {
		List<Task> tasks = getHibernateTemplate()
				.findByNamedParam(
						"from Task t"
								+ " where t.event.id=:eventId"
								+ " and t.nbMax > (select count(p)"
								+ " from Participant p where p.task.id = t.id and p.registration.validate = true)"
								+ " order by t.name", "eventId", eventId);
		return tasks;
	}
}
