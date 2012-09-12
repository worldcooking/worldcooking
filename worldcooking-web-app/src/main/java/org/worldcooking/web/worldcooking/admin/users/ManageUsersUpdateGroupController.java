/**
 * 
 */
package org.worldcooking.web.worldcooking.admin.users;

import org.mishk.core.dao.exception.EntityIdNotFoundException;
import org.mishk.security.entity.SecurityUser;
import org.mishk.security.service.Role;
import org.mishk.security.service.SecurityUserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author MatthieuG
 * 
 */
@Controller
public class ManageUsersUpdateGroupController {

	private static final String JSP = "site/admin/security/manage_users";

	@Autowired
	private SecurityUserManagementService securityUserManagementService;

	@RequestMapping(value = "/admin/security/manage/user/{userId}/update/group/{group}", method = RequestMethod.GET)
	public String handleRequest(@PathVariable Long userId,
			@PathVariable String group) throws EntityIdNotFoundException {

		SecurityUser securityUser = securityUserManagementService
				.findUserById(userId);

		if (group.equals("super_admin")) {
			securityUserManagementService.grantUser(securityUser,
					Role.SUPER_ADMIN);
		} else if (group.equals("admin")) {
			securityUserManagementService.grantUser(securityUser, Role.ADMIN);
		} else {
			securityUserManagementService.grantUser(securityUser, Role.GUEST);
		}

		return "redirect:/admin/security/manage/users";
	}
}
