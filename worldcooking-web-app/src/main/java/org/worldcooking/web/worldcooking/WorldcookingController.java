package org.worldcooking.web.worldcooking;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.worldcooking.server.entity.event.Event;
import org.worldcooking.server.entity.event.Registration;
import org.worldcooking.server.entity.people.Participant;
import org.worldcooking.server.services.EventService;
import org.worldcooking.web.util.ModelViewMapper;

/**
 * Simple index page controller serving hello.jsp file
 */
@Controller
public class WorldcookingController {

	@Autowired
	private EventService eventService;

	private final static Logger LOGGER = LoggerFactory
			.getLogger(WorldcookingController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView handleRequest() {
		// Acces to the event in DB.
		Event e = eventService.getLastEvent();
		ModelAndView modelAndView = new ModelAndView("worldcookingperu");

		if (e != null) {
			WorldcookingEventModel wcEvent = ModelViewMapper.getInstance().map(
					e, WorldcookingEventModel.class);

			List<String> waitingParticipants = new ArrayList<String>();
			Set<Registration> registrations = e.getRegistrations();

			// Iteration on registration to get all the participants,
			for (Registration registration : registrations) {
				if (registration.getValidate()) {
					// if a registration is validated, all its partcipants are
					// confirmed
					Set<Participant> partList = registration.getParticipants();
					for (Participant participant : partList) {
						wcEvent.addValidatedParticipantTask(participant
								.getName(), participant.getTask().getId(),
								participant.getId());
						parcoursTasks: for (TaskModel t : wcEvent.getTasks()) {
							if (t.getId().equals(participant.getTask().getId())) {
								t.setTotalRegister(t.getTotalRegister() + 1);
								break parcoursTasks;
							}
						}
					}
				} else {
					// if a registration is not validated, all its partcipants
					// are
					// waiting for confirmation
					Set<Participant> partList = registration.getParticipants();
					for (Participant participant : partList) {
						waitingParticipants.add(participant.getName());

					}
				}
			}
			modelAndView.addObject("event", wcEvent);
			wcEvent.addWaitingParticipants(waitingParticipants);
			if (wcEvent.getNbParticipants() >= wcEvent.getNbParticipantsMax()) {
				wcEvent.setRegistrationClosed(true);
			} else {
				wcEvent.setRegistrationClosed(false);
			}
		}
		return modelAndView;
	}
}
