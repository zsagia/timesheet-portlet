/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.timesheet.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.timesheet.model.Department;
import com.liferay.timesheet.service.base.DepartmentServiceBaseImpl;
import com.liferay.timesheet.service.permission.AdminPermission;
import com.liferay.timesheet.service.permission.DepartmentPermission;
import com.liferay.timesheet.util.ActionKeys;

import java.util.Iterator;
import java.util.List;

/**
 * The implementation of the department remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.timesheet.service.DepartmentService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Istvan Sajtos
 * @author Zsolt Szabo
 * @see com.liferay.timesheet.service.base.DepartmentServiceBaseImpl
 * @see com.liferay.timesheet.service.DepartmentServiceUtil
 */
public class DepartmentServiceImpl extends DepartmentServiceBaseImpl {

	public Department addDepartment(
			long userId, String departmentName, ServiceContext serviceContext)
		throws PortalException, SystemException {

		AdminPermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			ActionKeys.ADD_DEPARTMENT);

		return departmentLocalService.addDepartment(
			userId, departmentName, serviceContext);
	}

	public Department getDepartment(long departmentId)
		throws PortalException, SystemException {

		DepartmentPermission.contains(
			getPermissionChecker(), departmentId, ActionKeys.VIEW);

		return departmentLocalService.getDepartment(departmentId);
	}

	public List<Department> getDepartments(long companyId)
		throws PortalException, SystemException {

		List<Department> departments = departmentLocalService.getDepartments(
			companyId);

		departments = ListUtil.copy(departments);

		Iterator<Department> iterator = departments.iterator();

		while (iterator.hasNext()) {
			Department department = iterator.next();

			if (!DepartmentPermission.contains(
					getPermissionChecker(), department, ActionKeys.VIEW)) {

				iterator.remove();
			}
		}

		return departments;
	}

	public Department updateDepartment(Department department)
		throws PortalException, SystemException {

		DepartmentPermission.check(
			getPermissionChecker(), department, ActionKeys.UPDATE);

		return departmentLocalService.updateDepartment(department);
	}

}