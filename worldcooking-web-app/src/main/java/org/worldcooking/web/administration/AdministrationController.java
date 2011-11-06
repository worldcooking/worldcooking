/**
 * 
 */
package org.worldcooking.web.administration;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.worldcooking.server.services.EventService;

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

		modelAndView.addObject("administrationResetDb", resetDb);

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(
			@Valid @ModelAttribute("administrationResetDb") AdministrationValidate validate,
			BindingResult result) throws Exception {

		if (result.hasErrors()) {

			return "administration";
		}

		if ("supercoincoin".equals(validate.getPassword())) {
			eventService.resetDb();
		}
		// redirect to main page
		return "administration";
	}
}
