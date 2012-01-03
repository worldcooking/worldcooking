package org.worldcooking.server.services.registration;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;

import org.oupsasso.mishk.core.dao.exception.EntityIdNotFountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.worldcooking.server.dao.impl.EventDAOImpl;
import org.worldcooking.server.dao.impl.ParticipantDAOImpl;
import org.worldcooking.server.dao.impl.RegistrationDAOImpl;
import org.worldcooking.server.dao.impl.TaskDAOImpl;
import org.worldcooking.server.entity.event.Event;
import org.worldcooking.server.entity.event.Registration;
import org.worldcooking.server.entity.event.Task;
import org.worldcooking.server.entity.payment.Payment;
import org.worldcooking.server.entity.payment.PaymentMode;
import org.worldcooking.server.entity.people.Participant;
import org.worldcooking.server.services.registration.model.NewParticipant;
import org.worldcooking.server.services.registration.model.NewRegistration;
import org.worldcooking.server.services.registration.model.NewRegistrationPaymentMode;

@Repository
public class RegistrationService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private EventDAOImpl eventDao;

	@Autowired
	private TaskDAOImpl taskDao;

	@Autowired
	private RegistrationDAOImpl registrationDAOImpl;

	@Autowired
	private ParticipantDAOImpl participantDAO;

	public boolean isRegistrationClosed(Long eventId) throws EntityIdNotFountException {
		Event e = eventDao.findById(eventId);
		Long maxParticipantsNb = e.getMaxParticipants();

		Long validParticipantsNb = registrationDAOImpl.countValidatedParticipants(eventId);

		// TODO maxParticipantsNb should be Long
		if (validParticipantsNb.intValue() >= maxParticipantsNb) {
			logger.debug("Registration is closed (" + validParticipantsNb + "/" + maxParticipantsNb + " participants)");
			return true;
		} else {
			logger.debug("Registration is not closed (" + validParticipantsNb + "/" + maxParticipantsNb
					+ " participants)");
			return false;
		}

	}

	public double calculateRegistrationPrice(Long registrationId) throws EntityIdNotFountException {
		Registration registration = registrationDAOImpl.findFullRegistrationById(registrationId);

		return calculateRegistrationPrice(registration.getParticipants());

	}

	public double calculateRegistrationPrice(Collection<Participant> participants) throws EntityIdNotFountException {
		// initial amount is zero
		double amount = 0;
		for (Participant participant : participants) {

			// add the price of participant to registration amount
			amount += participant.getTask().getPricePerParticipant();
		}
		return amount;
	}

	/**
	 * Register a new registration.
	 * 
	 * @param newRegistration
	 *            the registration parameters
	 * @return the persisted registration
	 * @throws EntityIdNotFountException
	 *             a required entity id was not fount in database.
	 */
	public Registration subscribe(NewRegistration newRegistration) throws EntityIdNotFountException {
		// TODO make services transactionnals
		// TODO registration or registration?
		Registration registration = null;
		try {
			if (newRegistration.getEventId() != null) {
				// create subscriber participant
				Participant subscriberParticipant = createParticipant(newRegistration.getSubscriber()
						.getSubscriberParticipant());

				taskDao.saveOrUpdate(subscriberParticipant);

				// create a new registration
				registration = createRegistration(newRegistration);
				registration.setSubscriberParticipant(subscriberParticipant);
				taskDao.saveOrUpdate(registration);

				Set<Participant> participants = new HashSet<Participant>();

				// create and add all additional participants
				for (NewParticipant newParticipant : newRegistration.getAdditionalParticipants()) {
					participants.add(createParticipant(newParticipant));
				}
				// add subscriber
				participants.add(subscriberParticipant);

				// set all participants
				registration.addParticipants(participants);

				taskDao.saveOrUpdateAll(participants);

				// create payment
				Payment payment = createPayment(newRegistration, participants);
				payment.setRegistration(registration);
				taskDao.saveOrUpdate(payment);
			}
			logger.info("Successfully created registration of '{}' with {} participants.", newRegistration
					.getSubscriber().getEmailAddress(), newRegistration.getAdditionalParticipants().size());
		} catch (EntityIdNotFountException e) {
			logger.error("Unable to register registration because " + e.getErrorMessage() + ".", e);
			throw e;
		}
		return registration;
	}

	private Payment createPayment(NewRegistration newRegistration, Collection<Participant> participants)
			throws EntityIdNotFountException {
		// TODO Payment or Facturation ?
		Payment payment = new Payment();
		payment.setAmount(calculateRegistrationPrice(participants));
		if (newRegistration.getPaymentMode() == NewRegistrationPaymentMode.PAYPAL) {
			// paypal
			payment.setMode(PaymentMode.PAYPAL);
		} else {
			// manual
			payment.setMode(PaymentMode.PEOPLE);
			// the person who is receiving the money
			payment.setReference(newRegistration.getPaymentTarget());
		}
		return payment;
	}

	private Participant createParticipant(NewParticipant newParticipant) throws EntityIdNotFountException {
		Participant participant = new Participant();
		participant.setName(newParticipant.getName());

		// retrieve the corresponding task
		Task task = taskDao.findById(newParticipant.getTaskId());
		participant.setTask(task);

		return participant;
	}

	private Registration createRegistration(NewRegistration newRegistration) throws EntityIdNotFountException {
		Event e = eventDao.findById(newRegistration.getEventId());

		Registration registration;
		// create registration
		registration = new Registration();
		registration.setRegistrationDate(new Date());
		registration.setEvent(e);
		if (newRegistration.getSubscriber() != null) {
			registration.setEmail(newRegistration.getSubscriber().getEmailAddress());
		}
		return registration;
	}

	public Registration validatePayment(Long registrationId) throws EntityIdNotFountException {
		try {
			Registration registration = registrationDAOImpl.findById(registrationId);

			registration.setValidate(true);
			taskDao.saveOrUpdate(registration);

			return registration;
		} catch (EntityIdNotFountException e) {
			logger.error(
					"Unable to validate payment of registration '" + registrationId + "' because "
							+ e.getErrorMessage() + ".", e);
			throw e;
		}
	}

	public Registration unvalidatePayment(Long registrationId) throws EntityIdNotFountException {
		try {
			Registration registration = registrationDAOImpl.findById(registrationId);

			registration.setValidate(false);
			taskDao.saveOrUpdate(registration);

			return registration;
		} catch (EntityIdNotFountException e) {
			logger.error(
					"Unable to unvalidate payment of registration '" + registrationId + "' because "
							+ e.getErrorMessage() + ".", e);
			throw e;
		}
	}

	public Registration removeRegistration(Long registrationId) throws EntityIdNotFountException {
		try {
			Registration registration = registrationDAOImpl.findById(registrationId);

			registration.setSubscriberParticipant(null);

			registrationDAOImpl.saveOrUpdate(registration);

			registrationDAOImpl.delete(registration);

			return registration;
		} catch (EntityIdNotFountException e) {
			logger.error("Unable to remove registration '" + registrationId + "' because " + e.getErrorMessage() + ".",
					e);
			throw e;
		}
	}

	public Registration findFullRegistrationById(Long id) throws EntityIdNotFountException {
		return registrationDAOImpl.findFullRegistrationById(id);
	}

	public SortedSet<Registration> findNonValidatedRegistrations(Long eventId) {
		return registrationDAOImpl.findNonValidatedRegistrations(eventId);
	}

	public SortedSet<Registration> findValidatedRegistrations(Long eventId) {
		return registrationDAOImpl.findValidatedRegistrations(eventId);
	}

	public Participant findParticipantById(Long participantId) throws EntityIdNotFountException {
		return participantDAO.findById(participantId);
	}

	public Task updateTask(Long participantId, Long taskId) throws EntityIdNotFountException {
		Participant participant = participantDAO.findById(participantId);

		Task previousTask = participant.getTask();

		Task task = taskDao.findById(taskId);

		participant.setTask(task);

		taskDao.saveOrUpdate(participant);

		return previousTask;
	}

	public Long countValidatedParticipants(Long eventId) {
		return registrationDAOImpl.countValidatedParticipants(eventId);
	}

	public Long countNotValidatedParticipants(Long eventId) {
		return registrationDAOImpl.countNotValidatedParticipants(eventId);
	}
}
