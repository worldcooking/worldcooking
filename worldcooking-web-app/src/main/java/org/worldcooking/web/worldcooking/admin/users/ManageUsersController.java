/**
 * 
 */
package org.worldcooking.web.worldcooking.admin.users;

import java.util.Set;

import org.mishk.security.entity.SecurityGroup;
import org.mishk.security.service.SecurityUserManagementService;
import org.mishk.web.taglib.menu.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author MatthieuG
 * 
 */
@Controller
public class ManageUsersController {

	private static final String JSP = "site/admin/security/manage_users";

	@Autowired
	private SecurityUserManagementService securityUserManagementService;

	@RequestMapping(value = "/admin/security/manage/users", method = RequestMethod.GET)
	public String handleRequest() {
		return JSP;
	}

	@ModelAttribute("groups")
	public Set<SecurityGroup> getGroups() {
		return securityUserManagementService.findAllGroupsWithUsers();
	}
}
