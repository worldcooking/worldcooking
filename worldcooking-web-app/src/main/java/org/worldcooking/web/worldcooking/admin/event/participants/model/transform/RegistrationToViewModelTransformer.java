package org.worldcooking.web.worldcooking.admin.event.participants.model.transform;

import java.util.ArrayList;
import java.util.List;

import org.oupsasso.mishk.core.dao.exception.EntityIdNotFountException;
import org.oupsasso.mishk.core.transform.AbstractTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.worldcooking.server.entity.event.Registration;
import org.worldcooking.server.entity.event.Task;
import org.worldcooking.server.entity.payment.PaymentMode;
import org.worldcooking.server.entity.people.Participant;
import org.worldcooking.server.services.registration.RegistrationService;
import org.worldcooking.web.worldcooking.admin.event.participants.model.WorldcookingAdminEventParticipant;
import org.worldcooking.web.worldcooking.admin.event.participants.model.WorldcookingAdminEventRegistration;
import org.worldcooking.web.worldcooking.admin.event.participants.model.WorldcookingAdminEventTask;

@Repository
public class RegistrationToViewModelTransformer extends
		AbstractTransformer<Registration, WorldcookingAdminEventRegistration> {

	@Autowired
	private RegistrationService registrationService;

	@Override
	public WorldcookingAdminEventRegistration transform(Registration input) throws EntityIdNotFountException {
		WorldcookingAdminEventRegistration output = new WorldcookingAdminEventRegistration();
		output.setId(input.getId());
		WorldcookingAdminEventParticipant r = new WorldcookingAdminEventParticipant();

		r.setName(input.getSubscriberParticipant().getName());
		r.setId(input.getSubscriberParticipant().getId());

		r.setEmail(input.getSubscriberParticipant().getEmail());
		Task task = input.getSubscriberParticipant().getTask();
		WorldcookingAdminEventTask taskModel = new WorldcookingAdminEventTask(task.getName(), task.getId());
		r.setTask(taskModel);
		output.setRegistrer(r);

		List<WorldcookingAdminEventParticipant> additionalParticipants = new ArrayList<WorldcookingAdminEventParticipant>();

		for (Participant p : input.getParticipants()) {
			if (!p.equals(input.getSubscriberParticipant())) {
				WorldcookingAdminEventParticipant ap = new WorldcookingAdminEventParticipant();
				ap.setName(p.getName());
				ap.setEmail(p.getEmail());
				ap.setId(p.getId());
				Task pTask = p.getTask();
				WorldcookingAdminEventTask pTaskModel = new WorldcookingAdminEventTask(pTask.getName(), pTask.getId());
				ap.setTask(pTaskModel);
				additionalParticipants.add(ap);
			}
		}

		if (input.getPayment().getMode() == PaymentMode.PAYPAL) {
			output.setPaymentDescription("paypal");
		} else {
			output.setPaymentDescription(input.getPayment().getReference());
		}

		output.setValidated(input.getValidate());

		Double amount = registrationService.calculateRegistrationPrice(input.getParticipants());
		output.setAmount(amount);

		output.setAdditionalParticipants(additionalParticipants);

		return output;
	}
}
