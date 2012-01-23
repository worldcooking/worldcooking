package org.worldcooking.web.worldcooking.registration.form;

import java.util.ArrayList;
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
import org.mishk.business.shop.exception.InsufficientStockException;
import org.mishk.core.dao.exception.EntityIdNotFoundException;
import org.mishk.core.dao.exception.EntityNotFoundException;
import org.mishk.core.dao.exception.EntityReferenceNotFoundException;
import org.mishk.core.dao.exception.I18NServiceException;
import org.mishk.core.dao.exception.ServiceException;
import org.mishk.security.entity.SecurityUser;
import org.mishk.security.service.SecurityUserManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
	private static final String JSP = "site/registration/form/worldcooking-registration-form";

	@Autowired
	private WorldcookingService worldcookingService;

	@Autowired
	private EventService eventService;

	@Autowired
	private SecurityUserManagementService securityUserManagementService;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping(method = RequestMethod.GET)
	public String initializeForm(@PathVariable String eventReference,
			ModelMap model) throws EntityIdNotFoundException,
			EntityReferenceNotFoundException {

		WorldcookingRegistrationFormDetail registration = new WorldcookingRegistrationFormDetail();

		Event event = eventService.findEventByReference(eventReference, false);

		if (event != null) {
			registration.setEventId(event.getId());

			if (!eventService.isEventOpen(event)) {
				logger.warn("Attempt to access to {} event '{}'.",
						new Object[] { event.getEventRegistrationStatus(),
								event.getName() });
				return "redirect:/";
			}

		}

		SecurityUser user = securityUserManagementService.getCurrentUser();

		if (user != null) {
			registration.setSubscriberParticipantName(user.getNickname());
			registration.setEmailAddress(user.getEmailAddress());
		}
		model.addAttribute("registration", registration);

		return JSP;
	}

	@ModelAttribute("event")
	public Event populateEvent(@PathVariable String eventReference)
			throws EntityReferenceNotFoundException {
		return eventService.findEventByReference(eventReference, false);
	}

	@ModelAttribute("availableTasks")
	public Map<Long, String> populateAvailableTasks(
			@PathVariable String eventReference)
			throws EntityIdNotFoundException, EntityReferenceNotFoundException {

		Map<Long, String> availableTasksIdName = new LinkedHashMap<Long, String>();

		availableTasksIdName.put(-1l, "");

		Event event = eventService.findEventByReference(eventReference, false);
		if (event != null) {
			List<EventRole> availableRoles = new ArrayList<EventRole>();
			availableRoles.addAll(eventService.getAvailableEventRoles(
					event.getId(), false));

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
	public String onSubmit(
			@Valid @ModelAttribute("registration") WorldcookingRegistrationFormDetail form,
			BindingResult result) throws EntityNotFoundException {
		String returnView = JSP;

		Event event = eventService.findEventById(form.getEventId(), false);

		if (!eventService.isEventOpen(event)) {
			// event is not open for registration
			logger.warn(
					"Attempt to access to closed registration of event '{}'.",
					event.getName());
			return "redirect:/";
		}

		try {
			// create new registration
			NewRegistration newRegistration = createNewRegistration(form);

			// persist registration
			Registration registration;
			registration = worldcookingService.register(newRegistration);

			if (newRegistration.getPaymentMode() == NewRegistrationPaymentMode.PAYPAL) {
				// redirect to paypal
				returnView = "redirect:/event/" + event.getReference()
						+ "/registration/confirmation/paypal?registrationId="
						+ registration.getId();
			} else {
				returnView = "redirect:/event/" + event.getReference()
						+ "/registration/confirmation?registrationId="
						+ registration.getId();
			}

		} catch (I18NServiceException e) {
			result.addError(new ObjectError(result.getObjectName(), e
					.getErrorMessage()));
		} catch (InsufficientStockException e) {
			result.addError(new ObjectError(result.getObjectName(), e
					.getErrorMessage()));
		}
		if (result.hasErrors()) {
			// back to form
			returnView = JSP;
		}
		// redirect to main page
		return returnView;
	}

	/**
	 * Create the new registration.
	 * 
	 * @param registration
	 * @throws EntityIdNotFoundException
	 * @throws ServiceException
	 */
	private NewRegistration createNewRegistration(
			WorldcookingRegistrationFormDetail registration)
			throws EntityIdNotFoundException, I18NServiceException {

		// create registration
		NewRegistration newRegistration = new NewRegistration();
		Long eventId = registration.getEventId();

		// add additional participants
		String name1 = registration.getAdditionalParticipant1Name();
		Long task1 = registration.getAdditionalParticipant1Task();

		if (name1 != null && !name1.trim().isEmpty()) {
			if (task1 == null || task1 == -1) {
				throw new I18NServiceException(
						"Please specify the task for additionnal participant 1.");
			}
			newRegistration.addParticipant(
					registration.getAdditionalParticipant1EmailAddress(),
					name1, task1);
		}
		String name2 = registration.getAdditionalParticipant2Name();
		Long task2 = registration.getAdditionalParticipant2Task();
		if (name2 != null && !name2.trim().isEmpty()) {
			if (task2 == null || task2 == -1) {
				throw new I18NServiceException(
						"Please specify the task for additionnal participant 2.");
			}
			newRegistration.addParticipant(
					registration.getAdditionalParticipant2EmailAddress(),
					name2, task2);
		}

		NewParticipant subscriberParticipant = new NewParticipant(
				registration.getEmailAddress(),
				registration.getSubscriberParticipantName(),
				registration.getSubscriberParticipantTask());
		if (PAYPAL_MODE_KEY.equals(registration.getPaymentMode())) {
			// paypal

			newRegistration.configureWithPaypalPayment(eventId,
					subscriberParticipant);
		} else {
			Map<String, String> availablePaymentModes = populateAvailablePaymentModes();

			String paymentTarget = availablePaymentModes.get(registration
					.getPaymentMode());
			// TODO meilleure gestion des erreurs
			Assert.notNull(paymentTarget);
			newRegistration.configureWithManualPayment(eventId, paymentTarget,
					subscriberParticipant);
		}

		return newRegistration;
	}
}
