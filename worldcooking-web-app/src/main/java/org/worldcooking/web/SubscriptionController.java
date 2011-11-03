package org.worldcooking.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.worldcooking.server.exception.EntityIdNotFountException;
import org.worldcooking.server.services.subscription.SubscriptionService;
import org.worldcooking.server.services.subscription.model.NewSubscription;

@Controller
public class SubscriptionController {
	@Autowired
	private SubscriptionService subscriptionService;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	public void test() throws EntityIdNotFountException {
		NewSubscription newSubscription = new NewSubscription();
		Long eventId = null;
		String subscriberEmailAddress = null;
		String paymentTarget = null;
		if (1 + 1 == 2) {
			newSubscription.configureWithManualPayment(eventId,
					subscriberEmailAddress, paymentTarget);
		} else {
			newSubscription.configureWithPaypalPayment(eventId,
					subscriberEmailAddress);
		}
		// for(){
		// newSubscription.addParticipant(name, taskId);
		// }
		subscriptionService.subscribe(newSubscription);
	}
}
