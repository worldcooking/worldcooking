package org.worldcooking.server.dao.impl;

import javax.annotation.Resource;

import org.junit.Test;

import org.worldcooking.ApplicationContextAwareTest;
import org.worldcooking.server.dao.DummyDAO;
import org.worldcooking.server.entity.DummyEntity;
import org.worldcooking.server.entity.Event;

import static org.junit.Assert.assertEquals;

/**
 * 
 */
public class EventDAOImplTest extends ApplicationContextAwareTest {

    @Resource
    private EventDAOImpl dao;

    /**
     * Tries to store {@link org.worldcooking.server.entity.DummyEntity}.
     */
    @Test
    public void testPersiEventstTestEntity() {
        int countBefore = dao.findAll().size();
        Event e = new Event();
        e.setName("Worlcooking Peru");
        dao.makePersistent(e);

        int countAfter = dao.findAll().size();
        assertEquals(countBefore + 1, countAfter);
    }
}
