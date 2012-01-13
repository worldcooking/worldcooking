/**
 * 
 */
package org.worldcooking.web.worldcooking.admin.event.description;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.mishk.business.event.entity.Direction;
import org.mishk.business.event.entity.Event;
import org.mishk.business.event.entity.EventRegistrationStatus;
import org.mishk.business.event.entity.Place;
import org.mishk.business.event.service.EventService;
import org.mishk.business.event.service.PlaceService;
import org.oupsasso.mishk.core.dao.exception.EntityReferenceNotFoundException;
import org.oupsasso.mishk.core.dao.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.worldcooking.web.worldcooking.admin.event.description.model.EventDescriptionForm;

/**
 * @author MatthieuG
 * 
 */
@Controller
public class WorldcookingAdminEventDescriptionController {

	private static final String URL = "/direct/admin/event/{eventReference}/description";
	private static final String JSP = "worldcooking/admin/event/description/worldcooking-admin-event-description";

	@Autowired
	private EventService eventService;

	@Autowired
	private PlaceService placeService;

	@RequestMapping(value = URL, method = RequestMethod.GET)
	public ModelAndView handleRequest(@PathVariable String eventReference) throws ServiceException {
		ModelAndView modelAndView = new ModelAndView(JSP);

		Event event = eventService.findEventByReference(eventReference, false);

		modelAndView.addObject("event", event);

		EventDescriptionForm form = initForm(event);

		modelAndView.addObject("eventDescriptionForm", form);

		return modelAndView;
	}

	/**
	 * Init form fields.
	 * 
	 * @param event
	 * @return
	 */
	private EventDescriptionForm initForm(Event event) {
		EventDescriptionForm form = new EventDescriptionForm();

		// set event id and name
		form.setEventId(event.getId());
		form.setEventName(event.getName());

		// set event place
		Place place = event.getPlace();
		if (place != null) {
			form.setPlaceId(place.getId());
		}

		// set event date and time
		form.setDate(event.getDateTime());
		form.setTime(event.getDateTime());

		form.setEventRegistrationStatus(event.getEventRegistrationStatus().toString());

		return form;
	}

	@RequestMapping(value = URL, method = RequestMethod.POST)
	public String handleRequestPost(@Valid @ModelAttribute("eventDescriptionForm") EventDescriptionForm form,
			BindingResult result) throws ServiceException {

		if (result.hasErrors()) {

			return JSP;
		}

		Event event = eventService.findEventById(form.getEventId(), false);

		event.setName(form.getEventName());
		Date newDateTime = new Date(form.getDate().getTime() + form.getTime().getTime());
		event.setDateTime(newDateTime);

		event.setEventRegistrationStatus(EventRegistrationStatus.valueOf(form.getEventRegistrationStatus()));

		eventService.saveOrUpdate(event);

		return "redirect:/direct/admin/event/" + event.getReference() + "/description";
	}

	@ModelAttribute("event")
	public Event getEvent(@PathVariable String eventReference) throws EntityReferenceNotFoundException {
		return eventService.findEventByReference(eventReference, false);
	}

	@ModelAttribute("places")
	public Map<Long, String> getPlaces(@PathVariable String eventReference) throws EntityReferenceNotFoundException {

		Map<Long, String> placesIdDescription = new LinkedHashMap<Long, String>();

		Event event = eventService.findEventByReference(eventReference, false);
		if (event != null) {

			List<Place> places = placeService.findAllPlaces();

			for (Place p : places) {

				String placeDescription = p.getName();

				Direction d = p.getDirection();
				if (d != null && (d.getCity() != null || d.getCountry() != null)) {
					if (d.getCity() == null) {
						// country only
						placeDescription += " (" + d.getCountry().toUpperCase() + ")";
					} else if (d.getCountry() == null) {
						// city only
						placeDescription += " (" + d.getCity() + ")";
					} else {
						// city + country
						placeDescription += " (" + d.getCity() + ", " + d.getCountry().toUpperCase() + ")";
					}

				}

				placesIdDescription.put(p.getId(), placeDescription);
			}
		}

		return placesIdDescription;
	}

	@ModelAttribute("availableEventRegistrationStatus")
	public Map<String, String> getAvailableRegistrationStatus() throws EntityReferenceNotFoundException {

		Map<String, String> availableEventRegistrationStatus = new LinkedHashMap<String, String>();

		for (EventRegistrationStatus status : EventRegistrationStatus.values()) {

			availableEventRegistrationStatus.put(status.toString(), status.toString());
		}

		return availableEventRegistrationStatus;
	}

}
