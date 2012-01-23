/**
 * 
 */
package org.worldcooking.web.worldcooking.admin.event.participants.registration;

import javax.servlet.http.HttpSession;

import org.mishk.business.event.entity.EventRole;
import org.mishk.business.event.entity.Participant;
import org.mishk.business.event.service.EventService;
import org.mishk.business.event.service.RegistrationService;
import org.mishk.business.shop.exception.InsufficientStockException;
import org.mishk.core.dao.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.worldcooking.service.admin.WorldcookingService;
import org.worldcooking.web.worldcooking.admin.event.participants.model.WorldcookingAdminEventParticipantRegistration;
import org.worldcooking.web.worldcooking.history.WorldcookingHistoryController;
import org.worldcooking.web.worldcooking.history.model.WorldcookingHistoryEntry;
import org.worldcooking.web.worldcooking.history.model.WorldcookingHistoryMessageFragment.FragmentType;

/**
 * @author MatthieuG
 * 
 */
@Controller
public class WorldcookingAdminEventUpdateRoleController {

	/**
	 * JSP URL
	 */
	private static final String URL = "/admin/event/{eventReference}/update/role";

	/**
	 * AJAX URL ("/direct" = "no SiteMesh decoration", @see decorators.xml)
	 */
	private static final String AJAX_URL = "/direct" + URL;

	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private WorldcookingService worldcookingService;

	@Autowired
	private EventService eventService;

	@RequestMapping(value = AJAX_URL)
	public @ResponseBody
	WorldcookingAdminEventParticipantRegistration handleAjaxRequest(HttpSession session,
			@PathVariable String eventReference, @RequestParam Long participantId, @RequestParam Long eventRoleId)
			throws EntityNotFoundException, InsufficientStockException {

		Participant participant = updateRole(session, eventReference, participantId, eventRoleId);

		WorldcookingAdminEventParticipantRegistration participantRegistrationModel = new WorldcookingAdminEventParticipantRegistration();
		participantRegistrationModel.setEventRoleId(participant.getEventRole().getId());
		Double amount = worldcookingService.calculateRegistrationPrice(participant.getRegistration().getId());
		participantRegistrationModel.setNewAmount(amount);

		return participantRegistrationModel;
	}

	@RequestMapping(value = URL)
	public String handleRequest(HttpSession session, @PathVariable String eventReference,
			@RequestParam Long participantId, @RequestParam Long eventRoleId) throws EntityNotFoundException,
			InsufficientStockException {

		Participant participant = updateRole(session, eventReference, participantId, eventRoleId);

		return "redirect:/admin/event/" + participant.getRegistration().getEvent().getReference();
	}

	/**
	 * Classic call
	 * 
	 * @param session
	 * @param participantId
	 * @param eventRoleId
	 * @return
	 * @throws InsufficientStockException
	 * @throws EntityNotFoundException
	 */
	private Participant updateRole(HttpSession session, String eventReference, Long participantId, Long eventRoleId)
			throws EntityNotFoundException {
		Participant participant = registrationService.findParticipantById(participantId);

		EventRole previousEventRole = participant.getEventRole();
		EventRole newEventRole = eventService.findEventRoleById(eventRoleId);
		try {
			participant = worldcookingService.updateParticipantEventRole(participantId, eventRoleId);

			String undoLink = "/admin/event/" + eventReference + "/update/role?participantId=" + participantId
					+ "&eventRoleId=" + previousEventRole.getId();

			WorldcookingHistoryEntry historyEntry = new WorldcookingHistoryEntry(undoLink)
					.addMessageFragment("Updated role from '")
					.addMessageFragment(previousEventRole.getRole().getName(), FragmentType.IMPORTANT)
					.addMessageFragment(" ' to '")
					.addMessageFragment(newEventRole.getRole().getName(), FragmentType.IMPORTANT)
					.addMessageFragment("' for participant '")
					.addMessageFragment(participant.getName(), FragmentType.IMPORTANT).addMessageFragment("'.");

			WorldcookingHistoryController.addToHistory(session, historyEntry);
		} catch (InsufficientStockException e) {

			WorldcookingHistoryEntry historyEntry = new WorldcookingHistoryEntry(null)
					.addMessageFragment("Not able to update role from '")
					.addMessageFragment(previousEventRole.getRole().getName(), FragmentType.IMPORTANT)
					.addMessageFragment(" ' to '")
					.addMessageFragment(newEventRole.getRole().getName(), FragmentType.IMPORTANT)
					.addMessageFragment("' for participant '")
					.addMessageFragment(participant.getName(), FragmentType.IMPORTANT)
					.addMessageFragment("' because there is no available place.");

			WorldcookingHistoryController.addToHistory(session, historyEntry);
		}

		return participant;
	}

}
