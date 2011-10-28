package org.worldcooking.server.dao.impl;

import org.springframework.stereotype.Repository;

import org.worldcooking.server.dao.DummyDAO;
import org.worldcooking.server.entity.DummyEntity;

/**
 * Plain DAO which provides only {@link org.worldcooking.server.dao.impl.GenericHibernateDAOImpl} methods
 */
@Repository
public class DummyDAOImpl extends GenericHibernateDAOImpl<DummyEntity, Long> implements DummyDAO {
    
}
