package org.worldcooking.web.confirmation;

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
import org.worldcooking.server.entity.event.Subscription;
import org.worldcooking.server.exception.EntityIdNotFountException;
import org.worldcooking.server.services.EventService;
import org.worldcooking.server.services.subscription.SubscriptionService;

@Controller
public class ConfirmationController {

	private final Logger logger = LoggerFactory
			.getLogger(ConfirmationController.class);

	@Autowired
	private SubscriptionService subscriptionService;

	@Autowired
	private EventService eventService;

	@RequestMapping(value = "/registration/confirmation", method = RequestMethod.GET)
	public String handleRequest(@RequestParam Long subscriptionId,
			ModelMap model) throws EntityIdNotFountException {
		// TODO do not throw exception: manage errors!

		// TODO manage errors
		Assert.notNull(subscriptionId);

		Event event = eventService.getLastEvent();

		if (event != null) {
			if (subscriptionService.isRegistrationClosed(event.getId())) {
				logger.warn(
						"Attempt to access to closed registration of event '{}'.",
						event.getName());
				return "redirect:/";
			}
		}

		Subscription subscription = subscriptionService
				.findFullSubscriptionById(subscriptionId);

		if (subscription.getValidate() != null
				&& subscription.getValidate().booleanValue()) {
			// registration already validated: redirect to welcome page
			return "redirect:/";
		}

		model.addAttribute("event", subscription.getEvent());

		// calculate amount
		Double paypalAmount = subscriptionService
				.calculateSubscriptionPrice(subscription.getParticipants());

		// TODO display pre-registration details and price to pay

		model.addAttribute("event", null);
		return "confirmation";
	}

}
