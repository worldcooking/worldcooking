package org.worldcooking.web.worldcooking.admin.events.model.transform;

import org.mishk.business.event.entity.Event;
import org.mishk.business.event.entity.RegistrationStatus;
import org.mishk.business.event.service.EventService;
import org.mishk.business.event.service.RegistrationService;
import org.oupsasso.mishk.core.transform.AbstractTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.worldcooking.web.worldcooking.admin.events.model.EventToEditParticipants;

@Repository
public class EventToViewModelParticipantsTransformer extends AbstractTransformer<Event, EventToEditParticipants> {

	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private EventService eventService;

	@Override
	public EventToEditParticipants transform(Event input) {

		Long eventId = input.getId();

		// count participants by registration status
		Long nbValidatedParticipants = registrationService.countParticipantsByRegistrationStatus(eventId,
				RegistrationStatus.VALIDATED);
		Long nbNotValidatedParticipants = registrationService.countParticipantsByRegistrationStatus(eventId,
				RegistrationStatus.PENDING);
		// count max participants
		long maxParticipants = eventService.countMaxParticipants(eventId);

		EventToEditParticipants participants = new EventToEditParticipants(nbValidatedParticipants, maxParticipants,
				nbNotValidatedParticipants);

		return participants;
	}
}
