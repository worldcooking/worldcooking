/**
 * 
 */
package org.worldcooking.web.worldcooking.admin.event.participants.registration;

import javax.servlet.http.HttpSession;

import org.mishk.business.event.entity.Participant;
import org.mishk.business.event.service.RegistrationService;
import org.oupsasso.mishk.core.dao.exception.EntityIdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.worldcooking.web.worldcooking.admin.event.participants.model.WorldcookingAdminEventParticipantRegistration;

/**
 * @author MatthieuG
 * 
 */
@Controller
public class WorldcookingAdminEventUpdateTaskController {

	/**
	 * JSP URL
	 */
	private static final String URL = "/admin/event/{eventReference}/update/task";

	/**
	 * AJAX URL ("/direct" = "no SiteMesh decoration", @see decorators.xml)
	 */
	private static final String AJAX_URL = "/direct" + URL;

	@Autowired
	private RegistrationService registrationService;

	@RequestMapping(value = AJAX_URL)
	public @ResponseBody
	WorldcookingAdminEventParticipantRegistration handleAjaxRequest(HttpSession session,
			@PathVariable String eventReference, @RequestParam Long participantId, @RequestParam Long taskId)
			throws EntityIdNotFoundException {

		Participant participant = updateTask(session, eventReference, participantId, taskId);

		WorldcookingAdminEventParticipantRegistration participantRegistrationModel = new WorldcookingAdminEventParticipantRegistration();
		participantRegistrationModel.setTaskId(participant.getEventRole().getId());
		Double amount = 15d;// TODO
							// registrationService.calculateRegistrationPrice(participant.getRegistration().getId());
		participantRegistrationModel.setNewAmount(amount);

		return participantRegistrationModel;
	}

	@RequestMapping(value = URL)
	public String handleRequest(HttpSession session, @PathVariable String eventReference,
			@RequestParam Long participantId, @RequestParam Long taskId) throws EntityIdNotFoundException {

		Participant participant = updateTask(session, eventReference, participantId, taskId);

		return "redirect:/admin/event/" + participant.getRegistration().getEvent().getReference();
	}

	/**
	 * Classic call
	 * 
	 * @param session
	 * @param participantId
	 * @param taskId
	 * @return
	 * @throws EntityIdNotFoundException
	 */
	private Participant updateTask(HttpSession session, String eventReference, Long participantId, Long taskId)
			throws EntityIdNotFoundException {
		// TODO Task previousTask =
		// registrationService.updateTask(participantId, taskId);

		Participant participant = registrationService.findParticipantById(participantId);

		// // TODO dynamic undo link
		// String undoLink = "/worldcooking-web-app/admin/event/" +
		// eventReference + "/update/task?participantId="
		// + participantId + "&taskId=" + previousTask.getId();
		//
		// WorldcookingHistoryEntry historyEntry = new
		// WorldcookingHistoryEntry(undoLink)
		// .addMessageFragment("Updated task from '")
		// .addMessageFragment(previousTask.getName(),
		// FragmentType.IMPORTANT).addMessageFragment(" ' to '")
		// .addMessageFragment(participant.getTask().getName(),
		// FragmentType.IMPORTANT)
		// .addMessageFragment("' for participant '")
		// .addMessageFragment(participant.getName(),
		// FragmentType.IMPORTANT).addMessageFragment("'.");
		//
		// WorldcookingHistoryController.addToHistory(session, historyEntry);
		return participant;
	}

}
