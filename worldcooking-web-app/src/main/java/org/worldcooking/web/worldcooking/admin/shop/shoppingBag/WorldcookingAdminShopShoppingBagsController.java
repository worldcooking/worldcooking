/**
 * 
 */
package org.worldcooking.web.worldcooking.admin.shop.shoppingBag;

import java.util.Set;

import org.mishk.business.shop.entity.ShoppingBag;
import org.mishk.business.shop.service.ShoppingService;
import org.oupsasso.mishk.core.dao.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author MatthieuG
 * 
 */
@Controller
public class WorldcookingAdminShopShoppingBagsController {

	private static final String URL = "/direct/admin/shop/shopping-bags";
	private static final String JSP = "worldcooking/admin/shop/shoppingBag/worldcooking-admin-shop-shopping-bags";

	@Autowired
	private ShoppingService shoppingService;

	@RequestMapping(value = URL, method = RequestMethod.GET)
	public ModelAndView handleRequest() throws ServiceException {
		ModelAndView modelAndView = new ModelAndView(JSP);

		return modelAndView;
	}

	@ModelAttribute("shoppingBags")
	public Set<ShoppingBag> getShoppingBags() {
		return shoppingService.findAllShoppingBags(true);
	}
}
