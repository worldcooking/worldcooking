package org.worldcooking.service.admin;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.mishk.business.event.entity.Direction;
import org.mishk.business.event.entity.Event;
import org.mishk.business.event.entity.Place;
import org.mishk.business.event.entity.Task;
import org.mishk.business.event.service.EventService;
import org.mishk.business.event.service.PlaceService;
import org.mishk.business.event.service.TaskService;
import org.mishk.business.shop.service.CatalogService;
import org.mishk.business.shop.service.ShoppingService;
import org.oupsasso.mishk.core.dao.exception.EntityNotFoundException;
import org.oupsasso.mishk.core.dao.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(rollbackFor = ServiceException.class)
public class DbInitializerService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private EventService eventService;

	@Autowired
	private PlaceService placeService;

	@Autowired
	private WorldcookingService worldcookingService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private CatalogService catalogService;

	@Autowired
	private ShoppingService shoppingService;

	public void resetDb() {
		logger.info("The database will be reset now.");

		eventService.deleteAllEvents();

		placeService.deleteAllPlaces();

		catalogService.deleteAllCatalogs();
		catalogService.deleteAllStocks();
		shoppingService.deleteAllShoppingBagProduct();
		shoppingService.deleteAllShoppingBags();
		catalogService.deleteAllProducts();

		taskService.deleteAllRoles();
	}

	public void initData() throws EntityNotFoundException {
		// init DB with worldcooking Portugal event
		Event e = eventService.createNewEvent("Worldcooking Portugal");

		e.setDateTime(parseDate("2011-01-20_19:00:00"));

		Place place = initPlace();
		eventService.saveOrUpdate(place);

		e.setPlace(place);

		// tasks
		initRolesAndTasks(e);

		eventService.saveOrUpdate(e);
	}

	private void initRolesAndTasks(Event e) throws EntityNotFoundException {
		// create tasks
		Task taskCook = taskService.createNewTask("Cook", "prepare the meal");
		Task taskPrepareTheRoom = taskService.createNewTask("Prepare the room", "set the tables and chairs");
		Task taskSettingTheTable = taskService.createNewTask("Setting the table", "setting the plates, glasses...");
		Task taskDoingTheDishes = taskService.createNewTask("Doing the dishes", "washing all the dishes");
		Task taskCleaningTheRoom = taskService.createNewTask("Cleaning the room", "cleaning the room");

		// create roles
		worldcookingService.createNewRole(e, "Chef", "diner chef", 1l, 0d, taskCook);
		worldcookingService.createNewRole(e, "Cook", "cooking with the chef from 4pm", 7l, 15d, taskCook);
		worldcookingService.createNewRole(e, "Prepare", "prepare the room and settings the table from 6:30pm", 4l, 15d,
				taskPrepareTheRoom, taskSettingTheTable);
		worldcookingService.createNewRole(e, "Cleanning", "doing the dishes after the dinner (until 1am maxi)", 16l,
				15d, taskDoingTheDishes, taskCleaningTheRoom);

	}

	private Place initPlace() {
		// set place
		Place place = placeService.createNewPlace("La soupe au Caillou", "31200", "Toulouse", "France");

		Direction direction = place.getDirection();
		direction.addAddressline("15 Rue Charles Gounod");
		direction.setLatitude(43.61368640000001d);
		direction.setLongitude(1.4242076000000452d);
		return place;
	}

	private Date parseDate(String dateToParse) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		Date date = null;
		try {
			date = df.parse(dateToParse);

		} catch (ParseException ex) {
			logger.error("Error while formatting date.", ex);
		}
		return date;
	}

}
