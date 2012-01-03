/**
 * 
 */
package org.worldcooking.web.worldcooking.admin.events;

import java.util.List;
import java.util.SortedSet;

import org.oupsasso.mishk.core.dao.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.worldcooking.server.entity.event.Event;
import org.worldcooking.server.services.EventService;
import org.worldcooking.web.worldcooking.admin.events.model.EventToEdit;
import org.worldcooking.web.worldcooking.admin.events.model.transform.EventToViewModelTransformer;

/**
 * @author MatthieuG
 * 
 */
@Controller
public class WorldcookingAdminEventsController {

	private static final String URL = "/admin/events";
	private static final String JSP = "worldcooking/admin/events/worldcooking-admin-events";
	@Autowired
	private EventService eventService;

	@Autowired
	private EventToViewModelTransformer eventTransformer;

	@RequestMapping(value = URL, method = RequestMethod.GET)
	public ModelAndView handleRequest() {
		ModelAndView modelAndView = new ModelAndView(JSP);

		return modelAndView;
	}

	@ModelAttribute("events")
	public List<EventToEdit> populateEvents() throws ServiceException {
		SortedSet<Event> events = eventService.findAllFullEvents();

		return eventTransformer.transform(events);

	}
}
