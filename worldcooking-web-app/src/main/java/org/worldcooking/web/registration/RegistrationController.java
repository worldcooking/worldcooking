package org.worldcooking.web.registration;

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
import org.worldcooking.server.entity.event.Subscription;
import org.worldcooking.server.entity.event.Task;
import org.worldcooking.server.exception.EntityIdNotFountException;
import org.worldcooking.server.services.EventService;
import org.worldcooking.server.services.subscription.SubscriptionService;
import org.worldcooking.server.services.subscription.model.NewSubscription;
import org.worldcooking.server.services.subscription.model.NewSubscriptionPaymentMode;

@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {
	private static final String PAYPAL_MODE_KEY = "paypal";

	@Autowired
	private SubscriptionService subscriptionService;

	@Autowired
	private EventService eventService;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping(method = RequestMethod.GET)
	public String initializeForm(ModelMap model) {
		// Perform and Model / Form initialization

		Registration registration = new Registration();
		registration.setEventId(10L);

		model.addAttribute("registration", registration);
		return "registration";
	}

	@ModelAttribute("availableTasks")
	public Map<Long, String> populateAvailableTasks() {

		List<Task> availableTasks = eventService.getAvailableTasks(10L);

		Map<Long, String> availableTasksIdName = new LinkedHashMap<Long, String>();

		for (Task t : availableTasks) {
			availableTasksIdName.put(t.getId(), t.getName());
		}

		return availableTasksIdName;
	}

	@ModelAttribute("availablePaymentModes")
	public Map<String, String> populateAvailablePaymentModes() {
		Map<String, String> availablePaymentModes = new LinkedHashMap<String, String>();

		availablePaymentModes.put(PAYPAL_MODE_KEY, "paypal");
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
			@Valid @ModelAttribute("registration") Registration registration,
			BindingResult result) throws Exception {

		// check parameters (TODO manage errors)
		Assert.isTrue(
				registration.getParticipantsNames().size() <= registration
						.getParticipantTasks().size(),
				"Participants tasks should be as large as participants names.");

		if (result.hasErrors()) {

			return "registration";
		}

		// create new registration
		NewSubscription newRegistration = createNewRegistration(registration);

		// persiste new registration
		Subscription subscription = subscriptionService
				.subscribe(newRegistration);

		if (newRegistration.getPaymentMode() == NewSubscriptionPaymentMode.PAYPAL) {
			// redirect to paypal
			return "redirect:/registration/confirmation/paypal?subscriptionId="
					+ subscription.getId();
		}
		// redirect to main page
		return "redirect:/";
	}

	/**
	 * Create the new registration.
	 * 
	 * @param registration
	 * @throws EntityIdNotFountException
	 */
	private NewSubscription createNewRegistration(Registration registration)
			throws EntityIdNotFountException {
		// create subscription
		NewSubscription newSubscription = new NewSubscription();
		Long eventId = registration.getEventId();
		String subscriberEmailAddress = registration.getEmailAddress();

		Iterator<Long> participantsTasksIt = registration.getParticipantTasks()
				.iterator();
		// add participants
		for (String participantName : registration.getParticipantsNames()) {
			if (participantName != null) {
				participantName = participantName.trim();
				if (!participantName.isEmpty()) {
					newSubscription.addParticipant(participantName,
							participantsTasksIt.next());
				}
			}
		}

		if (PAYPAL_MODE_KEY.equals(registration.getPaymentMode())) {
			// paypal
			newSubscription.configureWithPaypalPayment(eventId,
					subscriberEmailAddress);
		} else {
			Map<String, String> availablePaymentModes = populateAvailablePaymentModes();

			String paymentTarget = availablePaymentModes.get(registration
					.getPaymentMode());
			// TODO meilleure gestion des erreurs
			Assert.notNull(paymentTarget);
			newSubscription.configureWithManualPayment(eventId,
					subscriberEmailAddress, paymentTarget);
		}

		return newSubscription;
	}

	// http://java.dzone.com/articles/converting-spring
	// http://www.objis.com/formation-java/tutoriel-spring-formulaires-spring-mvc.html
	// http://static.springsource.org/spring/docs/3.0.3.RELEASE/spring-framework-reference/html/view.html#view-jsp-formtaglib

	// http://www.mkyong.com/spring-mvc/spring-3-mvc-and-jsr303-valid-example/
}
