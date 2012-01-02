/**
 * 
 */
package org.worldcooking.web.worldcooking.admin.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.worldcooking.server.services.EventService;

/**
 * @author MatthieuG
 * 
 */
@Controller
public class WorldcookingAdminDbController {

	private static final String JSP = "worldcooking/admin/db/worldcooking-admin-db";
	@Autowired
	private EventService eventService;

	@RequestMapping(value = "/admin/db", method = RequestMethod.GET)
	public ModelAndView handleRequest() {
		ModelAndView modelAndView = new ModelAndView(JSP);

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit() throws Exception {
		// reset DB
		eventService.resetDb();

		// redirect to main page
		return "redirect:/";
	}
}
