/**
 * 
 */
package org.worldcooking.web.worldcooking.admin.shop.stock;

import java.util.Set;

import org.mishk.business.shop.entity.Stock;
import org.mishk.business.shop.service.CatalogService;
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
public class WorldcookingAdminShopStocksController {

	private static final String URL = "/direct/admin/shop/stocks";
	private static final String JSP = "worldcooking/admin/shop/stock/worldcooking-admin-shop-stocks";

	@Autowired
	private CatalogService catalogService;

	@RequestMapping(value = URL, method = RequestMethod.GET)
	public ModelAndView handleRequest() throws ServiceException {
		ModelAndView modelAndView = new ModelAndView(JSP);

		return modelAndView;
	}

	@ModelAttribute("stocks")
	public Set<Stock> getStocks() {
		return catalogService.findAllStocks(true);
	}
}
