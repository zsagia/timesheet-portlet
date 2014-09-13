package com.liferay.timesheet.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.timesheet.model.Task;
import com.liferay.timesheet.service.TaskLocalServiceUtil;

/**
* @author Zsolt Szabo
*/

public class TaskPermission {

	public static void check(
			PermissionChecker permissionChecker, Task task,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, task, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long taskId,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, taskId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, Task task, String actionId) {

		if (permissionChecker.hasOwnerPermission(
			task.getGroupId(), Task.class.getName(),
			task.getTaskId(), task.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			task.getGroupId(), Task.class.getName(),
			task.getTaskId(), actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long taskId,
			String actionId)
		throws PortalException, SystemException {

		Task task = TaskLocalServiceUtil.getTask(
			taskId);

		return contains(permissionChecker, task, actionId);
	}

}