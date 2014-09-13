package com.liferay.timesheet.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.timesheet.model.Project;
import com.liferay.timesheet.service.ProjectLocalServiceUtil;
public class ProjectPermission {

	public static void check(
			PermissionChecker permissionChecker, long projectId,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, projectId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, Project project,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, project, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long projectId,
			String actionId)
		throws PortalException, SystemException {

		Project project = ProjectLocalServiceUtil.getProject(projectId);

		return contains(permissionChecker, project, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, Project project, String actionId) {

		if (permissionChecker.hasOwnerPermission(
				project.getCompanyId(), Project.class.getName(),
			project.getProjectId(), project.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			project.getGroupId(), Project.class.getName(),
			project.getProjectId(), actionId);
	}

}