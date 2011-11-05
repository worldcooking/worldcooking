/**
 * 
 */
package org.worldcooking.web.administration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author MatthieuG
 * 
 */
@Controller
public class AdministrationController {

	@RequestMapping(value = "/administration", method = RequestMethod.GET)
	public ModelAndView handleRequest() {
		ModelAndView modelAndView = new ModelAndView("administration");
		AdministrationResetDb resetDb = new AdministrationResetDb();

		modelAndView.addObject("administrationResetDb", resetDb);
		return modelAndView;
	}

}
