package org.worldcooking.web.worldcooking.registration.form;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.worldcooking.server.entity.event.Event;
import org.worldcooking.server.entity.event.Registration;
import org.worldcooking.server.entity.event.Task;
import org.worldcooking.server.exception.EntityIdNotFountException;
import org.worldcooking.server.services.EventService;
import org.worldcooking.server.services.registration.RegistrationService;
import org.worldcooking.server.services.registration.model.NewParticipant;
import org.worldcooking.server.services.registration.model.NewRegistration;
import org.worldcooking.server.services.registration.model.NewRegistrationPaymentMode;
import org.worldcooking.web.worldcooking.registration.form.model.WorldcookingRegistrationFormDetail;

@Controller
@RequestMapping(value = "/registration")
public class WorldcookingRegistrationFormController {
	private static final String PAYPAL_MODE_KEY = "paypal";
	private static final String JSP = "worldcooking/registration/form/worldcooking-registration-form";

	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private EventService eventService;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping(method = RequestMethod.GET)
	public String initializeForm(ModelMap model)
			throws EntityIdNotFountException {

		WorldcookingRegistrationFormDetail registration = new WorldcookingRegistrationFormDetail();

		Event lastEvent = eventService.getLastEvent();

		if (lastEvent != null) {
			registration.setEventId(lastEvent.getId());

			if (registrationService.isRegistrationClosed(lastEvent.getId())) {
				logger.warn(
						"Attempt to access to closed registration of event '{}'.",
						lastEvent.getName());
				return "redirect:/";
			}

		}
		registration.setAdditionalParticipantsTasks(Arrays.asList(0l, 0l));

		model.addAttribute("registration", registration);
		return JSP;
	}

	@ModelAttribute("availableTasks")
	public Map<Long, String> populateAvailableTasks() {

		Map<Long, String> availableTasksIdName = new LinkedHashMap<Long, String>();

		Event lastEvent = eventService.getLastEvent();
		if (lastEvent != null) {
			List<Task> availableTasks = eventService
					.getAvailableTasks(lastEvent.getId());

			for (Task t : availableTasks) {
				availableTasksIdName.put(t.getId(), t.getName());
			}
		}

		return availableTasksIdName;
	}

	@ModelAttribute("availablePaymentModes")
	public Map<String, String> populateAvailablePaymentModes() {
		Map<String, String> availablePaymentModes = new LinkedHashMap<String, String>();

		availablePaymentModes.put(PAYPAL_MODE_KEY, "Paypal or CB");
		availablePaymentModes.put("manual-blevine", "Benjamin Levine");
		availablePaymentModes.put("manual-mgaudet", "Matthieu Gaudet");
		availablePaymentModes.put("manual-ngruyer", "Nicolas Gruyer");
		availablePaymentModes.put("manual-ntoublanc", "Nicolas Toublanc");
		availablePaymentModes.put("manual-ntorres", "Nidia Torres");
		availablePaymentModes.put("manual-fbouvet", "Fred Bouvet");
		return availablePaymentModes;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(
			@Valid @ModelAttribute("registration") WorldcookingRegistrationFormDetail registrationModel,
			BindingResult result) throws Exception {

		Event event = eventService.findById(registrationModel.getEventId());

		if (event != null) {
			if (registrationService.isRegistrationClosed(event.getId())) {
				logger.warn(
						"Attempt to access to closed registration of event '{}'.",
						event.getName());
				return "redirect:/";
			}
		}

		// check parameters (TODO manage errors)
		// TODO add a custom constraint (using group constraints?)
		int participantsNb = registrationModel.getAdditionalParticipantsNames()
				.size();
		int tasksNb = registrationModel.getAdditionalParticipantsTasks().size();
		Assert.isTrue(participantsNb <= tasksNb, "Participants tasks ("
				+ tasksNb + ") should be as large as participants names ("
				+ participantsNb + ").");

		if (result.hasErrors()) {

			return JSP;
		}

		// create new registration
		NewRegistration newRegistration = createNewRegistration(registrationModel);

		// persiste new registration
		Registration registration = registrationService
				.subscribe(newRegistration);

		String returnView;
		if (newRegistration.getPaymentMode() == NewRegistrationPaymentMode.PAYPAL) {
			// redirect to paypal
			returnView = "redirect:/registration/confirmation/paypal?registrationId="
					+ registration.getId();
		} else {
			returnView = "redirect:/registration/confirmation?registrationId="
					+ registration.getId();
		}
		// redirect to main page
		return returnView;
	}

	/**
	 * Create the new registration.
	 * 
	 * @param registration
	 * @throws EntityIdNotFountException
	 */
	private NewRegistration createNewRegistration(
			WorldcookingRegistrationFormDetail registration)
			throws EntityIdNotFountException {
		// create registration
		NewRegistration newRegistration = new NewRegistration();
		Long eventId = registration.getEventId();
		String subscriberEmailAddress = registration.getEmailAddress();

		Iterator<Long> participantsTasksIt = registration
				.getAdditionalParticipantsTasks().iterator();
		// add additional participants
		for (String participantName : registration
				.getAdditionalParticipantsNames()) {
			if (participantName != null) {
				participantName = participantName.trim();
				if (!participantName.isEmpty()) {
					newRegistration.addParticipant(participantName,
							participantsTasksIt.next());
				}
			}
		}
		NewParticipant subscriberParticipant = new NewParticipant(
				registration.getSubscriberParticipantName(),
				registration.getSubscriberParticipantTask());
		if (PAYPAL_MODE_KEY.equals(registration.getPaymentMode())) {
			// paypal

			newRegistration.configureWithPaypalPayment(eventId,
					subscriberEmailAddress, subscriberParticipant);
		} else {
			Map<String, String> availablePaymentModes = populateAvailablePaymentModes();

			String paymentTarget = availablePaymentModes.get(registration
					.getPaymentMode());
			// TODO meilleure gestion des erreurs
			Assert.notNull(paymentTarget);
			newRegistration.configureWithManualPayment(eventId,
					subscriberEmailAddress, paymentTarget,
					subscriberParticipant);
		}

		return newRegistration;
	}

	// http://java.dzone.com/articles/converting-spring
	// http://www.objis.com/formation-java/tutoriel-spring-formulaires-spring-mvc.html
	// http://static.springsource.org/spring/docs/3.0.3.RELEASE/spring-framework-reference/html/view.html#view-jsp-formtaglib

	// http://www.mkyong.com/spring-mvc/spring-3-mvc-and-jsr303-valid-example/
}
