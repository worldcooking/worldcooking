package org.worldcooking.web.worldcooking.registration.confirmation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.worldcooking.server.entity.event.Event;
import org.worldcooking.server.entity.event.Registration;
import org.worldcooking.server.exception.EntityIdNotFountException;
import org.worldcooking.server.services.EventService;
import org.worldcooking.server.services.registration.RegistrationService;

@Controller
public class WorldcookingManualPaymentConfirmationController {

	private final Logger logger = LoggerFactory
			.getLogger(WorldcookingManualPaymentConfirmationController.class);

	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private EventService eventService;

	@RequestMapping(value = "/registration/confirmation", method = RequestMethod.GET)
	public String handleRequest(@RequestParam Long registrationId,
			ModelMap model) throws EntityIdNotFountException {
		// TODO do not throw exception: manage errors!

		// TODO manage errors
		Assert.notNull(registrationId);

		Event event = eventService.getLastEvent();

		if (event != null) {
			if (registrationService.isRegistrationClosed(event.getId())) {
				logger.warn(
						"Attempt to access to closed registration of event '{}'.",
						event.getName());
				return "redirect:/";
			}
		}

		Registration registration = registrationService
				.findFullRegistrationById(registrationId);

		if (registration.getValidate() != null
				&& registration.getValidate().booleanValue()) {
			// registration already validated: redirect to welcome page
			return "redirect:/";
		}

		model.addAttribute("event", registration.getEvent());

		// calculate amount
		Double paypalAmount = registrationService
				.calculateRegistrationPrice(registration.getParticipants());

		// TODO display pre-registration details and price to pay

		model.addAttribute("event", null);
		return "worldcooking/registration/confirmation/worldcooking-manual-payment-confirmation";
	}

}
