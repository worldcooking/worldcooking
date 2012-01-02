/**
 * 
 */
package org.worldcooking.web.worldcooking.admin.event.registration;

import javax.servlet.http.HttpSession;

import org.oupsasso.mishk.core.dao.exception.EntityIdNotFountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.worldcooking.server.entity.event.Registration;
import org.worldcooking.server.services.registration.RegistrationService;

/**
 * @author MatthieuG
 * 
 */
@Controller
public class WorldcookingAdminEventRegistrationValidationController {

	/**
	 * JSP URL
	 */
	private static final String URL = "/admin/event/{eventReference}/validate/registration";

	/**
	 * AJAX URL ("/direct" = "no SiteMesh decoration", @see decorators.xml)
	 */
	private static final String AJAX_URL = "/direct" + URL;

	@Autowired
	private RegistrationService registrationService;

	@RequestMapping(value = AJAX_URL)
	public @ResponseBody
	String handleAjaxRequest(HttpSession session, @PathVariable String eventReference, @RequestParam Long registrationId)
			throws EntityIdNotFountException {
		Registration s = registrationService.validatePayment(registrationId);

		// TODO manage errors on client side

		// email is returned for debugging purpose
		return s.getEmail();
	}

	@RequestMapping(value = URL, method = RequestMethod.GET)
	public String handleRequest(@PathVariable String eventReference, @RequestParam Long registrationId)
			throws EntityIdNotFountException {

		Registration s = registrationService.validatePayment(registrationId);

		return "redirect:/admin/event/" + s.getEvent().getReference();
	}

}
