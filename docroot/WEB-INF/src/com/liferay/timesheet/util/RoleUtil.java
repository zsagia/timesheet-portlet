/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.timesheet.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.timesheet.model.Task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
* @author Zsolt Szabo
*/

public class RoleUtil {

	public static List<Role> getAssignedRoles(
			long companyId, Task task, String action)
		throws PortalException, SystemException {

		List<Role> roleList = RoleLocalServiceUtil.getRoles(
			companyId);

		List<Role> assignedRoles = new ArrayList<Role>();

		for (Role role: roleList) {
			if (!isFilteredRole(role) &&
				ResourcePermissionLocalServiceUtil.hasResourcePermission(
					companyId, Task.class.getName(),
					ResourceConstants.SCOPE_INDIVIDUAL,
					String.valueOf(task.getTaskId()), role.getRoleId(),
					action)) {

				assignedRoles.add(role);
			}
		}

		return assignedRoles;
	}

	public static List<Role> getRegularRoles() throws SystemException {
		return filterRoles(RoleLocalServiceUtil.getRoles(
			RoleConstants.TYPE_REGULAR, StringPool.BLANK));
	}

	public static boolean isFilteredRole(Role role) {
		boolean isFiltered = false;

		if (TimeSheetConstants.TIMESHEET_TASK_ROLE.equals(role.getSubtype()) ||
			RoleConstants.ADMINISTRATOR.equals(role.getName()) ||
			RoleConstants.GUEST.equals(role.getName()) ||
			RoleConstants.OWNER.equals(role.getName())) {

				isFiltered = true;
		}

		return isFiltered;
	}

	public static List<Role> filterRoles(List<Role> roles) {
		roles = ListUtil.copy(roles);

		Iterator<Role> iterator = roles.iterator();

		while(iterator.hasNext()) {
			Role role = iterator.next();

			if (isFilteredRole(role)) {
				iterator.remove();
			}
		}

		return roles;
	}

}