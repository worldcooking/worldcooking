/**
 * 
 */
package org.worldcooking.web.history;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.worldcooking.server.exception.EntityIdNotFountException;

/**
 * @author MatthieuG
 * 
 */
@Controller
public class HistoryController {

	private static final String URL = "/direct/history";
	private static final String JSP = "history";

	@RequestMapping(value = URL, method = RequestMethod.GET)
	public ModelAndView handleRequest(HttpSession session)
			throws EntityIdNotFountException {

		ModelAndView modelAndView = new ModelAndView(JSP);

		modelAndView.addObject("history", getHistory(session));

		return modelAndView;
	}

	public static HistoryModel addToHistory(HttpSession session,
			HistoryEntry historyEntry) {
		// retrieve (or create) history from session
		HistoryModel historyModel = getHistory(session);
		// add entry to history
		historyModel.add(historyEntry);
		// return history model
		return historyModel;
	}

	private static HistoryModel getHistory(HttpSession session) {
		HistoryModel historyModel = (HistoryModel) session
				.getAttribute("history");

		if (historyModel == null) {
			historyModel = new HistoryModel();
			session.setAttribute("history", historyModel);
		}
		return historyModel;
	}

}
