package org.worldcooking.server.dao.impl;

import java.util.Collection;

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

	/**
	 * Use saveOrUpdate instead
	 * 
	 * @param entity
	 * @return
	 */
	@Deprecated
	public <T> T makePersistent(T entity) {
		saveOrUpdate(entity);
		return entity;
	}

	public void saveOrUpdate(Object entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	public void saveOrUpdateAll(Collection<?> entity) {
		getHibernateTemplate().saveOrUpdateAll(entity);
	}
}