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
 * @author Istvan Sajtos, Zsolt Szabo
 * @see com.liferay.timesheet.service.base.DepartmentLocalServiceBaseImpl
 * @see com.liferay.timesheet.service.DepartmentLocalServiceUtil
 */
public class DepartmentLocalServiceImpl extends DepartmentLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.liferay.timesheet.service.DepartmentLocalServiceUtil} to access the department local service.
	 */

	public Department addDepartment(
			String departmentName, long creatorId)
		throws PortalException, SystemException {

		long departmentId = counterLocalService.increment();

		User user = userPersistence.findByPrimaryKey(creatorId);

		Department department = departmentPersistence.create(departmentId);

		Date createDate = new Date();

		department.setCompanyId(user.getCompanyId());
		department.setCreateDate(createDate);
		department.setDepartmentName(departmentName);
		department.setCreatorId(creatorId);

		departmentPersistence.update(department);

		return department;
	}

	public Department getDepartment(long departmentId)
		throws SystemException, NoSuchDepartmentException {

		return departmentPersistence.findByPrimaryKey(departmentId);
	}

	public List<Department> getDepartments(long companyId)
		throws SystemException {

		return departmentPersistence.findByCompanyId(companyId);
	}

}