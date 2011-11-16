/**
 * 
 */
package org.worldcooking.web.admin.event.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.worldcooking.server.entity.people.Participant;
import org.worldcooking.server.exception.EntityIdNotFountException;
import org.worldcooking.server.services.registration.RegistrationService;

/**
 * @author MatthieuG
 * 
 */
@Controller
public class AdminEventUpdateTaskController {

	@Autowired
	private RegistrationService registrationService;

	@RequestMapping(value = "/admin/event/update/task", method = RequestMethod.POST)
	public String handleRequest(@RequestParam Long participantId,
			@RequestParam Long taskId) throws EntityIdNotFountException {

		Participant participant = registrationService.updateTask(participantId,
				taskId);

		return "redirect:/admin/event?eventId="
				+ participant.getRegistration().getEvent().getId();
	}

}
