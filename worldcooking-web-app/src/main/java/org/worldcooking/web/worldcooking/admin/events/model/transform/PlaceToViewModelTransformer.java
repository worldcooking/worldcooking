package org.worldcooking.web.worldcooking.admin.events.model.transform;

import org.oupsasso.mishk.core.web.transform.AbstractTransformer;
import org.springframework.stereotype.Repository;
import org.worldcooking.server.entity.place.Direction;
import org.worldcooking.server.entity.place.Place;
import org.worldcooking.web.worldcooking.admin.events.model.EventToEditPlace;

@Repository
public class PlaceToViewModelTransformer extends AbstractTransformer<Place, EventToEditPlace> {

	@Override
	public EventToEditPlace transform(Place input) {
		if (input == null) {
			return null;
		}
		EventToEditPlace output = new EventToEditPlace(input.getName());

		Direction direction = input.getDirection();

		if (direction != null) {
			output.setCity(direction.getCity());
			output.setCountry(direction.getCountry());
		}

		return output;
	}
}
