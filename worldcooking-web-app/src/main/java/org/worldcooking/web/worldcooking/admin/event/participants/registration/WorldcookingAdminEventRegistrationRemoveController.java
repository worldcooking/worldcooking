/**
 * 
 */
package org.worldcooking.web.worldcooking.admin.event.participants.registration;

import org.mishk.business.event.entity.Registration;
import org.mishk.business.event.service.RegistrationService;
import org.oupsasso.mishk.core.dao.exception.EntityIdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author MatthieuG
 * 
 */
@Controller
public class WorldcookingAdminEventRegistrationRemoveController {

	@Autowired
	private RegistrationService registrationService;

	@RequestMapping(value = "/admin/event/{eventReference}/remove/registration", method = RequestMethod.GET)
	public String handleRequest(@PathVariable String eventReference, @RequestParam Long registrationId)
			throws EntityIdNotFoundException {

		Registration r = registrationService.removeRegistration(registrationId);

		return "redirect:/admin/event/" + r.getEvent().getReference();
	}

}
