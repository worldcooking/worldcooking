/**
 * 
 */
package org.worldcooking.web.admin.db;

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
public class AdminDbController {

	private static final String JSP = "admin/admin-db";
	@Autowired
	private EventService eventService;

	@RequestMapping(value = "/admin/db", method = RequestMethod.GET)
	public ModelAndView handleRequest() {
		ModelAndView modelAndView = new ModelAndView(JSP);
		AdministrationResetDb resetDb = new AdministrationResetDb();

		modelAndView.addObject("administrationResetDb", resetDb);

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(
			@Valid @ModelAttribute("administrationResetDb") AdministrationResetDb validate,
			BindingResult result) throws Exception {

		if (result.hasErrors()) {

			return JSP;
		}

		if ("supercoincoin".equals(validate.getPassword())) {
			eventService.resetDb();
			// redirect to main page
			return "redirect:/";
		}

		return JSP;

	}
}
