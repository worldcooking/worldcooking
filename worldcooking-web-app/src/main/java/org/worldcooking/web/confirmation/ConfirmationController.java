package org.worldcooking.web.confirmation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfirmationController {

	private final static Logger LOGGER = LoggerFactory
			.getLogger(ConfirmationController.class);

	@RequestMapping(value = "/registration/confirmation", method = RequestMethod.GET)
	public ModelAndView handleRequest() {
		ModelAndView modelAndView = new ModelAndView("confirmation");
		modelAndView.addObject("event", null);
		return modelAndView;
	}

}
