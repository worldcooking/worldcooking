package org.worldcooking.server.dao;

import org.worldcooking.server.entity.DummyEntity;

/**
 * Plain DAO which provides only {@link org.worldcooking.server.dao.impl.GenericHibernateDAOImpl} methods
 */
@Deprecated
public interface DummyDAO extends GenericDAO<DummyEntity, Long> {
}
