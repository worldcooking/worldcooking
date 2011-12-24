package org.worldcooking.server.dao.impl;

import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;

import org.oupsasso.mishk.core.dao.GenericDao;
import org.springframework.stereotype.Repository;
import org.worldcooking.server.entity.event.Registration;

/**
 * Plain DAO which provides only
 * {@link org.worldcooking.server.dao.impl.GenericHibernateDAOImpl} methods
 */
@Repository
public class RegistrationDAOImpl extends GenericDao<Registration, Long> {

	/**
	 * Method returning the Registration corresponding to the registrationId
	 * parameter. <br/>
	 * The Registration is completed with all his objects.
	 * 
	 * @param registrationId
	 *            Unique id use to retrieve the Registration.
	 * @return The Registration. Return null if the Registration is not found.
	 */
	public Registration findFullRegistrationById(Long registrationId) {
		@SuppressWarnings("unchecked")
		List<Registration> registrations = getHibernateTemplate().findByNamedParam(
				"from Registration s left join fetch s.participants as p " + " where s.id=:registrationId",
				"registrationId", registrationId);
		if (registrations != null && !registrations.isEmpty()) {
			return registrations.get(0);
		}
		return null;
	}

	public SortedSet<Registration> findNonValidatedRegistrations(Long eventId) {
		return findRegistrations(eventId, false);
	}

	public SortedSet<Registration> findValidatedRegistrations(Long eventId) {
		return findRegistrations(eventId, true);
	}

	private SortedSet<Registration> findRegistrations(Long eventId, Boolean validatedRegistration) {
		@SuppressWarnings("unchecked")
		List<Registration> registrations = getHibernateTemplate().findByNamedParam(
				"from Registration s left join fetch s.participants as p "
						+ " where s.event.id=:eventId and s.validate=:validatedRegistration",
				new String[] { "eventId", "validatedRegistration" }, new Object[] { eventId, validatedRegistration });
		return toTreeSet(registrations, new Comparator<Registration>() {

			@Override
			public int compare(Registration s1, Registration s2) {
				// TODO use comparetobuilder to avoid to erase 2 subscribtions
				// done on same date
				return s1.getRegistrationDate().compareTo(s2.getRegistrationDate());
			}

		});
	}

	public Long countValidatedParticipants(Long eventId) {
		@SuppressWarnings("unchecked")
		List<Long> counts = getHibernateTemplate().findByNamedParam(
				"select count (p) from Participant p"
						+ " where p.registration.event.id=:eventId and p.registration.validate=true", "eventId",
				eventId);
		if (counts != null && counts.size() == 1) {
			return counts.get(0);
		}
		return null;
	}
}
