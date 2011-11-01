package org.worldcooking.server.dao.init;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.worldcooking.AbstractApplicationContextAware;
import org.worldcooking.server.dao.impl.MultiEntitiesHibernateDAOImpl;
import org.worldcooking.server.entity.event.Event;
import org.worldcooking.server.entity.event.Task;
import org.worldcooking.server.entity.people.Participant;

@ContextConfiguration(locations = { "classpath:init-db-spring-dao-context.xml" })
public class InitDatabaseService extends AbstractApplicationContextAware {

	@Resource
	private MultiEntitiesHibernateDAOImpl dao;

	/**
	 * Tries to store {@link org.worldcooking.server.entity.DummyEntity}.
	 */
	@Test
	@Rollback(value = false)
	public void initDatabase() {
		// create worldcooking Peru
		Event e = new Event();
		e.setName("Worldcooking Peru");
		dao.makePersistent(e);

		Task t1 = new Task();
		t1.setName("Chef");
		t1.setNbMax(1);
		e.addAvailableTask(t1);
		dao.makePersistent(t1);

		Participant p1 = new Participant();
		p1.setName("Nidia Torres");
		t1.addParticipant(p1);
		dao.makePersistent(p1);

		Task t2 = new Task();
		t2.setName("Cooking");
		t2.setNbMax(7);
		e.addAvailableTask(t2);
		dao.makePersistent(t2);

		Participant p2 = new Participant();
		p2.setName("Matthieu Gaudet");
		t2.addParticipant(p2);
		dao.makePersistent(p2);

		Task t3 = new Task();
		t3.setName("Setting the table");
		t3.setNbMax(10);
		e.addAvailableTask(t3);
		dao.makePersistent(t3);

		Participant p3 = new Participant();
		p3.setName("Maya Rouvneska");
		t3.addParticipant(p3);
		dao.makePersistent(p3);

		Task t4 = new Task();
		t4.setName("Doing the dishes");
		t4.setNbMax(9);
		e.addAvailableTask(t4);
		dao.makePersistent(t4);

		Participant p4 = new Participant();
		p4.setName("Nicolas Toublanc");
		t4.addParticipant(p4);
		dao.makePersistent(p4);

		Task t5 = new Task();
		t5.setName("Cleaning the room");
		t5.setNbMax(9);
		e.addAvailableTask(t5);
		dao.makePersistent(t5);

		Participant p5 = new Participant();
		p5.setName("Nicolas Gruyer");
		t5.addParticipant(p5);
		dao.makePersistent(p5);

		Participant p6 = new Participant();
		p6.setName("Peter Tosh");
		t5.addParticipant(p6);
		dao.makePersistent(p6);

	}
}
