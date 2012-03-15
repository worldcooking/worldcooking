package org.worldcooking.main;

import java.util.Map;

import org.mishk.core.dao.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.worldcooking.service.admin.DbInitializerService;

public class WorldcookingDatabaseInitializer {

	private static Logger logger = LoggerFactory.getLogger(WorldcookingDatabaseInitializer.class);

	public static void main(String... args) throws EntityNotFoundException {
		ApplicationContext springContext = new ClassPathXmlApplicationContext("spring/init-db-spring-dao-context.xml");

		Map<String, DbInitializerService> services = springContext.getBeansOfType(DbInitializerService.class);

		DbInitializerService service = services.values().iterator().next();

		logger.info("Reseting database.");

		service.resetDb();
		service.initData();

		logger.info("Database has been reset.");
	}
}
