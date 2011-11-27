/**
 * 
 */
package org.worldcooking.web.admin.payment.validation;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedSet;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.worldcooking.server.entity.event.Event;
import org.worldcooking.server.entity.event.Registration;
import org.worldcooking.server.entity.people.Participant;
import org.worldcooking.server.exception.EntityIdNotFountException;
import org.worldcooking.server.services.EventService;
import org.worldcooking.server.services.registration.RegistrationService;

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
	private RegistrationService registrationService;

	@RequestMapping(value = "/admin/payment/validation", method = RequestMethod.GET)
	public ModelAndView handleRequest() {

		ModelAndView modelAndView = new ModelAndView(JSP);
		AdminPaymentValidation form = new AdminPaymentValidation();

		modelAndView.addObject(MODEL_NAME, form);

		return modelAndView;
	}

	@ModelAttribute("registrations")
	public Map<Long, String> populateRegistrations()
			throws EntityIdNotFountException {
		// TODO manage error

		Map<Long, String> registrationsDesc = new LinkedHashMap<Long, String>();

		Event lastEvent = eventService.getLastEvent();

		if (lastEvent != null) {
			SortedSet<Registration> registrations = registrationService
					.findNonValidatedRegistrations(lastEvent.getId());

			registrationsDesc.put(-1L, "");
			for (Registration registration : registrations) {
				double price = registrationService
						.calculateRegistrationPrice(registration
								.getParticipants());

				String description = registration.getEmail() + " - " + price
						+ "€";

				String participantDescription = null;

				for (Participant participant : registration.getParticipants()) {
					if (participantDescription == null) {
						participantDescription = "";
					} else {
						participantDescription += ", ";
					}
					participantDescription += participant.getName();
				}

				description += " (participants: " + participantDescription
						+ ")";

				registrationsDesc.put(registration.getId(), description);
			}
		}

		return registrationsDesc;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(
			@Valid @ModelAttribute(MODEL_NAME) AdminPaymentValidation adminPaymentValidation,
			BindingResult result) throws Exception {

		if (result.hasErrors()) {
			return JSP;
		}

		if ("pépérou".equals(adminPaymentValidation.getPassword())) {
			registrationService.validatePayment(adminPaymentValidation
					.getRegistrationId());
			return JSP;
		}
		// redirect to main page
		return JSP;
	}
}
