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

package com.liferay.timesheet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProjectService}.
 *
 * @author Istvan Sajtos, Zsolt Szabo
 * @see ProjectService
 * @generated
 */
public class ProjectServiceWrapper implements ProjectService,
	ServiceWrapper<ProjectService> {
	public ProjectServiceWrapper(ProjectService projectService) {
		_projectService = projectService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _projectService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_projectService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _projectService.invokeMethod(name, parameterTypes, arguments);
	}

	@Override
	public com.liferay.timesheet.model.Project addProject(long userId,
		long departmentId, boolean enabled, long parentProjectId,
		java.lang.String projectName, java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _projectService.addProject(userId, departmentId, enabled,
			parentProjectId, projectName, description, serviceContext);
	}

	@Override
	public com.liferay.timesheet.model.Project getProject(long projectId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _projectService.getProject(projectId);
	}

	@Override
	public java.util.List<com.liferay.timesheet.model.Project> getProjects(
		long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _projectService.getProjects(companyId);
	}

	@Override
	public com.liferay.timesheet.model.Project updateProject(
		com.liferay.timesheet.model.Project project)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _projectService.updateProject(project);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public ProjectService getWrappedProjectService() {
		return _projectService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedProjectService(ProjectService projectService) {
		_projectService = projectService;
	}

	@Override
	public ProjectService getWrappedService() {
		return _projectService;
	}

	@Override
	public void setWrappedService(ProjectService projectService) {
		_projectService = projectService;
	}

	private ProjectService _projectService;
}