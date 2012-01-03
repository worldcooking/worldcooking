package org.worldcooking.server.services.admin;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.worldcooking.server.services.DbInitializerService;

public class DatabaseInitializer {

	private static Logger logger = LoggerFactory.getLogger(DatabaseInitializer.class);

	public static void main(String... args) {
		logger.info("Reseting database.");

		ApplicationContext springContext = new ClassPathXmlApplicationContext("spring/init-db-spring-dao-context.xml");

		Map<String, DbInitializerService> services = springContext.getBeansOfType(DbInitializerService.class);

		DbInitializerService service = services.values().iterator().next();

		service.resetDb();

		logger.info("Database has been reset.");
	}
}
