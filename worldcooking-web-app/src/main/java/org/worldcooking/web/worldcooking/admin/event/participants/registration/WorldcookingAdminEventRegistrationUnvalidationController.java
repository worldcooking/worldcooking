/**
 * 
 */
package org.worldcooking.web.worldcooking.admin.event.participants.registration;

import javax.servlet.http.HttpSession;

import org.mishk.business.event.entity.Registration;
import org.mishk.core.dao.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.worldcooking.service.admin.WorldcookingService;

/**
 * @author MatthieuG
 * 
 */
@Controller
public class WorldcookingAdminEventRegistrationUnvalidationController {

	/**
	 * JSP URL
	 */
	private static final String URL = "/admin/event/{eventReference}/unvalidate/registration";

	/**
	 * AJAX URL ("/direct" = "no SiteMesh decoration", @see decorators.xml)
	 */
	private static final String AJAX_URL = "/direct" + URL;

	@Autowired
	private WorldcookingService worldcookingService;

	@RequestMapping(value = AJAX_URL)
	public @ResponseBody
	String handleAjaxRequest(HttpSession session,
			@PathVariable String eventReference,
			@RequestParam Long registrationId) throws EntityNotFoundException {
		Registration registration = worldcookingService
				.unvalidatePayment(registrationId);

		// TODO manage errors on client side

		// id is returned for debugging purpose
		return registration.getId().toString();
	}

}
