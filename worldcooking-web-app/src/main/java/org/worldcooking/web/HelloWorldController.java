package org.worldcooking.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.worldcooking.server.services.EventService;

/**
 * Simple index page controller serving hello.jsp file 
 */
@Controller
public class HelloWorldController {

	@Autowired
	private EventService eventService;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
    /**
     * Simply serves hello.jsp
     * @return view with name 'hello'
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String handleRequest() {

    	logger.info("Nombre d'évents:"+  eventService.getAllEvents().size());
        return "hello";
    }

}
