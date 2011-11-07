/**
 * 
 */
package org.worldcooking.web.admin.payment.validation;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.worldcooking.server.entity.event.Event;
import org.worldcooking.server.entity.event.Subscription;
import org.worldcooking.server.entity.people.Participant;
import org.worldcooking.server.exception.EntityIdNotFountException;
import org.worldcooking.server.services.EventService;
import org.worldcooking.server.services.subscription.SubscriptionService;

/**
 * @author MatthieuG
 * 
 */
@Controller
public class AdminPaymentValidationController {

	private static final String MODEL_NAME = "paymentValidation";
	private static final String JSP = "admin/admin-payment-validation";
	@Autowired
	private EventService eventService;

	@Autowired
	private SubscriptionService subscriptionService;

	@RequestMapping(value = "/admin/payment/validation", method = RequestMethod.GET)
	public ModelAndView handleRequest() {

		ModelAndView modelAndView = new ModelAndView(JSP);
		AdminPaymentValidation form = new AdminPaymentValidation();

		modelAndView.addObject(MODEL_NAME, form);

		return modelAndView;
	}

	@ModelAttribute("subscriptions")
	public Map<Long, String> populateSubscriptions()
			throws EntityIdNotFountException {
		// TODO manage error

		Map<Long, String> subscriptionsDesc = new LinkedHashMap<Long, String>();

		Event lastEvent = eventService.getLastEvent();

		if (lastEvent != null) {
			List<Subscription> subscriptions = subscriptionService
					.findNonValidatedSubscriptions(lastEvent.getId());

			subscriptionsDesc.put(-1L, "");
			for (Subscription subscription : subscriptions) {
				double price = subscriptionService
						.calculateSubscriptionPrice(subscription
								.getParticipants());

				String description = subscription.getEmail() + " - " + price
						+ "€";

				String participantDescription = null;

				for (Participant participant : subscription.getParticipants()) {
					if (participantDescription == null) {
						participantDescription = "";
					} else {
						participantDescription += ", ";
					}
					participantDescription += participant.getName();
				}

				description += " (participants: " + participantDescription
						+ ")";

				subscriptionsDesc.put(subscription.getId(), description);
			}
		}

		return subscriptionsDesc;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(
			@Valid @ModelAttribute(MODEL_NAME) AdminPaymentValidation adminPaymentValidation,
			BindingResult result) throws Exception {

		if (result.hasErrors()) {
			return JSP;
		}

		if ("pépérou".equals(adminPaymentValidation.getPassword())) {
			subscriptionService.validatePayment(adminPaymentValidation
					.getSubscriptionId());
			return JSP;
		}
		// redirect to main page
		return JSP;
	}
}
