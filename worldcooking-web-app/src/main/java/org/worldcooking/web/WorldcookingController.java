package org.worldcooking.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.worldcooking.server.entity.event.Event;
import org.worldcooking.server.entity.event.Task;
import org.worldcooking.server.services.EventService;

/**
 * Simple index page controller serving hello.jsp file
 */
@Controller
public class WorldcookingController {

	@Autowired
	private EventService eventService;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 
	 * @return view with name
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView handleRequest() {
		Event e = eventService.getFullEventById(10L);

		List<Task> availableTasks = e.getAvailableTasks();
		if (availableTasks != null) {
			System.out.println("Tasks nb:" + availableTasks.size());
			logger.error("Tasks nb:" + availableTasks.size());
		} else {
			System.out.println("No Tasks");
			logger.error("Tasks");
		}
		ModelAndView modelAndView = new ModelAndView("worldcookingperu");
		modelAndView.addObject("event", e);
		return modelAndView;
	}
}
