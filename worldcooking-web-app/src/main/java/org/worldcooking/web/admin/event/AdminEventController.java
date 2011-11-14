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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.worldcooking.server.entity.event.Event;
import org.worldcooking.server.entity.event.Subscription;
import org.worldcooking.server.entity.people.Participant;
import org.worldcooking.server.exception.EntityIdNotFountException;
import org.worldcooking.server.services.EventService;
import org.worldcooking.server.services.subscription.SubscriptionService;

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
	private SubscriptionService subscriptionService;

	@RequestMapping(value = URL, method = RequestMethod.GET)
	public ModelAndView handleRequest(@RequestParam Long eventId)
			throws EntityIdNotFountException {
		ModelAndView modelAndView = new ModelAndView(JSP);

		// TODO manage errors
		Assert.notNull(eventId);

		Event event = eventService.findById(eventId);

		modelAndView.addObject("event", event);

		SortedSet<Subscription> nonValidatedRegistrations = subscriptionService
				.findNonValidatedSubscriptions(eventId);

		modelAndView.addObject("nonValidatedRegistrations",
				registrationsToModel(nonValidatedRegistrations));

		SortedSet<Subscription> validatedRegistrations = subscriptionService
				.findValidatedSubscriptions(eventId);

		modelAndView.addObject("validatedRegistrations",
				registrationsToModel(validatedRegistrations));

		return modelAndView;
	}

	private List<Registration> registrationsToModel(
			SortedSet<Subscription> nonValidatedRegistrations)
			throws EntityIdNotFountException {
		List<Registration> registrations = new ArrayList<Registration>();

		for (Subscription s : nonValidatedRegistrations) {
			Registration registration = new Registration();
			registration.setId(s.getId());
			Registrer r = new Registrer();
			r.setName(s.getSubscriberParticipant().getName());
			r.setEmail(s.getEmail());
			r.setTaskName(s.getSubscriberParticipant().getTask().getName());
			registration.setRegistrer(r);

			List<AdditionalParticipant> additionalParticipants = new ArrayList<AdditionalParticipant>();

			for (Participant p : s.getParticipants()) {
				if (!p.equals(s.getSubscriberParticipant())) {
					AdditionalParticipant ap = new AdditionalParticipant();
					ap.setName(p.getName());
					ap.setTaskName(p.getTask().getName());
					additionalParticipants.add(ap);
				}
			}

			Double amount = subscriptionService.calculateSubscriptionPrice(s
					.getParticipants());
			registration.setAmount(amount);

			registration.setAdditionalParticipants(additionalParticipants);

			registrations.add(registration);
		}

		return registrations;
	}

}
