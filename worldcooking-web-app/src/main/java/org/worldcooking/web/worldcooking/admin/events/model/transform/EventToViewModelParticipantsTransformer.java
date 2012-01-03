package org.worldcooking.web.worldcooking.admin.events.model.transform;

import org.oupsasso.mishk.core.web.transform.AbstractTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.worldcooking.server.entity.event.Event;
import org.worldcooking.server.services.registration.RegistrationService;
import org.worldcooking.web.worldcooking.admin.events.model.EventToEditParticipants;

@Repository
public class EventToViewModelParticipantsTransformer extends AbstractTransformer<Event, EventToEditParticipants> {

	@Autowired
	private RegistrationService registrationService;

	@Override
	public EventToEditParticipants transform(Event input) {

		Long nbValidatedParticipants = registrationService.countValidatedParticipants(input.getId());
		Long nbNotValidatedParticipants = registrationService.countNotValidatedParticipants(input.getId());

		EventToEditParticipants participants = new EventToEditParticipants(nbValidatedParticipants,
				input.getMaxParticipants(), nbNotValidatedParticipants);

		return participants;
	}
}
