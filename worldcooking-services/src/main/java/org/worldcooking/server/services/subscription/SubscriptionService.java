package org.worldcooking.server.services.subscription;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.worldcooking.server.dao.impl.EventDAOImpl;
import org.worldcooking.server.dao.impl.MultiEntitiesHibernateDAOImpl;
import org.worldcooking.server.dao.impl.SubscriptionDAOImpl;
import org.worldcooking.server.dao.impl.TaskDAOImpl;
import org.worldcooking.server.entity.event.Event;
import org.worldcooking.server.entity.event.Subscription;
import org.worldcooking.server.entity.event.Task;
import org.worldcooking.server.entity.payment.Payment;
import org.worldcooking.server.entity.payment.PaymentMode;
import org.worldcooking.server.entity.people.Participant;
import org.worldcooking.server.exception.EntityIdNotFountException;
import org.worldcooking.server.services.subscription.model.NewParticipant;
import org.worldcooking.server.services.subscription.model.NewSubscription;
import org.worldcooking.server.services.subscription.model.NewSubscriptionPaymentMode;

@Repository
public class SubscriptionService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private EventDAOImpl eventDao;

	@Autowired
	private TaskDAOImpl taskDao;

	@Autowired
	private SubscriptionDAOImpl subscriptionDAOImpl;

	@Autowired
	private MultiEntitiesHibernateDAOImpl dao;

	public boolean isRegistrationClosed(Long eventId)
			throws EntityIdNotFountException {
		Event e = eventDao.findById(eventId);
		Integer maxParticipantsNb = e.getMaxParticipants();

		Long validParticipantsNb = subscriptionDAOImpl
				.countValidatedParticipants(eventId);

		// TODO maxParticipantsNb should be Long
		if (validParticipantsNb.intValue() >= maxParticipantsNb) {
			logger.debug("Registration is closed (" + validParticipantsNb + "/"
					+ maxParticipantsNb + " participants)");
			return true;
		} else {
			logger.debug("Registration is not closed (" + validParticipantsNb
					+ "/" + maxParticipantsNb + " participants)");
			return false;
		}

	}

	public double calculateSubscriptionPrice(
			Collection<Participant> participants)
			throws EntityIdNotFountException {
		// initial amount is zero
		double amount = 0;
		for (Participant participant : participants) {

			// add the price of participant to subscription amount
			amount += participant.getTask().getPricePerParticipant();
		}
		return amount;
	}

	/**
	 * Register a new subscription.
	 * 
	 * @param newSubscription
	 *            the subscription parameters
	 * @return the persisted subscription
	 * @throws EntityIdNotFountException
	 *             a required entity id was not fount in database.
	 */
	public Subscription subscribe(NewSubscription newSubscription)
			throws EntityIdNotFountException {
		// TODO make services transactionnals
		// TODO subscription or registration?
		Subscription subscription = null;
		try {
			if (newSubscription.getEventId() != null) {
				// create subscriber participant
				Participant subscriberParticipant = createParticipant(newSubscription
						.getSubscriber().getSubscriberParticipant());

				dao.saveOrUpdate(subscriberParticipant);

				// create a new subscription
				subscription = createSubscription(newSubscription);
				subscription.setSubscriberParticipant(subscriberParticipant);
				dao.saveOrUpdate(subscription);

				Set<Participant> participants = new HashSet<Participant>();

				// create and add all additional participants
				for (NewParticipant newParticipant : newSubscription
						.getAdditionalParticipants()) {
					participants.add(createParticipant(newParticipant));
				}
				// add subscriber
				participants.add(subscriberParticipant);

				// set all participants
				subscription.addParticipants(participants);

				dao.saveOrUpdateAll(participants);

				// create payment
				Payment payment = createPayment(newSubscription, participants);
				payment.setSubscription(subscription);
				dao.saveOrUpdate(payment);
			}
			logger.info(
					"Successfully created subscription of '{}' with {} participants.",
					newSubscription.getSubscriber().getEmailAddress(),
					newSubscription.getAdditionalParticipants().size());
		} catch (EntityIdNotFountException e) {
			logger.error(
					"Unable to register subscription because "
							+ e.getErrorMessage() + ".", e);
			throw e;
		}
		return subscription;
	}

	private Payment createPayment(NewSubscription newSubscription,
			Collection<Participant> participants)
			throws EntityIdNotFountException {
		// TODO Payment or Facturation ?
		Payment payment = new Payment();
		payment.setAmount(calculateSubscriptionPrice(participants));
		if (newSubscription.getPaymentMode() == NewSubscriptionPaymentMode.PAYPAL) {
			// paypal
			payment.setMode(PaymentMode.PAYPAL);
		} else {
			// manual
			payment.setMode(PaymentMode.PEOPLE);
			// the person who is receiving the money
			payment.setReference(newSubscription.getPaymentTarget());
		}
		return payment;
	}

	private Participant createParticipant(NewParticipant newParticipant)
			throws EntityIdNotFountException {
		Participant participant = new Participant();
		participant.setName(newParticipant.getName());

		// retrieve the corresponding task
		Task task = taskDao.findById(newParticipant.getTaskId());
		participant.setTask(task);

		return participant;
	}

	private Subscription createSubscription(NewSubscription newSubscription)
			throws EntityIdNotFountException {
		Event e = eventDao.findById(newSubscription.getEventId());

		Subscription subscription;
		// create subscription
		subscription = new Subscription();
		subscription.setSubscriptionDate(new Date());
		subscription.setEvent(e);
		if (newSubscription.getSubscriber() != null) {
			subscription.setEmail(newSubscription.getSubscriber()
					.getEmailAddress());
		}
		return subscription;
	}

	public Subscription validatePayment(Long subscriptionId)
			throws EntityIdNotFountException {
		try {
			Subscription subscription = subscriptionDAOImpl
					.findById(subscriptionId);

			subscription.setValidate(true);
			dao.saveOrUpdate(subscription);

			return subscription;
		} catch (EntityIdNotFountException e) {
			logger.error(
					"Unable to validate payment of subscription '"
							+ subscriptionId + "' because "
							+ e.getErrorMessage() + ".", e);
			throw e;
		}
	}

	public Subscription unvalidatePayment(Long subscriptionId)
			throws EntityIdNotFountException {
		try {
			Subscription subscription = subscriptionDAOImpl
					.findById(subscriptionId);

			subscription.setValidate(false);
			dao.saveOrUpdate(subscription);

			return subscription;
		} catch (EntityIdNotFountException e) {
			logger.error(
					"Unable to unvalidate payment of subscription '"
							+ subscriptionId + "' because "
							+ e.getErrorMessage() + ".", e);
			throw e;
		}
	}

	public Subscription findFullSubscriptionById(Long id)
			throws EntityIdNotFountException {
		return subscriptionDAOImpl.findFullSubscriptionById(id);
	}

	public SortedSet<Subscription> findNonValidatedSubscriptions(Long eventId) {
		return subscriptionDAOImpl.findNonValidatedSubscriptions(eventId);
	}

	public SortedSet<Subscription> findValidatedSubscriptions(Long eventId) {
		return subscriptionDAOImpl.findValidatedSubscriptions(eventId);
	}
}
