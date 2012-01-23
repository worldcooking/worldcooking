package org.worldcooking.web.worldcooking.admin.event.participants.model.transform;

import java.util.ArrayList;
import java.util.List;

import org.mishk.business.event.entity.EventRole;
import org.mishk.business.event.entity.Participant;
import org.mishk.business.event.entity.Registration;
import org.mishk.business.event.entity.RegistrationStatus;
import org.mishk.business.shop.entity.Payment;
import org.mishk.business.shop.entity.PaymentMode;
import org.mishk.business.shop.entity.Shopping;
import org.mishk.business.shop.service.ShoppingService;
import org.mishk.core.dao.exception.EntityNotFoundException;
import org.mishk.core.transform.AbstractTransformer;
import org.mishk.security.entity.SecurityUser;
import org.mishk.security.service.SecurityUserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.worldcooking.service.admin.WorldcookingService;
import org.worldcooking.web.worldcooking.admin.event.participants.model.WorldcookingAdminEventParticipant;
import org.worldcooking.web.worldcooking.admin.event.participants.model.WorldcookingAdminEventRegistration;
import org.worldcooking.web.worldcooking.admin.event.participants.model.WorldcookingAdminEventTask;

@Repository
public class RegistrationToViewModelTransformer extends
		AbstractTransformer<Registration, WorldcookingAdminEventRegistration> {

	@Autowired
	private ShoppingService shoppingService;

	@Autowired
	private WorldcookingService worldcookingService;

	@Autowired
	private SecurityUserManagementService securityUserManagementService;

	@Override
	public WorldcookingAdminEventRegistration transform(Registration input) throws EntityNotFoundException {
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

		// if user was connected, retrieve its nickname
		SecurityUser securityUser = securityUserManagementService.findUserByUsername(input.getUsername());
		if (securityUser != null) {
			output.setSubscriberFullName(securityUser.getNickname());
		}

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

		Shopping shopping = shoppingService.findShoppingByReference(input.getShoppingReference());

		Payment payment = shopping.getPayment();
		if (payment != null) {
			if (payment.getPaymentMode() == PaymentMode.PAYPAL) {
				// paypal payment
				output.setPaymentDescription("paypal");
			} else {
				// manual payment
				output.setPaymentDescription(payment.getReference());
			}
		}

		output.setValidated(input.getRegistrationStatus() == RegistrationStatus.VALIDATED);

		Double amount = worldcookingService.calculateRegistrationPrice(input.getId());
		output.setAmount(amount);

		output.setAdditionalParticipants(additionalParticipants);

		return output;
	}
}
