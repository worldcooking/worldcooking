/**
 * 
 */
package org.worldcooking.web.admin;

import java.util.SortedSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.worldcooking.server.entity.event.Event;
import org.worldcooking.server.services.EventService;

/**
 * @author MatthieuG
 * 
 */
@Controller
public class AdminMainController {

	private static final String URL = "/admin";
	private static final String JSP = "admin/admin-main";
	@Autowired
	private EventService eventService;

	@RequestMapping(value = URL, method = RequestMethod.GET)
	public ModelAndView handleRequest() {
		ModelAndView modelAndView = new ModelAndView(JSP);

		return modelAndView;
	}

	@ModelAttribute("events")
	public SortedSet<Event> populateEvents() {
		SortedSet<Event> events = eventService.findAllFullEvents();
		for (Event event : events) {
			// TODO better split in javascript depending of available space
			event.setDescription(event.getDescription().substring(0, 150)
					+ "...");
		}
		return events;
	}
}
