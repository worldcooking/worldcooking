/**
 * 
 */
package org.worldcooking.web.worldcooking.admin.event.participants.registration;

import javax.servlet.http.HttpSession;

import org.mishk.business.event.entity.Registration;
import org.oupsasso.mishk.core.dao.exception.EntityIdNotFoundException;
import org.oupsasso.mishk.core.dao.exception.EntityReferenceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.worldcooking.service.admin.WorldcookingService;

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
	private WorldcookingService worldcookingService;

	@RequestMapping(value = AJAX_URL)
	public @ResponseBody
	String handleAjaxRequest(HttpSession session, @PathVariable String eventReference, @RequestParam Long registrationId)
			throws EntityIdNotFoundException, EntityReferenceNotFoundException {

		Registration registration = worldcookingService.validatePayment(registrationId);

		// TODO manage errors on client side

		// id is returned for debugging purpose
		return registration.getId().toString();
	}

	@RequestMapping(value = URL, method = RequestMethod.GET)
	public String handleRequest(@PathVariable String eventReference, @RequestParam Long registrationId)
			throws EntityIdNotFoundException {

		Registration s = null;
		// TODO registrationService.validatePayment(registrationId);

		return "redirect:/admin/event/" + s.getEvent().getReference();
	}

}
