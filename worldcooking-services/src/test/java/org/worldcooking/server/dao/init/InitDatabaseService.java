package org.worldcooking.server.dao.init;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.worldcooking.AbstractApplicationContextAware;
import org.worldcooking.server.dao.impl.EventDAOImpl;
import org.worldcooking.server.dao.impl.TaskDAOImpl;
import org.worldcooking.server.entity.event.Event;
import org.worldcooking.server.entity.event.Task;

@ContextConfiguration(locations = { "classpath:spring-dao-context.xml" })
public class DatasetFiller extends AbstractApplicationContextAware {

	@Resource
	private EventDAOImpl eventDao;

	@Resource
	private TaskDAOImpl taskDAO;

	/**
	 * Tries to store {@link org.worldcooking.server.entity.DummyEntity}.
	 */
	@Test
	@Rollback(value = false)
	public void initDatabase() {
		// create worldcooking Peru
		Event e = new Event();
		e.setName("Worldcooking Peru");
		eventDao.makePersistent(e);

		Task t1 = new Task();
		t1.setName("Chef");
		// 1
		e.addAvailableTask(t1);
		taskDAO.makePersistent(t1);

		Task t2 = new Task();
		t2.setName("Cooking");
		// 7
		e.addAvailableTask(t2);
		taskDAO.makePersistent(t2);

		Task t3 = new Task();
		t3.setName("Setting the table");
		// 10
		e.addAvailableTask(t3);
		taskDAO.makePersistent(t3);

		Task t4 = new Task();
		t4.setName("Doing the dishes");
		// 9
		e.addAvailableTask(t4);
		taskDAO.makePersistent(t4);

		Task t5 = new Task();
		t5.setName("Cleaning the room");
		// 9
		e.addAvailableTask(t5);
		taskDAO.makePersistent(t5);

	}
}
