/**
 * 
 */
package org.worldcooking.web.worldcooking.admin.event;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.worldcooking.server.entity.event.Event;
import org.worldcooking.server.entity.event.Registration;
import org.worldcooking.server.entity.event.Task;
import org.worldcooking.server.entity.payment.PaymentMode;
import org.worldcooking.server.entity.people.Participant;
import org.worldcooking.server.exception.EntityIdNotFountException;
import org.worldcooking.server.services.EventService;
import org.worldcooking.server.services.registration.RegistrationService;
import org.worldcooking.web.worldcooking.admin.event.model.WorldcookingAdminEventParticipant;
import org.worldcooking.web.worldcooking.admin.event.model.WorldcookingAdminEventRegistration;
import org.worldcooking.web.worldcooking.admin.event.model.WorldcookingAdminEventRegistrer;
import org.worldcooking.web.worldcooking.admin.event.model.WorldcookingAdminEventTask;
import org.worldcooking.web.worldcooking.admin.event.model.WorldcookingAdminEventTaskForm;

/**
 * @author MatthieuG
 * 
 */
@Controller
public class WorldcookingAdminEventController {

	private static final String URL = "/admin/event";
	private static final String JSP = "worldcooking/admin/event/worldcooking-admin-event";
	@Autowired
	private EventService eventService;

	@Autowired
	private RegistrationService registrationService;

	@RequestMapping(value = URL, method = RequestMethod.GET)
	public ModelAndView handleRequest(@RequestParam Long eventId)
			throws EntityIdNotFountException {
		ModelAndView modelAndView = new ModelAndView(JSP);

		// TODO manage errors
		Assert.notNull(eventId);

		Event event = eventService.findById(eventId);

		modelAndView.addObject("event", event);

		SortedSet<Registration> nonValidatedRegistrations = registrationService
				.findNonValidatedRegistrations(eventId);

		modelAndView.addObject("nonValidatedRegistrations",
				registrationsToModel(nonValidatedRegistrations));

		SortedSet<Registration> validatedRegistrations = registrationService
				.findValidatedRegistrations(eventId);

		modelAndView.addObject("validatedRegistrations",
				registrationsToModel(validatedRegistrations));

		WorldcookingAdminEventTaskForm taskModel = new WorldcookingAdminEventTaskForm();

		modelAndView.addObject("taskForm", taskModel);

		return modelAndView;
	}

	@ModelAttribute("tasks")
	public List<Task> populateTasks() {

		Event lastEvent = eventService.getLastEvent();
		List<Task> tasks = eventService.findAllTasks(lastEvent.getId());

		return tasks;
	}

	private List<WorldcookingAdminEventRegistration> registrationsToModel(
			SortedSet<Registration> nonValidatedRegistrations)
			throws EntityIdNotFountException {
		List<WorldcookingAdminEventRegistration> registrations = new ArrayList<WorldcookingAdminEventRegistration>();

		for (Registration reg : nonValidatedRegistrations) {
			WorldcookingAdminEventRegistration registration = new WorldcookingAdminEventRegistration();
			registration.setId(reg.getId());
			WorldcookingAdminEventRegistrer r = new WorldcookingAdminEventRegistrer();

			WorldcookingAdminEventParticipant pm = new WorldcookingAdminEventParticipant();

			pm.setName(reg.getSubscriberParticipant().getName());
			pm.setId(reg.getSubscriberParticipant().getId());

			r.setParticipant(pm);

			r.setEmail(reg.getEmail());
			Task task = reg.getSubscriberParticipant().getTask();
			WorldcookingAdminEventTask taskModel = new WorldcookingAdminEventTask(task.getName(), task.getId());
			pm.setTask(taskModel);
			registration.setRegistrer(r);

			List<WorldcookingAdminEventParticipant> additionalParticipants = new ArrayList<WorldcookingAdminEventParticipant>();

			for (Participant p : reg.getParticipants()) {
				if (!p.equals(reg.getSubscriberParticipant())) {
					WorldcookingAdminEventParticipant ap = new WorldcookingAdminEventParticipant();
					ap.setName(p.getName());
					ap.setId(p.getId());
					Task pTask = p.getTask();
					WorldcookingAdminEventTask pTaskModel = new WorldcookingAdminEventTask(pTask.getName(),
							pTask.getId());
					ap.setTask(pTaskModel);
					additionalParticipants.add(ap);
				}
			}

			if (reg.getPayment().getMode() == PaymentMode.PAYPAL) {
				registration.setPaymentDescription("paypal");
			} else {
				registration.setPaymentDescription(reg.getPayment()
						.getReference());
			}

			Double amount = registrationService.calculateRegistrationPrice(reg
					.getParticipants());
			registration.setAmount(amount);

			registration.setAdditionalParticipants(additionalParticipants);

			registrations.add(registration);
		}

		return registrations;
	}
}
