package org.worldcooking.web.worldcooking.registration.form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.mishk.business.event.entity.Event;
import org.mishk.business.event.entity.EventRole;
import org.mishk.business.event.entity.Registration;
import org.mishk.business.event.service.EventService;
import org.mishk.business.event.service.model.NewParticipant;
import org.mishk.business.event.service.model.NewRegistration;
import org.mishk.business.event.service.model.NewRegistrationPaymentMode;
import org.oupsasso.mishk.business.shop.exception.InsufficientStockException;
import org.oupsasso.mishk.core.dao.exception.EntityIdNotFoundException;
import org.oupsasso.mishk.core.dao.exception.EntityNotFoundException;
import org.oupsasso.mishk.core.dao.exception.EntityReferenceNotFoundException;
import org.oupsasso.mishk.security.entity.SecurityUser;
import org.oupsasso.mishk.security.service.SecurityUserManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.worldcooking.service.admin.WorldcookingService;
import org.worldcooking.web.worldcooking.registration.form.model.WorldcookingRegistrationFormDetail;

@Controller
@RequestMapping(value = "/event/{eventReference}/registration")
public class WorldcookingRegistrationFormController {
	private static final String PAYPAL_MODE_KEY = "paypal";
	private static final String JSP = "worldcooking/registration/form/worldcooking-registration-form";

	@Autowired
	private WorldcookingService worldcookingService;

	@Autowired
	private EventService eventService;

	@Autowired
	private SecurityUserManagementService securityUserManagementService;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping(method = RequestMethod.GET)
	public String initializeForm(@PathVariable String eventReference, ModelMap model) throws EntityIdNotFoundException,
			EntityReferenceNotFoundException {

		WorldcookingRegistrationFormDetail registration = new WorldcookingRegistrationFormDetail();

		Event event = eventService.findEventByReference(eventReference, false);

		if (event != null) {
			registration.setEventId(event.getId());

			if (!eventService.isEventOpen(event)) {
				logger.warn("Attempt to access to {} event '{}'.", new Object[] { event.getEventRegistrationStatus(),
						event.getName() });
				return "redirect:/";
			}

		}
		registration.setAdditionalParticipantsTasks(Arrays.asList(0l, 0l));

		SecurityUser user = securityUserManagementService.getCurrentUser();

		if (user != null) {
			registration.setSubscriberParticipantName(user.getNickname());
			registration.setEmailAddress(user.getEmailAddress());
		}
		model.addAttribute("registration", registration);
		model.addAttribute("event", event);

		return JSP;
	}

	@ModelAttribute("availableTasks")
	public Map<Long, String> populateAvailableTasks(@PathVariable String eventReference)
			throws EntityIdNotFoundException, EntityReferenceNotFoundException {

		Map<Long, String> availableTasksIdName = new LinkedHashMap<Long, String>();

		availableTasksIdName.put(-1l, "");

		Event event = eventService.findEventByReference(eventReference, false);
		if (event != null) {
			List<EventRole> availableRoles = new ArrayList<EventRole>();
			availableRoles.addAll(eventService.getAvailableEventRoles(event.getId(), false));

			for (EventRole t : availableRoles) {
				availableTasksIdName.put(t.getId(), t.getRole().getName());
			}
		}

		return availableTasksIdName;
	}

	@ModelAttribute("availablePaymentModes")
	public Map<String, String> populateAvailablePaymentModes() {
		Map<String, String> availablePaymentModes = new LinkedHashMap<String, String>();

		availablePaymentModes.put(PAYPAL_MODE_KEY, "Paypal or CB");
		availablePaymentModes.put("manual-mgaudet", "Matthieu Gaudet");
		return availablePaymentModes;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(@Valid @ModelAttribute("registration") WorldcookingRegistrationFormDetail form,
			BindingResult result) throws EntityNotFoundException {

		Event event = eventService.findEventById(form.getEventId(), false);

		if (!eventService.isEventOpen(event)) {
			// event is not open for registration
			logger.warn("Attempt to access to closed registration of event '{}'.", event.getName());
			return "redirect:/";
		}

		// check parameters (TODO manage errors)
		// TODO add a custom constraint (using group constraints?)
		int participantsNb = form.getAdditionalParticipantsNames().size();
		int tasksNb = form.getAdditionalParticipantsTasks().size();
		Assert.isTrue(participantsNb <= tasksNb, "Participants tasks (" + tasksNb
				+ ") should be as large as participants names (" + participantsNb + ").");

		// create new registration
		NewRegistration newRegistration = createNewRegistration(form);

		if (newRegistration == null) {
			// TODO replace with multi-fields form validation

			return JSP;
		}

		if (result.hasErrors()) {

			return JSP;
		}

		// persist registration
		Registration registration;
		try {
			registration = worldcookingService.register(newRegistration);
		} catch (InsufficientStockException e) {

			return JSP;
		}

		String returnView;
		if (newRegistration.getPaymentMode() == NewRegistrationPaymentMode.PAYPAL) {
			// redirect to paypal
			returnView = "redirect:/event/" + event.getReference()
					+ "/registration/confirmation/paypal?registrationId=" + registration.getId();
		} else {
			returnView = "redirect:/event/" + event.getReference() + "/registration/confirmation?registrationId="
					+ registration.getId();
		}
		// redirect to main page
		return returnView;
	}

	/**
	 * Create the new registration.
	 * 
	 * @param registration
	 * @throws EntityIdNotFoundException
	 */
	private NewRegistration createNewRegistration(WorldcookingRegistrationFormDetail registration)
			throws EntityIdNotFoundException {

		// create registration
		NewRegistration newRegistration = new NewRegistration();
		Long eventId = registration.getEventId();

		Iterator<Long> participantsTasksIt = registration.getAdditionalParticipantsTasks().iterator();
		Iterator<String> participantsEmailsIt = registration.getAdditionalParticipantsEmailAddresses().iterator();
		// add additional participants
		for (String participantName : registration.getAdditionalParticipantsNames()) {
			if (participantName != null) {
				participantName = participantName.trim();
				if (!participantName.isEmpty()) {
					Long taskId = participantsTasksIt.next();
					if (taskId == -1) {
						return null;
					}
					newRegistration.addParticipant(participantsEmailsIt.next(), participantName, taskId);
				}
			}
		}
		NewParticipant subscriberParticipant = new NewParticipant(registration.getEmailAddress(),
				registration.getSubscriberParticipantName(), registration.getSubscriberParticipantTask());
		if (PAYPAL_MODE_KEY.equals(registration.getPaymentMode())) {
			// paypal

			newRegistration.configureWithPaypalPayment(eventId, subscriberParticipant);
		} else {
			Map<String, String> availablePaymentModes = populateAvailablePaymentModes();

			String paymentTarget = availablePaymentModes.get(registration.getPaymentMode());
			// TODO meilleure gestion des erreurs
			Assert.notNull(paymentTarget);
			newRegistration.configureWithManualPayment(eventId, paymentTarget, subscriberParticipant);
		}

		return newRegistration;
	}
}
