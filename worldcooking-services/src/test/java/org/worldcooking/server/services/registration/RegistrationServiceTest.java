package org.worldcooking.server.services.registration;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.oupsasso.mishk.core.dao.exception.EntityIdNotFountException;
import org.worldcooking.ApplicationContextAwareTest;
import org.worldcooking.server.dao.impl.RegistrationDAOImpl;
import org.worldcooking.server.entity.event.Event;
import org.worldcooking.server.entity.event.Registration;
import org.worldcooking.server.entity.event.Task;
import org.worldcooking.server.entity.people.Participant;
import org.worldcooking.server.services.registration.model.NewParticipant;
import org.worldcooking.server.services.registration.model.NewRegistration;

public class RegistrationServiceTest extends ApplicationContextAwareTest {

	@Resource
	private RegistrationService registrationService;

	@Resource
	private RegistrationDAOImpl dao;

	@Test
	public void testCalculateRegistrationPrice() throws EntityIdNotFountException {
		Task t1 = new Task();
		t1.setPricePerParticipant(10d);

		Task t2 = new Task();
		t2.setPricePerParticipant(12d);

		Task t3 = new Task();
		t3.setPricePerParticipant(0d);

		Participant p1 = new Participant();
		p1.setTask(t1);

		// T1 (10)
		Assert.assertEquals(10d,
				registrationService.calculateRegistrationPrice(new HashSet<Participant>(Arrays.asList(p1))));

		Participant p2 = new Participant();
		p2.setTask(t2);

		// T1 (10) + T2 (12) = 22
		Assert.assertEquals(22d,
				registrationService.calculateRegistrationPrice(new HashSet<Participant>(Arrays.asList(p1, p2))));

		Participant p3 = new Participant();
		p3.setTask(t2);

		Participant p4 = new Participant();
		p4.setTask(t3);

		// T1 (10) + T2 (12) + T2 (12) + T3 (0) = 34
		Assert.assertEquals(34d,
				registrationService.calculateRegistrationPrice(new HashSet<Participant>(Arrays.asList(p1, p2, p3, p4))));

		// T3 (0)
		Assert.assertEquals(0d,
				registrationService.calculateRegistrationPrice(new HashSet<Participant>(Arrays.asList(p4))));

	}

	@Test
	public void testSubscribe() throws EntityIdNotFountException {

		// test initialization
		Event e = new Event();
		e.setName("Event 1");
		dao.saveOrUpdate(e);

		Task t1 = new Task();
		t1.setPricePerParticipant(10d);
		e.addAvailableTask(t1);

		t1.setName("Task 1");
		t1.setDescription("Description of task 1");

		dao.saveOrUpdate(t1);

		Task t2 = new Task();
		t2.setName("Task 2");
		t2.setPricePerParticipant(12d);
		e.addAvailableTask(t2);
		dao.saveOrUpdate(t2);

		Task t3 = new Task();
		t3.setName("Task 3");
		t3.setPricePerParticipant(0d);
		e.addAvailableTask(t3);
		dao.saveOrUpdate(t3);

		NewRegistration newRegistration = new NewRegistration()
				.configureWithPaypalPayment(e.getId(), "subscriber@paypal-member.com",
						new NewParticipant("Tarif eco1 and subscriber", t1.getId())).addParticipant("Chef", t3.getId())
				.addParticipant("Tarif eco2", t1.getId()).addParticipant("Tarif normal1", t2.getId())
				.addParticipant("Tarif normal2", t2.getId()).addParticipant("Tarif eco3", t1.getId());

		Registration registration = registrationService.subscribe(newRegistration);

		// amount = 0 + 10 + 10 + 12 + 12 + 10 = 54
		Assert.assertEquals(54d, registration.getPayment().getAmount());

		// 6 participants
		Assert.assertEquals(6, registration.getParticipants().size());

		// check that payment is NOT validated
		Assert.assertFalse(registration.getValidate());

		Participant subscriberParticipant = registration.getSubscriberParticipant();
		// check the subscriber participant
		Assert.assertNotNull(subscriberParticipant);

		Assert.assertEquals("Tarif eco1 and subscriber", subscriberParticipant.getName());
	}

	@Test
	public void testValidatePayment() throws EntityIdNotFountException {
		Registration registration = new Registration();

		registration.setEmail("a@b.c");
		registration.setRegistrationDate(new Date());

		dao.saveOrUpdate(registration);
		registrationService.validatePayment(registration.getId());
		// check that payment is validated
		Assert.assertTrue(registration.getValidate());

	}

}
