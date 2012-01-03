package org.worldcooking.server.dao.impl;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;
import org.worldcooking.ApplicationContextAwareTest;
import org.worldcooking.server.entity.event.Event;
import org.worldcooking.server.entity.event.Event.RegistrationStatus;

/**
 * 
 */
public class EventDAOImplTest extends ApplicationContextAwareTest {

	@Resource(type = EventDAOImpl.class)
	private EventDAOImpl dao;

	/**
	 * Tries to store {@link org.worldcooking.server.entity.DummyEntity}.
	 */
	@Test
	public void testPersiEventstTestEntity() {
		int countBefore = dao.findAll().size();
		Event e = new Event();
		e.setName("Worlcooking Peru");
		e.setReference("peru");
		e.setRegistrationStatus(RegistrationStatus.OPEN);
		dao.saveOrUpdate(e);

		int countAfter = dao.findAll().size();
		assertEquals(countBefore + 1, countAfter);
	}
}
