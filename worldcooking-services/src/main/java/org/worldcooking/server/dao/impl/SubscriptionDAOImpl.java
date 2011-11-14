package org.worldcooking.server.dao.impl;

import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;

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

	public SortedSet<Subscription> findNonValidatedSubscriptions(Long eventId) {
		return findRegistrations(eventId, false);
	}

	public SortedSet<Subscription> findValidatedSubscriptions(Long eventId) {
		return findRegistrations(eventId, true);
	}

	private SortedSet<Subscription> findRegistrations(Long eventId,
			Boolean validatedRegistration) {
		@SuppressWarnings("unchecked")
		List<Subscription> subscriptions = getHibernateTemplate()
				.findByNamedParam(
						"from Subscription s left join fetch s.participants as p "
								+ " where s.event.id=:eventId and s.validate=:validatedRegistration",
						new String[] { "eventId", "validatedRegistration" },
						new Object[] { eventId, validatedRegistration });
		return toTreeSet(subscriptions, new Comparator<Subscription>() {

			@Override
			public int compare(Subscription s1, Subscription s2) {
				// TODO use comparetobuilder to avoid to erase 2 subscribtions
				// done on same date
				return s1.getSubscriptionDate().compareTo(
						s2.getSubscriptionDate());
			}

		});
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
