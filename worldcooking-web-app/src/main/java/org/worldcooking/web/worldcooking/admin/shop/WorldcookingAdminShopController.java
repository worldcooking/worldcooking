/**
 * 
 */
package org.worldcooking.web.worldcooking.admin.shop;

import org.mishk.business.event.service.EventService;
import org.oupsasso.mishk.core.dao.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author MatthieuG
 * 
 */
@Controller
public class WorldcookingAdminShopController {

	private static final String URL = "/admin/shop";
	private static final String JSP = "worldcooking/admin/shop/worldcooking-admin-shop";
	@Autowired
	private EventService eventService;

	@RequestMapping(value = URL, method = RequestMethod.GET)
	public ModelAndView handleRequest() throws ServiceException {
		ModelAndView modelAndView = new ModelAndView(JSP);

		return modelAndView;
	}

}
