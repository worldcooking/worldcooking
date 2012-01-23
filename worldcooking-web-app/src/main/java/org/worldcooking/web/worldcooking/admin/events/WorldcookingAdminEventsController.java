/**
 * 
 */
package org.worldcooking.web.worldcooking.admin.events;

import java.util.Collection;

import org.mishk.business.event.entity.Event;
import org.mishk.business.event.service.EventService;
import org.oupsasso.mishk.core.dao.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.worldcooking.web.worldcooking.admin.events.model.EventToEdit;
import org.worldcooking.web.worldcooking.admin.events.model.transform.EventToViewModelTransformer;

/**
 * @author MatthieuG
 * 
 */
@Controller
public class WorldcookingAdminEventsController {

	private static final String URL = "/admin/events";
	private static final String JSP = "site/admin/events/worldcooking-admin-events";
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
	public Collection<EventToEdit> populateEvents() throws ServiceException {
		Collection<Event> events = eventService.findAllEvents(true);

		return eventTransformer.transform(events);

	}
}
