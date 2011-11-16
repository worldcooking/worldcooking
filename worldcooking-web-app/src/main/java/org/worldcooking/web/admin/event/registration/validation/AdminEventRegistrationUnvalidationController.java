/**
 * 
 */
package org.worldcooking.web.admin.event.registration.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.worldcooking.server.entity.event.Registration;
import org.worldcooking.server.exception.EntityIdNotFountException;
import org.worldcooking.server.services.registration.RegistrationService;

/**
 * @author MatthieuG
 * 
 */
@Controller
public class AdminEventRegistrationUnvalidationController {

	@Autowired
	private RegistrationService registrationService;

	@RequestMapping(value = "/admin/event/unvalidate/registration", method = RequestMethod.GET)
	public String handleRequest(@RequestParam Long registrationId)
			throws EntityIdNotFountException {

		Registration s = registrationService.unvalidatePayment(registrationId);

		return "redirect:/admin/event?eventId=" + s.getEvent().getId();
	}

}
