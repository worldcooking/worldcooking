package org.worldcooking.web.worldcooking.resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.mishk.business.event.entity.Event;
import org.mishk.business.event.entity.EventRole;
import org.mishk.business.event.entity.Participant;
import org.mishk.business.event.entity.Registration;
import org.mishk.business.event.entity.RegistrationStatus;
import org.mishk.business.event.service.EventService;
import org.mishk.business.event.service.RegistrationService;
import org.oupsasso.mishk.core.dao.exception.EmptyCollectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.worldcooking.web.util.ModelViewMapper;
import org.worldcooking.web.worldcooking.resume.model.WorldcookingResumeEvent;
import org.worldcooking.web.worldcooking.resume.model.WorldcookingResumeTask;

/**
 * Simple index page controller serving hello.jsp file
 */
@Controller
public class WorldcookingResumeController {

	@Autowired
	private EventService eventService;

	@Autowired
	private RegistrationService registrationService;

	private final static Logger LOGGER = LoggerFactory.getLogger(WorldcookingResumeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView handleRequest() {
		ModelAndView modelAndView = new ModelAndView("worldcooking/resume/worldcooking-resume");

		try {
			// retrieve the last visible event (OPEN or PLANNED)
			Event e = eventService.findLastVisibleEvent(true);

			if (e != null) {
				WorldcookingResumeEvent wcEvent = ModelViewMapper.getInstance().map(e, WorldcookingResumeEvent.class);

				List<WorldcookingResumeTask> tasks = new ArrayList<WorldcookingResumeTask>();

				for (EventRole eRole : e.getEventRoles()) {
					WorldcookingResumeTask task = new WorldcookingResumeTask();
					task.setId(eRole.getId());
					task.setName(eRole.getRole().getName());
					task.setTotalMax(eRole.getParticipantsMax());
					task.setTotalRegister(registrationService.countParticipantsByRegistrationStatus(eRole.getId(),
							RegistrationStatus.VALIDATED));

					tasks.add(task);
				}

				wcEvent.setTasks(tasks);

				wcEvent.setNbParticipantsMax(eventService.countMaxParticipants(e.getId()));

				List<String> waitingParticipants = new ArrayList<String>();
				Set<Registration> registrations = e.getRegistrations();

				// Iteration on registration to get all the participants,
				for (Registration registration : registrations) {
					if (registration.getRegistrationStatus() == RegistrationStatus.VALIDATED) {
						// if a registration is validated, all its participants
						// are
						// confirmed
						Set<Participant> partList = registration.getParticipants();
						for (Participant participant : partList) {
							wcEvent.addValidatedParticipantTask(participant.getName(), participant.getEventRole()
									.getId(), participant.getId());
							parcoursTasks: for (WorldcookingResumeTask t : wcEvent.getTasks()) {
								if (t.getId().equals(participant.getEventRole().getRole().getId())) {
									t.setTotalRegister(t.getTotalRegister() + 1);
									break parcoursTasks;
								}
							}
						}
					} else {
						// if a registration is not validated, all its
						// partcipants
						// are waiting for confirmation
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
		} catch (EmptyCollectionException e1) {
			// no event
		}
		return modelAndView;
	}
}
