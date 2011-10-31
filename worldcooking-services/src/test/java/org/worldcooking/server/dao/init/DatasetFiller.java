package org.worldcooking.server.dao.init;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.worldcooking.AbstractApplicationContextAware;
import org.worldcooking.server.dao.impl.EventDAOImpl;
import org.worldcooking.server.entity.event.Event;

@ContextConfiguration(locations = { "classpath:spring-dao-context.xml" })
public class DatasetFiller extends AbstractApplicationContextAware {

	@Resource
	private EventDAOImpl eventDao;

	/**
	 * Tries to store {@link org.worldcooking.server.entity.DummyEntity}.
	 */
	@Test
	@Rollback(value = false)
	public void testPersiEventstTestEntity() {
		Event e = new Event();
		e.setName("Worlcooking Peru");
		eventDao.makePersistent(e);
	}
}
