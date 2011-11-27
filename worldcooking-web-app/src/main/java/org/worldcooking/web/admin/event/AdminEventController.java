/**
 * 
 */
package org.worldcooking.web.admin.event;

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
import org.worldcooking.web.admin.event.form.TaskForm;
import org.worldcooking.web.admin.event.model.ParticipantModel;
import org.worldcooking.web.admin.event.model.RegistrationModel;
import org.worldcooking.web.admin.event.model.RegistrerModel;
import org.worldcooking.web.admin.event.model.TaskModel;

/**
 * @author MatthieuG
 * 
 */
@Controller
public class AdminEventController {

	private static final String URL = "/admin/event";
	private static final String JSP = "admin/admin-event";
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

		TaskForm taskModel = new TaskForm();

		modelAndView.addObject("taskForm", taskModel);

		return modelAndView;
	}

	@ModelAttribute("tasks")
	public List<Task> populateTasks() {

		Event lastEvent = eventService.getLastEvent();
		List<Task> tasks = eventService.findAllTasks(lastEvent.getId());

		return tasks;
	}

	private List<RegistrationModel> registrationsToModel(
			SortedSet<Registration> nonValidatedRegistrations)
			throws EntityIdNotFountException {
		List<RegistrationModel> registrations = new ArrayList<RegistrationModel>();

		for (Registration reg : nonValidatedRegistrations) {
			RegistrationModel registration = new RegistrationModel();
			registration.setId(reg.getId());
			RegistrerModel r = new RegistrerModel();

			ParticipantModel pm = new ParticipantModel();

			pm.setName(reg.getSubscriberParticipant().getName());
			pm.setId(reg.getSubscriberParticipant().getId());

			r.setParticipant(pm);

			r.setEmail(reg.getEmail());
			Task task = reg.getSubscriberParticipant().getTask();
			TaskModel taskModel = new TaskModel(task.getName(), task.getId());
			pm.setTask(taskModel);
			registration.setRegistrer(r);

			List<ParticipantModel> additionalParticipants = new ArrayList<ParticipantModel>();

			for (Participant p : reg.getParticipants()) {
				if (!p.equals(reg.getSubscriberParticipant())) {
					ParticipantModel ap = new ParticipantModel();
					ap.setName(p.getName());
					ap.setId(p.getId());
					Task pTask = p.getTask();
					TaskModel pTaskModel = new TaskModel(pTask.getName(),
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
