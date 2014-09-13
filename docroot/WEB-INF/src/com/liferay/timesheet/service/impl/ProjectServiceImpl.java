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
import com.liferay.timesheet.model.Project;
import com.liferay.timesheet.service.base.ProjectServiceBaseImpl;
import com.liferay.timesheet.service.permission.AdminPermission;
import com.liferay.timesheet.service.permission.ProjectPermission;
import com.liferay.timesheet.util.ActionKeys;

import java.util.Iterator;
import java.util.List;

/**
 * The implementation of the project remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.timesheet.service.ProjectService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Istvan Sajtos, Zsolt Szabo
 * @see com.liferay.timesheet.service.base.ProjectServiceBaseImpl
 * @see com.liferay.timesheet.service.ProjectServiceUtil
 */
public class ProjectServiceImpl extends ProjectServiceBaseImpl {

	public Project addProject(
			long userId, long departmentId, boolean enabled,
			long parentProjectId, String projectName, String description,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		AdminPermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			ActionKeys.ADD_PROJECT);

		return projectLocalService.addProject(
			userId, departmentId, enabled, parentProjectId, projectName,
			description, serviceContext);
	}

	public Project getProject(long projectId)
		throws PortalException, SystemException {

		ProjectPermission.contains(
			getPermissionChecker(), projectId, ActionKeys.VIEW);

		return projectLocalService.getProject(projectId);
	}

	public List<Project> getProjects(long companyId)
		throws PortalException, SystemException {

		List<Project> projects = projectLocalService.getProjects(companyId);

		projects = ListUtil.copy(projects);

		Iterator<Project> iterator = projects.iterator();

		while (iterator.hasNext()) {
			Project project = iterator.next();

			if (!ProjectPermission.contains(
					getPermissionChecker(), project, ActionKeys.VIEW)) {

				iterator.remove();
			}
		}

		return projects;
	}

	public Project updateProject(Project project)
		throws PortalException, SystemException {

		ProjectPermission.check(
			getPermissionChecker(), project, ActionKeys.UPDATE);

		return projectLocalService.updateProject(project);
	}

}