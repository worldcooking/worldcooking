package org.worldcooking.server.dao.impl;

import org.springframework.stereotype.Repository;
import org.worldcooking.server.entity.event.Task;

/**
 * Plain DAO which provides only
 * {@link org.worldcooking.server.dao.impl.GenericHibernateDAOImpl} methods
 */
@Repository
public class TaskDAOImpl extends GenericHibernateDAOImpl<Task, Long> {

}
