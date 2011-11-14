package org.worldcooking.server.dao.init;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.worldcooking.server.services.EventService;

public class InitDatabaseService {

	public static void main(String... args) {
		ApplicationContext springContext = new ClassPathXmlApplicationContext(
				"init-db-spring-dao-context.xml");

		Map<String, EventService> services = springContext
				.getBeansOfType(EventService.class);

		EventService service = services.values().iterator().next();

		service.resetDb();
	}
}
