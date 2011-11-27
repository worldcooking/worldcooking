package org.worldcooking.server.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.worldcooking.server.dao.GenericDAO;
import org.worldcooking.server.exception.EntityIdNotFountException;

/**
 * Based on http://community.jboss.org/docs/DOC-13955
 * 
 * @param <T>
 *            entity type
 * @param <ID>
 *            primary key
 */
public abstract class GenericHibernateDAOImpl<T, ID extends Serializable>
		extends HibernateDaoSupport implements GenericDAO<T, ID> {

	private Class<T> persistentClass;

	@Autowired
	public void init(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@SuppressWarnings({ "unchecked" })
	public GenericHibernateDAOImpl() {
		try {
			persistentClass = (Class<T>) ((ParameterizedType) getClass()
					.getGenericSuperclass()).getActualTypeArguments()[0];
		} catch (ClassCastException e) {
			// can be raised when DAO is inherited twice
			persistentClass = (Class<T>) ((ParameterizedType) getClass()
					.getSuperclass().getGenericSuperclass())
					.getActualTypeArguments()[0];
		}
	}

	public T findById(ID id) throws EntityIdNotFountException {
		return findById(id, false);
	}

	@Override
	public T findById(ID id, boolean lock) throws EntityIdNotFountException {
		T entity;
		if (lock) {
			entity = getHibernateTemplate().get(persistentClass, id,
					LockMode.PESSIMISTIC_WRITE);
		} else {
			entity = getHibernateTemplate().get(persistentClass, id);
		}
		if (entity == null) {
			// no entity with this id
			throw new EntityIdNotFountException(persistentClass, id);
		}

		return entity;
	}

	protected List<?> findByQuery(String queryString, Object... values) {
		return getHibernateTemplate().find(queryString, values);
	}

	@Override
	public List<T> findAll() {
		return findByCriteria();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findByExample(T exampleInstance, String[] excludeProperty) {
		DetachedCriteria crit = DetachedCriteria.forClass(persistentClass);
		Example example = Example.create(exampleInstance);
		for (String exclude : excludeProperty) {
			example.excludeProperty(exclude);
		}
		crit.add(example);
		return getHibernateTemplate().findByCriteria(crit);
	}

	@Override
	public T saveOrUpdate(T entity) {
		getHibernateTemplate().saveOrUpdate(entity);
		return entity;
	}

	@Override
	public void delete(T entity) {
		getHibernateTemplate().delete(entity);
	}

	/**
	 * Use this inside subclasses as a convenience method.
	 */
	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(Criterion... detachedCriterias) {
		DetachedCriteria crit = DetachedCriteria.forClass(persistentClass);
		for (Criterion c : detachedCriterias) {
			crit.add(c);
		}
		return getHibernateTemplate().findByCriteria(crit);
	}

	protected TreeSet<T> toTreeSet(List<T> eList) {
		return new TreeSet<T>(eList);
	}

	protected TreeSet<T> toTreeSet(List<T> eList,
			Comparator<? super T> comparator) {
		// TODO is it the best way to manage unicity?
		TreeSet<T> treeSet = new TreeSet<T>(comparator);
		treeSet.addAll(eList);
		return treeSet;
	}

}