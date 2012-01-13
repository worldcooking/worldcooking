package org.worldcooking.web.worldcooking.admin.event.participants.model.transform;

import java.util.ArrayList;
import java.util.List;

import org.mishk.business.event.entity.EventRole;
import org.mishk.business.event.entity.Participant;
import org.mishk.business.event.entity.Registration;
import org.mishk.business.event.entity.RegistrationStatus;
import org.mishk.business.event.service.RegistrationService;
import org.oupsasso.mishk.core.dao.exception.EntityIdNotFoundException;
import org.oupsasso.mishk.core.transform.AbstractTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.worldcooking.web.worldcooking.admin.event.participants.model.WorldcookingAdminEventParticipant;
import org.worldcooking.web.worldcooking.admin.event.participants.model.WorldcookingAdminEventRegistration;
import org.worldcooking.web.worldcooking.admin.event.participants.model.WorldcookingAdminEventTask;

@Repository
public class RegistrationToViewModelTransformer extends
		AbstractTransformer<Registration, WorldcookingAdminEventRegistration> {

	@Autowired
	private RegistrationService registrationService;

	@Override
	public WorldcookingAdminEventRegistration transform(Registration input) throws EntityIdNotFoundException {
		WorldcookingAdminEventRegistration output = new WorldcookingAdminEventRegistration();
		output.setId(input.getId());
		WorldcookingAdminEventParticipant r = new WorldcookingAdminEventParticipant();

		r.setName(input.getSubscriberParticipant().getName());
		r.setId(input.getSubscriberParticipant().getId());

		r.setEmail(input.getSubscriberParticipant().getEmail());
		EventRole eventRole = input.getSubscriberParticipant().getEventRole();
		WorldcookingAdminEventTask taskModel = new WorldcookingAdminEventTask(eventRole.getRole().getName(),
				eventRole.getId());
		r.setTask(taskModel);
		output.setRegistrer(r);

		List<WorldcookingAdminEventParticipant> additionalParticipants = new ArrayList<WorldcookingAdminEventParticipant>();

		for (Participant p : input.getParticipants()) {
			if (!p.equals(input.getSubscriberParticipant())) {
				WorldcookingAdminEventParticipant ap = new WorldcookingAdminEventParticipant();
				ap.setName(p.getName());
				ap.setEmail(p.getEmail());
				ap.setId(p.getId());
				EventRole pEventRole = p.getEventRole();
				WorldcookingAdminEventTask pTaskModel = new WorldcookingAdminEventTask(pEventRole.getRole().getName(),
						pEventRole.getId());
				ap.setTask(pTaskModel);
				additionalParticipants.add(ap);
			}
		}

		// if (input.getPayment().getMode() == PaymentMode.PAYPAL) {
		// output.setPaymentDescription("paypal");
		// } else {
		// output.setPaymentDescription(input.getPayment().getReference());
		// }

		output.setValidated(input.getRegistrationStatus() == RegistrationStatus.VALIDATED);

		// TODO Double amount =
		// registrationService.calculateRegistrationPrice(input.getParticipants());
		Double amount = 15d;
		output.setAmount(amount);

		output.setAdditionalParticipants(additionalParticipants);

		return output;
	}
}
