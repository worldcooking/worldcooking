package org.worldcooking.web;

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
import org.worldcooking.server.entity.event.Subscription;
import org.worldcooking.server.entity.people.Participant;
import org.worldcooking.server.services.EventService;
import org.worldcooking.web.util.ModelViewMapper;
import org.worldcooking.web.worldcooking.TaskModel;
import org.worldcooking.web.worldcooking.WorldcookingEventModel;

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
		Event e = eventService.getFullEventById(10L);

		WorldcookingEventModel wcEvent = ModelViewMapper.getInstance().map(e,
				WorldcookingEventModel.class);

		List<String> waitingParticipants = new ArrayList<String>();
		Set<Subscription> subscriptions = e.getSubscriptions();

		// Iteration on subscription to get all the participants,
		for (Subscription subscription : subscriptions) {
			if (subscription.getValidate()) {
				// if a subscription is validated, all its partcipants are
				// confirmed
				Set<Participant> partList = subscription.getParticipants();
				for (Participant participant : partList) {
					wcEvent.addValidatedParticipantTask(participant.getName(),
							participant.getTask().getId(), participant.getId());
					parcoursTasks: for (TaskModel t : wcEvent.getTasks()) {
						if (t.getId().equals(participant.getTask().getId())) {
							t.setTotalRegister(t.getTotalRegister() + 1);
							break parcoursTasks;
						}
					}
				}
			} else {
				// if a subscription is not validated, all its partcipants are
				// waiting for confirmation
				Set<Participant> partList = subscription.getParticipants();
				for (Participant participant : partList) {
					waitingParticipants.add(participant.getName());

				}
			}
		}

		wcEvent.addWaitingParticipants(waitingParticipants);

		ModelAndView modelAndView = new ModelAndView("worldcookingperu");
		modelAndView.addObject("event", wcEvent);
		return modelAndView;
	}
}
