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
 * Provides a wrapper for {@link TaskService}.
 *
 * @author Istvan Sajtos, Zsolt Szabo
 * @see TaskService
 * @generated
 */
public class TaskServiceWrapper implements TaskService,
	ServiceWrapper<TaskService> {
	public TaskServiceWrapper(TaskService taskService) {
		_taskService = taskService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _taskService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_taskService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _taskService.invokeMethod(name, parameterTypes, arguments);
	}

	@Override
	public com.liferay.timesheet.model.Task getTask(long taskId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _taskService.getTask(taskId);
	}

	@Override
	public java.util.List<com.liferay.timesheet.model.Task> getTasksByC_G_U(
		long companyId, long groupId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _taskService.getTasksByC_G_U(companyId, groupId, userId);
	}

	@Override
	public java.util.List<com.liferay.timesheet.model.Task> getTasksByC_G_TT(
		long companyId, long groupId, int taskType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _taskService.getTasksByC_G_TT(companyId, groupId, taskType);
	}

	@Override
	public java.util.List<com.liferay.timesheet.model.Task> getTasksByC_G_U_TT(
		long companyId, long groupId, long userId, int taskType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _taskService.getTasksByC_G_U_TT(companyId, groupId, userId,
			taskType);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public TaskService getWrappedTaskService() {
		return _taskService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedTaskService(TaskService taskService) {
		_taskService = taskService;
	}

	@Override
	public TaskService getWrappedService() {
		return _taskService;
	}

	@Override
	public void setWrappedService(TaskService taskService) {
		_taskService = taskService;
	}

	private TaskService _taskService;
}