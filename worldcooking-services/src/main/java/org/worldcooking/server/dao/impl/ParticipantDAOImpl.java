package org.worldcooking.server.dao.impl;

import org.oupsasso.mishk.core.dao.GenericDao;
import org.springframework.stereotype.Repository;
import org.worldcooking.server.entity.people.Participant;

/**
 * Plain DAO which provides only
 * {@link org.worldcooking.server.dao.impl.GenericHibernateDAOImpl} methods
 */
@Repository
public class ParticipantDAOImpl extends GenericDao<Participant, Long> {

}
