/**
 * 
 */
package org.worldcooking.web.worldcooking.admin.event.tasks;

import org.mishk.business.event.entity.Event;
import org.mishk.business.event.service.EventService;
import org.mishk.core.dao.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author MatthieuG
 * 
 */
@Controller
public class WorldcookingAdminEventTasksController {

	private static final String URL = "/direct/admin/event/{eventReference}/tasks";
	private static final String JSP = "site/admin/event/tasks/worldcooking-admin-event-tasks";
	@Autowired
	private EventService eventService;

	@RequestMapping(value = URL, method = RequestMethod.GET)
	public ModelAndView handleRequest(@PathVariable String eventReference) throws ServiceException {
		ModelAndView modelAndView = new ModelAndView(JSP);

		Event event = eventService.findEventByReference(eventReference, false);

		modelAndView.addObject("event", event);

		return modelAndView;
	}

}
