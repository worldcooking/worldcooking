package org.worldcooking.web.worldcooking.registration.confirmation;

import javax.servlet.http.HttpServletRequest;

import org.mishk.business.event.entity.Event;
import org.mishk.business.event.entity.Registration;
import org.mishk.business.event.entity.RegistrationStatus;
import org.mishk.business.event.service.EventService;
import org.mishk.business.event.service.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.worldcooking.service.admin.WorldcookingService;

@Controller
@RequestMapping(value = "/event/{eventReference}/registration/confirmation/paypal")
public class WorldcookingPaypalConfirmationController {

	private static final String JSP = "worldcooking/registration/confirmation/worldcooking-paypal-confirmation";

	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private WorldcookingService worldcookingService;

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
	 * Paypal form 'return' field.
	 * 
	 */
	@Value("${registration.paypal.return.url}")
	private String paypalReturnUrl;

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
	 * Paypal form 'currency_code' field.
	 */
	@Value("${registration.paypal.currency}")
	private String paypalCurrencyCode;

	/**
	 * @param eventId
	 *            query parameter
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String initializeForm(@PathVariable String eventReference, @RequestParam Long registrationId, ModelMap model)
			throws Exception {

		Event event = eventService.findEventByReference(eventReference, false);

		if (!eventService.isEventOpen(event)) {
			logger.warn("Attempt to access to closed registration of event '{}'.", event.getName());
			return "redirect:/";
		}

		Registration registration = registrationService.findRegistrationById(registrationId, false);

		if (registration.getRegistrationStatus() == RegistrationStatus.VALIDATED) {
			// registration already validated: redirect to welcome page
			return "redirect:/";
		}

		model.addAttribute("event", registration.getEvent());

		// calculate amount
		Double paypalAmount = worldcookingService.calculateRegistrationPrice(registrationId);

		// Paypal form 'amount' field.
		model.addAttribute("paypalAmount", paypalAmount);

		// Paypal form 'item_number' field.
		Long paypalItemNumber = 1L;
		model.addAttribute("paypalItemNumber", paypalItemNumber);

		return JSP;
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
	 * Paypal form 'currency_code' field.
	 * 
	 * @return
	 */
	@ModelAttribute("paypalCurrencyCode")
	public String populatePaypalCurrencyCode() {
		return paypalCurrencyCode;
	}

	/**
	 * Paypal form 'return' field.
	 * 
	 * @param request
	 * @return
	 */
	@ModelAttribute("paypalReturnUrl")
	public String populatePaypalReturnUrl(HttpServletRequest request) {
		return paypalReturnUrl;
	}

	private String buildAppUrl(HttpServletRequest request) {
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
	}

	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

}
