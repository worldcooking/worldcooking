/**
 * 
 */
package org.worldcooking.web.worldcooking.admin.event.registration;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.worldcooking.server.entity.event.Task;
import org.worldcooking.server.entity.people.Participant;
import org.worldcooking.server.exception.EntityIdNotFountException;
import org.worldcooking.server.services.registration.RegistrationService;
import org.worldcooking.web.worldcooking.admin.event.model.WorldcookingAdminEventParticipantRegistration;
import org.worldcooking.web.worldcooking.history.WorldcookingHistoryController;
import org.worldcooking.web.worldcooking.history.model.WorldcookingHistoryEntry;
import org.worldcooking.web.worldcooking.history.model.WorldcookingHistoryMessageFragment.FragmentType;

/**
 * @author MatthieuG
 * 
 */
@Controller
public class WorldcookingAdminEventUpdateTaskController {

	/**
	 * JSP URL
	 */
	private static final String URL = "/admin/event/update/task";

	/**
	 * AJAX URL ("/direct" = "no SiteMesh decoration", @see decorators.xml)
	 */
	private static final String AJAX_URL = "/direct" + URL;

	@Autowired
	private RegistrationService registrationService;

	@RequestMapping(value = AJAX_URL)
	public @ResponseBody
	WorldcookingAdminEventParticipantRegistration handleAjaxRequest(HttpSession session,
			@RequestParam Long participantId, @RequestParam Long taskId)
			throws EntityIdNotFountException {

		Participant participant = updateTask(session, participantId, taskId);

		WorldcookingAdminEventParticipantRegistration participantRegistrationModel = new WorldcookingAdminEventParticipantRegistration();
		participantRegistrationModel.setTaskId(participant.getTask().getId());
		Double amount = registrationService
				.calculateRegistrationPrice(participant.getRegistration()
						.getId());
		participantRegistrationModel.setNewAmount(amount);

		return participantRegistrationModel;
	}

	@RequestMapping(value = URL)
	public String handleRequest(HttpSession session,
			@RequestParam Long participantId, @RequestParam Long taskId)
			throws EntityIdNotFountException {

		Participant participant = updateTask(session, participantId, taskId);

		return "redirect:/admin/event?eventId="
				+ participant.getRegistration().getEvent().getId();
	}

	/**
	 * Classic call
	 * 
	 * @param session
	 * @param participantId
	 * @param taskId
	 * @return
	 * @throws EntityIdNotFountException
	 */
	private Participant updateTask(HttpSession session, Long participantId,
			Long taskId) throws EntityIdNotFountException {
		Task previousTask = registrationService.updateTask(participantId,
				taskId);

		Participant participant = registrationService
				.findParticipantById(participantId);

		// TODO dynamic undo link
		String undoLink = "/worldcooking-web-app/admin/event/update/task?participantId="
				+ participantId + "&taskId=" + previousTask.getId();

		WorldcookingHistoryEntry historyEntry = new WorldcookingHistoryEntry(undoLink)
				.addMessageFragment("Updated task from '")
				.addMessageFragment(previousTask.getName(),
						FragmentType.IMPORTANT)
				.addMessageFragment(" ' to '")
				.addMessageFragment(participant.getTask().getName(),
						FragmentType.IMPORTANT)
				.addMessageFragment("' for participant '")
				.addMessageFragment(participant.getName(),
						FragmentType.IMPORTANT).addMessageFragment("'.");

		WorldcookingHistoryController.addToHistory(session, historyEntry);
		return participant;
	}

}
