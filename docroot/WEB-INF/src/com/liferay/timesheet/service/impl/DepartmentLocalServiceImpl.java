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
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.timesheet.NoSuchDepartmentException;
import com.liferay.timesheet.model.Department;
import com.liferay.timesheet.service.base.DepartmentLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * The implementation of the department local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.timesheet.service.DepartmentLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Istvan Sajtos
 * @author Zsolt Szabo
 * @see com.liferay.timesheet.service.base.DepartmentLocalServiceBaseImpl
 * @see com.liferay.timesheet.service.DepartmentLocalServiceUtil
 */
public class DepartmentLocalServiceImpl extends DepartmentLocalServiceBaseImpl {

	public Department addDepartment(
			long userId, String departmentName, ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		long groupId = serviceContext.getScopeGroupId();

		long departmentId = counterLocalService.increment();

		Department department = departmentPersistence.create(departmentId);

		Date now = new Date();

		department.setCompanyId(user.getCompanyId());
		department.setCreateDate(now);
		department.setDepartmentName(departmentName);
		department.setGroupId(groupId);
		department.setModifiedDate(now);
		department.setUserId(userId);
		department.setUserName(user.getFullName());

		departmentPersistence.update(department);

		addDepartmentResources(
			department, serviceContext.getGroupPermissions(),
			serviceContext.getGuestPermissions());

		return department;
	}

	public void addDepartmentResources(
			Department department, String[] groupPermissions,
			String[] guestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.addModelResources(
			department.getCompanyId(), department.getGroupId(),
			department.getUserId(), Department.class.getName(),
			department.getDepartmentId(), groupPermissions, guestPermissions);
	}

	public Department getDepartment(long departmentId)
		throws NoSuchDepartmentException, SystemException {

		return departmentPersistence.findByPrimaryKey(departmentId);
	}

	public List<Department> getDepartments(long companyId)
		throws SystemException {

		return departmentPersistence.findByCompanyId(companyId);
	}

}