/**
 * 
 */
package org.worldcooking.web.worldcooking.history;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.worldcooking.server.exception.EntityIdNotFountException;
import org.worldcooking.web.worldcooking.history.model.WorldcookingHistory;
import org.worldcooking.web.worldcooking.history.model.WorldcookingHistoryEntry;

/**
 * @author MatthieuG
 * 
 */
@Controller
public class WorldcookingHistoryController {

	private static final String URL = "/direct/history";
	private static final String JSP = "history";

	@RequestMapping(value = URL, method = RequestMethod.GET)
	public ModelAndView handleRequest(HttpSession session)
			throws EntityIdNotFountException {

		ModelAndView modelAndView = new ModelAndView(JSP);

		modelAndView.addObject("history", getHistory(session));

		return modelAndView;
	}

	public static WorldcookingHistory addToHistory(HttpSession session,
			WorldcookingHistoryEntry historyEntry) {
		// retrieve (or create) history from session
		WorldcookingHistory historyModel = getHistory(session);
		// add entry to history
		historyModel.add(historyEntry);
		// return history model
		return historyModel;
	}

	public static WorldcookingHistory getHistory(HttpSession session) {
		WorldcookingHistory historyModel = (WorldcookingHistory) session
				.getAttribute("history");

		if (historyModel == null) {
			historyModel = new WorldcookingHistory();
			session.setAttribute("history", historyModel);
		}
		return historyModel;
	}

}
