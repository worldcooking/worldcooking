package org.worldcooking.server.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * Based on http://community.jboss.org/docs/DOC-13955
 * 
 * @param <T>
 *            entity type
 * @param <ID>
 *            primary key
 */
@Repository
public class MultiEntitiesHibernateDAOImpl extends HibernateDaoSupport {

	@Autowired
	public void init(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	public MultiEntitiesHibernateDAOImpl() {
	}

	public <T> T makePersistent(T entity) {
		getHibernateTemplate().saveOrUpdate(entity);
		return entity;
	}

}