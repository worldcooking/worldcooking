/**
 * 
 */
package org.worldcooking.web.worldcooking.admin.event.participants;

import java.util.List;
import java.util.SortedSet;

import javax.servlet.http.HttpSession;

import org.oupsasso.mishk.core.dao.exception.EntityIdNotFountException;
import org.oupsasso.mishk.core.dao.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.worldcooking.server.entity.event.Event;
import org.worldcooking.server.entity.event.Registration;
import org.worldcooking.server.entity.event.Task;
import org.worldcooking.server.services.EventService;
import org.worldcooking.server.services.registration.RegistrationService;
import org.worldcooking.web.worldcooking.admin.event.participants.model.WorldcookingAdminEventTaskForm;
import org.worldcooking.web.worldcooking.admin.event.participants.model.transform.RegistrationToViewModelTransformer;
import org.worldcooking.web.worldcooking.history.WorldcookingHistoryController;
import org.worldcooking.web.worldcooking.history.model.WorldcookingHistory;

/**
 * @author MatthieuG
 * 
 */
@Controller
public class WorldcookingAdminEventParticipantsController {

	private static final String URL = "/admin/event/{eventReference}/participants";
	private static final String JSP = "worldcooking/admin/event/participants/worldcooking-admin-event-participants";
	@Autowired
	private EventService eventService;

	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private RegistrationToViewModelTransformer registrationToViewModelTransformer;
	/**
	 * AJAX URL ("/direct" = "no SiteMesh decoration", @see decorators.xml)
	 */
	private static final String AJAX_URL_VALIDATED_REGISTRATION = "/direct/admin/event/{eventReference}/validated/registrations";

	@RequestMapping(value = AJAX_URL_VALIDATED_REGISTRATION)
	public ModelAndView showValidatedRegistrationsAjax(@PathVariable String eventReference) throws ServiceException {

		ModelAndView modelAndView = new ModelAndView("worldcooking/admin/event/worldcooking-admin-event-registrations");

		Event event = eventService.findByReference(eventReference);

		modelAndView.addObject("event", event);

		SortedSet<Registration> registrations = registrationService.findValidatedRegistrations(event.getId());

		modelAndView.addObject("registrations", registrationToViewModelTransformer.transform(registrations));

		return modelAndView;
	}

	/**
	 * AJAX URL ("/direct" = "no SiteMesh decoration", @see decorators.xml)
	 */
	private static final String AJAX_URL_UNVALIDATED_REGISTRATION = "/direct/admin/event/{eventReference}/unvalidated/registrations";

	@RequestMapping(value = AJAX_URL_UNVALIDATED_REGISTRATION)
	public ModelAndView showUnvalidatedRegistrationsAjax(@PathVariable String eventReference) throws ServiceException {

		ModelAndView modelAndView = new ModelAndView("worldcooking/admin/event/worldcooking-admin-event-registrations");

		Event event = eventService.findByReference(eventReference);

		modelAndView.addObject("event", event);

		SortedSet<Registration> registrations = registrationService.findNonValidatedRegistrations(event.getId());

		modelAndView.addObject("registrations", registrationToViewModelTransformer.transform(registrations));

		return modelAndView;
	}

	@RequestMapping(value = URL, method = RequestMethod.GET)
	public ModelAndView handleRequest(@PathVariable String eventReference) throws ServiceException {
		ModelAndView modelAndView = new ModelAndView(JSP);

		Event event = eventService.findByReference(eventReference);

		modelAndView.addObject("event", event);

		SortedSet<Registration> nonValidatedRegistrations = registrationService.findNonValidatedRegistrations(event
				.getId());

		modelAndView.addObject("nonValidatedRegistrations",
				registrationToViewModelTransformer.transform(nonValidatedRegistrations));

		SortedSet<Registration> validatedRegistrations = registrationService.findValidatedRegistrations(event.getId());

		modelAndView.addObject("validatedRegistrations",
				registrationToViewModelTransformer.transform(validatedRegistrations));

		WorldcookingAdminEventTaskForm taskModel = new WorldcookingAdminEventTaskForm();

		modelAndView.addObject("taskForm", taskModel);

		return modelAndView;
	}

	@ModelAttribute("tasks")
	public List<Task> populateTasks(@PathVariable String eventReference) throws EntityIdNotFountException {

		Event event = eventService.findByReference(eventReference);
		List<Task> tasks = eventService.findAllTasks(event.getId());

		return tasks;
	}

	@ModelAttribute("history")
	public WorldcookingHistory getHistory(HttpSession session) {
		return WorldcookingHistoryController.getHistory(session);
	}

}
