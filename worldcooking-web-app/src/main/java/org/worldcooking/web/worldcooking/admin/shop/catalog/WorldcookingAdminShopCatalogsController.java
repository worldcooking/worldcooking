/**
 * 
 */
package org.worldcooking.web.worldcooking.admin.shop.catalog;

import org.oupsasso.mishk.core.dao.exception.ServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author MatthieuG
 * 
 */
@Controller
public class WorldcookingAdminShopCatalogsController {

	private static final String URL = "/direct/admin/shop/catalogs";
	private static final String JSP = "worldcooking/admin/shop/catalog/worldcooking-admin-shop-catalogs";

	@RequestMapping(value = URL, method = RequestMethod.GET)
	public ModelAndView handleRequest() throws ServiceException {
		ModelAndView modelAndView = new ModelAndView(JSP);

		return modelAndView;
	}

}
