package org.worldcooking.web.worldcooking.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author MatthieuG
 * 
 */
@Controller
public class LoginController {

	private static final String JSP = "site/security/authentication/login";

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String handleRequest() {
		return JSP;
	}
}