package org.worldcooking.server.dao;

import java.io.Serializable;
import java.util.List;

import org.worldcooking.server.exception.EntityIdNotFountException;

/**
 * Based on http://community.jboss.org/docs/DOC-13955
 * 
 * @param <T>
 *            entity type
 * @param <ID>
 *            primary key
 * 
 * @see org.worldcooking.server.dao.impl.GenericHibernateDAOImpl
 */
public interface GenericDAO<T, ID extends Serializable> {

	T findById(ID id, boolean lock) throws EntityIdNotFountException;

	List<T> findAll();

	List<T> findByExample(T exampleInstance, String[] excludeProperty);

	T makePersistent(T entity);

	void makeTransient(T entity);
}
