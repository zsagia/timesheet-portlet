package com.liferay.timesheet.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.timesheet.model.Department;
import com.liferay.timesheet.service.DepartmentLocalServiceUtil;

public class DepartmentPermission {

	public static void check(
			PermissionChecker permissionChecker, Department department,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, department, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long departmentId,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, departmentId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, Department department,
		String actionId) {

		if (permissionChecker.hasOwnerPermission(
			department.getCompanyId(), Department.class.getName(),
			department.getDepartmentId(), department.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			department.getGroupId(), Department.class.getName(),
			department.getDepartmentId(), actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long departmentId,
			String actionId)
		throws PortalException, SystemException {

		Department department = DepartmentLocalServiceUtil.getDepartment(
			departmentId);

		return contains(permissionChecker, department, actionId);
	}

}