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

package com.liferay.timesheet.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.timesheet.model.Project;
import com.liferay.timesheet.service.base.ProjectLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * The implementation of the project local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.timesheet.service.ProjectLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Istvan Sajtos
 * @author Zsolt Szabo
 * @see com.liferay.timesheet.service.base.ProjectLocalServiceBaseImpl
 * @see com.liferay.timesheet.service.ProjectLocalServiceUtil
 */
public class ProjectLocalServiceImpl extends ProjectLocalServiceBaseImpl {
	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.liferay.timesheet.service.ProjectLocalServiceUtil} to access the project local service.
	 */

	public Project addProject(
			long userId, long ownerGroupId, boolean enabled,
			long parentProjectId, String projectName, String description,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		long groupId = serviceContext.getScopeGroupId();

		long projectId = counterLocalService.increment();

		Project project = projectPersistence.create(projectId);

		Date now = new Date();

		project.setCompanyId(user.getCompanyId());
		project.setCreateDate(now);
		project.setOwnerGroupId(ownerGroupId);
		project.setDescription(description);
		project.setEnabled(enabled);
		project.setGroupId(groupId);
		project.setModifiedDate(now);
		project.setParentProjectId(parentProjectId);
		project.setProjectName(projectName);
		project.setUserId(userId);
		project.setUserName(user.getFullName());

		projectPersistence.update(project);

		addProjectResources(
			project, serviceContext.getGroupPermissions(),
			serviceContext.getGuestPermissions());

		return project;
	}

	public void addProjectResources(
			Project project, boolean addGroupPermissions,
			boolean addGuestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.addResources(
			project.getCompanyId(), project.getGroupId(), project.getUserId(),
			Project.class.getName(), project.getProjectId(), false,
			addGroupPermissions, addGuestPermissions);
	}

	public void addProjectResources(
			Project project, String[] groupPermissions,
			String[] guestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.addModelResources(
			project.getCompanyId(), project.getGroupId(), project.getUserId(),
			Project.class.getName(), project.getProjectId(), groupPermissions,
			guestPermissions);
	}

	public List<Project> getProjects(long parentProjectId)
		throws SystemException {

		return projectPersistence.findByParentProjectId(parentProjectId);
	}
}