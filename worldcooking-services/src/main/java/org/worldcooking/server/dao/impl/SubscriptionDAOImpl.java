package org.worldcooking.server.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.worldcooking.server.entity.event.Subscription;

/**
 * Plain DAO which provides only
 * {@link org.worldcooking.server.dao.impl.GenericHibernateDAOImpl} methods
 */
@Repository
public class SubscriptionDAOImpl extends
		GenericHibernateDAOImpl<Subscription, Long> {

	/**
	 * Method returning the Subscription corresponding to the subscriptionId
	 * parameter. <br/>
	 * The Subscription is completed with all his objects.
	 * 
	 * @param subscriptionId
	 *            Unique id use to retrieve the Subscription.
	 * @return The Subscription. Return null if the Subscription is not found.
	 */
	public Subscription findFullSubscriptionById(Long subscriptionId) {
		@SuppressWarnings("unchecked")
		List<Subscription> subscriptions = getHibernateTemplate()
				.findByNamedParam(
						"from Subscription s left join fetch s.participants as p "
								+ " where s.id=:subscriptionId",
						"subscriptionId", subscriptionId);
		if (subscriptions != null && !subscriptions.isEmpty()) {
			return subscriptions.get(0);
		}
		return null;
	}

	public List<Subscription> findNonValidatedSubscriptions(Long eventId) {
		@SuppressWarnings("unchecked")
		List<Subscription> subscriptions = getHibernateTemplate()
				.findByNamedParam(
						"from Subscription s left join fetch s.participants as p "
								+ " where s.event.id=:eventId and s.validate=false",
						"eventId", eventId);
		return subscriptions;
	}

	public Long countValidatedParticipants(Long eventId) {
		@SuppressWarnings("unchecked")
		List<Long> counts = getHibernateTemplate()
				.findByNamedParam(
						"select count (p) from Participant p"
								+ " where p.subscription.event.id=:eventId and p.subscription.validate=true",
						"eventId", eventId);
		if (counts != null && counts.size() == 1) {
			return counts.get(0);
		}
		return null;
	}
}
