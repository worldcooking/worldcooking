package org.worldcooking.server.dao.impl;

import java.util.List;

import org.oupsasso.mishk.core.dao.GenericDao;
import org.springframework.stereotype.Repository;
import org.worldcooking.server.entity.place.Place;

/**
 * Plain DAO which provides only
 * {@link org.worldcooking.server.dao.impl.GenericHibernateDAOImpl} methods
 */
@Repository
public class PlaceDAOImpl extends GenericDao<Place, Long> {

	@SuppressWarnings("unchecked")
	public List<Place> findAllOrderByName(Long eventId) {
		return getHibernateTemplate().find("from Place p" + " order by p.name");
	}
}
