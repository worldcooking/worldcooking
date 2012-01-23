package org.worldcooking.web.worldcooking.admin.events.model.transform;

import java.util.Arrays;

import org.mishk.business.event.entity.Event;
import org.mishk.core.transform.AbstractTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.worldcooking.web.worldcooking.admin.events.model.EventToEdit;
import org.worldcooking.web.worldcooking.admin.events.model.EventToEditParticipants;
import org.worldcooking.web.worldcooking.admin.events.model.EventToEditPlace;

@Repository
public class EventToViewModelTransformer extends AbstractTransformer<Event, EventToEdit> {

	@Autowired
	private EventToViewModelParticipantsTransformer eventToViewModelParticipantsTransformer;

	@Autowired
	private PlaceToViewModelTransformer placeToViewModelTransformer;

	@Override
	public EventToEdit transform(Event input) {
		EventToEdit output = new EventToEdit(input.getName());

		// set reference
		output.setReference(input.getReference());

		// set date/time
		output.setDateTime(input.getDateTime());

		// TODO map to real languages
		output.setLanguages(Arrays.asList("en", "pt"));

		// set participants
		EventToEditParticipants participants = eventToViewModelParticipantsTransformer.transform(input);
		output.setParticipants(participants);

		// set place
		EventToEditPlace place = placeToViewModelTransformer.transform(input.getPlace());
		output.setPlace(place);

		// set status
		output.setStatus(input.getEventRegistrationStatus().toString());

		return output;
	}
}
