/**
 * 
 */
package org.worldcooking.web.worldcooking.admin.event.participants.registration;

import javax.servlet.http.HttpSession;

import org.oupsasso.mishk.business.shop.exception.InsufficientStockException;
import org.oupsasso.mishk.core.dao.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.worldcooking.service.admin.WorldcookingService;
import org.worldcooking.web.worldcooking.history.WorldcookingHistoryController;
import org.worldcooking.web.worldcooking.history.model.WorldcookingHistoryEntry;

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
	void handleAjaxRequest(HttpSession session,
			@PathVariable String eventReference,
			@RequestParam Long registrationId) throws EntityNotFoundException {

		try {
			worldcookingService.validatePayment(registrationId);
		} catch (InsufficientStockException e) {
			WorldcookingHistoryEntry historyEntry = new WorldcookingHistoryEntry(
					null).addMessageFragment(e.getErrorMessage());

			WorldcookingHistoryController.addToHistory(session, historyEntry);
		}
	}

}
