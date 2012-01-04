/**
 * 
 */
package org.worldcooking.web.worldcooking.admin.event.description;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.oupsasso.mishk.core.dao.exception.EntityIdNotFountException;
import org.oupsasso.mishk.core.dao.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.worldcooking.server.entity.event.Event;
import org.worldcooking.server.entity.place.Direction;
import org.worldcooking.server.entity.place.Place;
import org.worldcooking.server.services.EventService;
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

	@RequestMapping(value = URL, method = RequestMethod.GET)
	public ModelAndView handleRequest(@PathVariable String eventReference) throws ServiceException {
		ModelAndView modelAndView = new ModelAndView(JSP);

		Event event = eventService.findByReference(eventReference);

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

		return form;
	}

	@RequestMapping(value = URL, method = RequestMethod.POST)
	public String handleRequestPost(@Valid @ModelAttribute("eventDescriptionForm") EventDescriptionForm form,
			BindingResult result) throws ServiceException {

		if (result.hasErrors()) {

			return JSP;
		}

		Event event = eventService.findById(form.getEventId());

		event.setName(form.getEventName());
		Date newDateTime = new Date(form.getDate().getTime() + form.getTime().getTime());
		event.setDateTime(newDateTime);

		eventService.saveOrUpdate(event);

		return "redirect:/direct/admin/event/" + event.getReference() + "/description";
	}

	@ModelAttribute("event")
	public Event getEvent(@PathVariable String eventReference) throws EntityIdNotFountException {
		return eventService.findByReference(eventReference);
	}

	@ModelAttribute("places")
	public Map<Long, String> getPlaces(@PathVariable String eventReference) throws EntityIdNotFountException {

		Map<Long, String> placesIdDescription = new LinkedHashMap<Long, String>();

		Event event = eventService.findByReference(eventReference);
		if (event != null) {

			List<Place> places = eventService.findAllPlacesOrderByName(event.getId());

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

}
