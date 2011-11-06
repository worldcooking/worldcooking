/**
 * 
 */
package org.worldcooking.web.administration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.worldcooking.server.entity.event.Event;
import org.worldcooking.server.entity.event.Subscription;
import org.worldcooking.server.services.EventService;
import org.worldcooking.web.util.ModelViewMapper;

/**
 * @author MatthieuG
 * 
 */
@Controller
public class AdministrationController {

	@Autowired
	private EventService eventService;

	@RequestMapping(value = "/administration", method = RequestMethod.GET)
	public ModelAndView handleRequest() {
		ModelAndView modelAndView = new ModelAndView("administration");
		AdministrationResetDb resetDb = new AdministrationResetDb();

		AdministrationValidate validate = new AdministrationValidate();

		modelAndView.addObject("administrationResetDb", resetDb);
		modelAndView.addObject("administrationValidate", validate);
		return modelAndView;
	}

	@ModelAttribute("waitingSubscription")
	public List<SubscriptionModel> populateAvailableTasks() {
		List<SubscriptionModel> waitingSubscription = new ArrayList<SubscriptionModel>();

		Event event = eventService.getFullEventById(10L);

		for (Subscription s : event.getSubscriptions()) {
			if (!s.getValidate()) {
				SubscriptionModel destination = ModelViewMapper.getInstance()
						.map(s, SubscriptionModel.class);
				waitingSubscription.add(destination);
			}
		}

		return waitingSubscription;
	}
}
