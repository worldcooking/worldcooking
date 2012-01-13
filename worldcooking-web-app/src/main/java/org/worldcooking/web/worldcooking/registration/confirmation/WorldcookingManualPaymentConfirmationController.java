package org.worldcooking.web.worldcooking.registration.confirmation;

import org.mishk.business.event.entity.Event;
import org.mishk.business.event.entity.Registration;
import org.mishk.business.event.entity.RegistrationStatus;
import org.mishk.business.event.service.EventService;
import org.mishk.business.event.service.RegistrationService;
import org.oupsasso.mishk.core.dao.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.worldcooking.service.admin.WorldcookingService;

@Controller
public class WorldcookingManualPaymentConfirmationController {

	private final Logger logger = LoggerFactory.getLogger(WorldcookingManualPaymentConfirmationController.class);

	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private EventService eventService;

	@Autowired
	private WorldcookingService worldcookingService;

	@RequestMapping(value = "/event/{eventReference}/registration/confirmation", method = RequestMethod.GET)
	public String handleRequest(@PathVariable String eventReference, @RequestParam Long registrationId, ModelMap model)
			throws EntityNotFoundException {
		// TODO do not throw exception: manage errors!

		// TODO manage errors
		Assert.notNull(registrationId);

		Event event = eventService.findEventByReference(eventReference, false);

		if (!eventService.isEventOpen(event)) {
			logger.warn("Attempt to access to closed registration of event '{}'.", event.getName());
			return "redirect:/";
		}

		Registration registration = registrationService.findRegistrationById(registrationId, true);

		if (isRegistrationValidated(registration)) {
			// registration already validated: redirect to welcome page
			return "redirect:/";
		}

		model.addAttribute("event", registration.getEvent());

		// calculate amount
		// TODO Double paypalAmount =
		// worldcookingService.calculateRegistrationPrice(registrationId);

		// TODO display pre-registration details and price to pay

		return "worldcooking/registration/confirmation/worldcooking-manual-payment-confirmation";
	}

	private boolean isRegistrationValidated(Registration registration) {
		return registration != null && registration.getRegistrationStatus() == RegistrationStatus.VALIDATED;
	}

}
