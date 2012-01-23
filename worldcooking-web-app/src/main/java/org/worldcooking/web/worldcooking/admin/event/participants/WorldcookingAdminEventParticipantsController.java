/**
 * 
 */
package org.worldcooking.web.worldcooking.admin.event.participants;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.mishk.business.event.entity.Event;
import org.mishk.business.event.entity.EventRole;
import org.mishk.business.event.entity.Registration;
import org.mishk.business.event.entity.RegistrationStatus;
import org.mishk.business.event.service.EventService;
import org.mishk.business.event.service.RegistrationService;
import org.mishk.core.dao.exception.EntityReferenceNotFoundException;
import org.mishk.core.dao.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
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

	private static final String REGISTRATION_JSP = "site/admin/event/participants/worldcooking-admin-event-registrations";
	private static final String URL = "/direct/admin/event/{eventReference}/participants";
	private static final String JSP = "site/admin/event/participants/worldcooking-admin-event-participants";
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

		ModelAndView modelAndView = new ModelAndView(REGISTRATION_JSP);

		Event event = eventService.findEventByReference(eventReference, false);

		modelAndView.addObject("event", event);

		Set<Registration> registrations = registrationService.findRegistrationsByStatus(event.getId(),
				RegistrationStatus.VALIDATED, true);

		modelAndView.addObject("registrations", registrationToViewModelTransformer.transform(registrations));

		return modelAndView;
	}

	/**
	 * AJAX URL ("/direct" = "no SiteMesh decoration", @see decorators.xml)
	 */
	private static final String AJAX_URL_UNVALIDATED_REGISTRATION = "/direct/admin/event/{eventReference}/unvalidated/registrations";

	@RequestMapping(value = AJAX_URL_UNVALIDATED_REGISTRATION)
	public ModelAndView showUnvalidatedRegistrationsAjax(@PathVariable String eventReference) throws ServiceException {

		ModelAndView modelAndView = new ModelAndView(REGISTRATION_JSP);

		Event event = eventService.findEventByReference(eventReference, false);

		modelAndView.addObject("event", event);

		Set<Registration> registrations = registrationService.findRegistrationsByStatus(event.getId(),
				RegistrationStatus.PENDING, true);

		modelAndView.addObject("registrations", registrationToViewModelTransformer.transform(registrations));

		return modelAndView;
	}

	@RequestMapping(value = URL, method = RequestMethod.GET)
	public ModelAndView handleRequest(@PathVariable String eventReference) throws ServiceException {
		ModelAndView modelAndView = new ModelAndView(JSP);

		Event event = eventService.findEventByReference(eventReference, true);

		modelAndView.addObject("event", event);

		Set<Registration> nonValidatedRegistrations = registrationService.findRegistrationsByStatus(event.getId(),
				RegistrationStatus.PENDING, true);

		modelAndView.addObject("nonValidatedRegistrations",
				registrationToViewModelTransformer.transform(nonValidatedRegistrations));

		Set<Registration> validatedRegistrations = registrationService.findRegistrationsByStatus(event.getId(),
				RegistrationStatus.VALIDATED, true);

		modelAndView.addObject("validatedRegistrations",
				registrationToViewModelTransformer.transform(validatedRegistrations));

		WorldcookingAdminEventTaskForm taskModel = new WorldcookingAdminEventTaskForm();

		modelAndView.addObject("taskForm", taskModel);

		return modelAndView;
	}

	@ModelAttribute("eventRoles")
	public List<EventRole> populateTasks(@PathVariable String eventReference) throws EntityReferenceNotFoundException {

		Event event = eventService.findEventByReference(eventReference, false);
		List<EventRole> eventRoles = eventService.findAllEventRoles(event.getId(), false);

		return eventRoles;
	}

	@ModelAttribute("history")
	public WorldcookingHistory getHistory(HttpSession session) {
		return WorldcookingHistoryController.getHistory(session);
	}

}
