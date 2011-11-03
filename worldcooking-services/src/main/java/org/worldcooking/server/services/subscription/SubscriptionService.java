package org.worldcooking.server.services.subscription;

import java.util.List;

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

	public double calculateSubscriptionPrice(List<Participant> participants)
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
				// create a new subscription
				subscription = createSubscription(newSubscription);
				// add participants
				createParticipants(newSubscription, subscription);
				// initialize payment
				createPayment(newSubscription, subscription);
			}
		} catch (EntityIdNotFountException e) {
			logger.error(
					"Unable to register subscription because "
							+ e.getErrorMessage() + ".", e);
			throw e;
		}
		return subscription;
	}

	private void createPayment(NewSubscription newSubscription,
			Subscription subscription) throws EntityIdNotFountException {
		// TODO Payment or Facturation ?
		Payment payment = new Payment();
		payment.setAmount(calculateSubscriptionPrice(subscription
				.getParticipants()));
		if (newSubscription.getPaymentMode() == NewSubscriptionPaymentMode.PAYPAL) {
			// paypal
			payment.setMode(PaymentMode.PAYPAL);
		} else {
			// manual
			payment.setMode(PaymentMode.PEOPLE);
			// the person who is receiving the money
			payment.setReference(newSubscription.getPaymentTarget());
		}
		subscription.setPayment(payment);
		dao.makePersistent(payment);
	}

	private void createParticipants(NewSubscription newSubscription,
			Subscription subscription) throws EntityIdNotFountException {

		// create and add all participants
		for (NewParticipant newParticipant : newSubscription.getParticipants()) {
			Participant participant = new Participant();
			participant.setName(newParticipant.getName());

			// retrieve the corresponding task
			Task task = taskDao.findById(newParticipant.getTaskId());
			participant.setTask(task);

			subscription.addParticipant(participant);
			dao.makePersistent(participant);
		}
	}

	private Subscription createSubscription(NewSubscription newSubscription)
			throws EntityIdNotFountException {
		Event e = eventDao.findById(newSubscription.getEventId());

		Subscription subscription;
		// create subscription
		subscription = new Subscription();
		subscription.setEvent(e);
		if (newSubscription.getSubscriber() != null) {
			subscription.setEmail(newSubscription.getSubscriber()
					.getEmailAddress());
		}
		dao.makePersistent(subscription);
		return subscription;
	}

	public void validatePayment(Long subscriptionId)
			throws EntityIdNotFountException {
		try {
			Subscription subscription = subscriptionDAOImpl
					.findById(subscriptionId);

			// TODO subscription.setValid();
			dao.makePersistent(subscription);
		} catch (EntityIdNotFountException e) {
			logger.error(
					"Unable to validate payment of subscription '"
							+ subscriptionId + "' because "
							+ e.getErrorMessage() + ".", e);
			throw e;
		}
	}
}
