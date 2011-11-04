package org.worldcooking.server.services.subscription;

import java.util.Arrays;
import java.util.HashSet;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.worldcooking.ApplicationContextAwareTest;
import org.worldcooking.server.dao.impl.MultiEntitiesHibernateDAOImpl;
import org.worldcooking.server.entity.event.Event;
import org.worldcooking.server.entity.event.Subscription;
import org.worldcooking.server.entity.event.Task;
import org.worldcooking.server.entity.people.Participant;
import org.worldcooking.server.exception.EntityIdNotFountException;
import org.worldcooking.server.services.subscription.model.NewSubscription;

public class SubscriptionServiceTest extends ApplicationContextAwareTest {

	@Resource
	private SubscriptionService subscriptionService;

	@Resource
	private MultiEntitiesHibernateDAOImpl dao;

	@Test
	public void testCalculateSubscriptionPrice()
			throws EntityIdNotFountException {
		Task t1 = new Task();
		t1.setPricePerParticipant(10d);

		Task t2 = new Task();
		t2.setPricePerParticipant(12d);

		Task t3 = new Task();
		t3.setPricePerParticipant(0d);

		Participant p1 = new Participant();
		p1.setTask(t1);

		// T1 (10)
		Assert.assertEquals(10d, subscriptionService
				.calculateSubscriptionPrice(new HashSet(Arrays.asList(p1))));

		Participant p2 = new Participant();
		p2.setTask(t2);

		// T1 (10) + T2 (12) = 22
		Assert.assertEquals(22d, subscriptionService
				.calculateSubscriptionPrice(new HashSet(Arrays.asList(p1, p2))));

		Participant p3 = new Participant();
		p3.setTask(t2);

		Participant p4 = new Participant();
		p4.setTask(t3);

		// T1 (10) + T2 (12) + T2 (12) + T3 (0) = 34
		Assert.assertEquals(34d, subscriptionService
				.calculateSubscriptionPrice(new HashSet(Arrays.asList(p1, p2,
						p3, p4))));

		// T3 (0)
		Assert.assertEquals(0d, subscriptionService
				.calculateSubscriptionPrice(new HashSet(Arrays.asList(p4))));

	}

	@Test
	public void testSubscribe() throws EntityIdNotFountException {

		// test initialization
		Event e = new Event();
		e.setName("Event 1");
		dao.makePersistent(e);

		Task t1 = new Task();
		t1.setPricePerParticipant(10d);
		e.addAvailableTask(t1);
		dao.makePersistent(t1);

		Task t2 = new Task();
		t2.setPricePerParticipant(12d);
		e.addAvailableTask(t2);
		dao.makePersistent(t2);

		Task t3 = new Task();
		t3.setPricePerParticipant(0d);
		e.addAvailableTask(t3);
		dao.makePersistent(t3);

		NewSubscription newSubscription = new NewSubscription()
				.configureWithPaypalPayment(e.getId(),
						"subscriber@paypal-member.com")
				.addParticipant("Chef", t3.getId())
				.addParticipant("Tarif eco1", t1.getId())
				.addParticipant("Tarif eco2", t1.getId())
				.addParticipant("Tarif normal1", t2.getId())
				.addParticipant("Tarif normal2", t2.getId())
				.addParticipant("Tarif eco3", t1.getId());

		Subscription s = subscriptionService.subscribe(newSubscription);

		// amount = 0 + 10 + 10 + 12 + 12 + 10 = 54
		Assert.assertEquals(54d, s.getPayment().getAmount());

		// 6 participants
		Assert.assertEquals(6, s.getParticipants().size());

		// TODO check that payment is NOT validated
		// Assert.assertFalse(subscription.getPayment().getValid);
	}

	@Test
	public void testValidatePayment() throws EntityIdNotFountException {
		Subscription subscription = new Subscription();
		// TODO subscription.setValid(FALSE);
		dao.makePersistent(subscription);
		subscriptionService.validatePayment(subscription.getId());
		// TODO check that payment is validated
		// Assert.assertTrue(subscription.getPayment().getValid);

	}

}
