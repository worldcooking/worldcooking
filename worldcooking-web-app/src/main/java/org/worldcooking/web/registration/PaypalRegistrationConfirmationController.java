package org.worldcooking.web.registration;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.worldcooking.server.entity.event.Subscription;
import org.worldcooking.server.services.EventService;
import org.worldcooking.server.services.subscription.SubscriptionService;

@Controller
@RequestMapping(value = "/registration/confirmation/paypal")
public class PaypalRegistrationConfirmationController {

	@Autowired
	private SubscriptionService subscriptionService;

	@Autowired
	private EventService eventService;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * Paypal form action: <br/>
	 * <ul>
	 * <li>https://www.paypal.com/cgi-bin/webscr: production URL</li>
	 * <li>https://www.sandbox.paypal.com/cgi-bin/webscr: testing URL</li>
	 * </ul>
	 */
	@Value("${registration.paypal.form.action}")
	private String paypalFormAction;

	/**
	 * Paypal form 'item-name' field.
	 */
	@Value("${registration.paypal.item.name}")
	private String paypalItemName;

	/**
	 * Paypal form 'business' field.
	 */
	@Value("${registration.paypal.business.email.address}")
	private String paypalBusinessEmailAddress;

	/**
	 * @param eventId
	 *            query parameter
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String initializeForm(@RequestParam Long subscriptionId,
			ModelMap model) throws Exception {
		// TODO do not throw exception: manage errors!

		// TODO manage errors
		Assert.notNull(subscriptionId);

		Subscription subscription = subscriptionService
				.findSubscriptionById(subscriptionId);

		model.addAttribute("event", subscription.getEvent());

		// calculate amount
		Double paypalAmount = subscriptionService
				.calculateSubscriptionPrice(subscription.getParticipants());

		// Paypal form 'amount' field.
		model.addAttribute("paypalAmount", paypalAmount);

		// Paypal form 'item_number' field.
		Long paypalItemNumber = 1L;
		model.addAttribute("paypalItemNumber", paypalItemNumber);

		return "registration/paypalRegistrationConfirmation";
	}

	/**
	 * Paypal form action: <br/>
	 * <ul>
	 * <li>https://www.paypal.com/cgi-bin/webscr: production URL</li>
	 * <li>https://www.sandbox.paypal.com/cgi-bin/webscr: testing URL</li>
	 * </ul>
	 * 
	 * @return
	 */
	@ModelAttribute("paypalFormAction")
	public String populatePaypalFormAction() {
		return paypalFormAction;
	}

	/**
	 * Paypal form 'item-name' field.
	 * 
	 * @return
	 */
	@ModelAttribute("paypalItemName")
	public String populatePaypalItemName() {
		return paypalItemName;
	}

	/**
	 * Paypal form 'business' field.
	 * 
	 * @return
	 */
	@ModelAttribute("paypalBusinessEmailAddress")
	public String populatePaypalBusinessEmailAddress() {
		return paypalBusinessEmailAddress;
	}

	/**
	 * Paypal form 'return' field.
	 * 
	 * @param request
	 * @return
	 */
	@ModelAttribute("paypalReturnUrl")
	public String populatePaypalReturnUrl(HttpServletRequest request) {
		return buildAppUrl(request);
	}

	private String buildAppUrl(HttpServletRequest request) {
		return request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort() + request.getContextPath();
	}

	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}
}
