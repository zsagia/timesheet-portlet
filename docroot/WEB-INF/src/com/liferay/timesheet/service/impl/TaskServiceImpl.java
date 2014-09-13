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
import com.liferay.timesheet.model.Task;
import com.liferay.timesheet.service.base.TaskServiceBaseImpl;
import com.liferay.timesheet.service.permission.TaskPermission;
import com.liferay.timesheet.util.ActionKeys;

import java.util.Iterator;
import java.util.List;

/**
 * The implementation of the task remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.timesheet.service.TaskService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Istvan Sajtos, Zsolt Szabo
 * @see com.liferay.timesheet.service.base.TaskServiceBaseImpl
 * @see com.liferay.timesheet.service.TaskServiceUtil
 */
public class TaskServiceImpl extends TaskServiceBaseImpl {

	public Task getTask(long taskId) throws PortalException, SystemException {
		TaskPermission.contains(
			getPermissionChecker(), taskId, ActionKeys.VIEW);

		return taskLocalService.getTask(taskId);
	}

	public List<Task> getTasksByC_G_TT(
			long companyId, long groupId, int taskType)
		throws PortalException, SystemException {

		return taskLocalService.getTasksByC_G_TT(companyId, groupId, taskType);
	}

	public List<Task> getTasksByC_G_U(long companyId, long groupId, long userId)
		throws PortalException, SystemException {

		List<Task> tasks = taskLocalService.getTasksByC_G_U(
			companyId, groupId, userId);

		tasks = ListUtil.copy(tasks);

		Iterator<Task> iterator = tasks.iterator();

		while (iterator.hasNext()) {
			Task task = iterator.next();

			if (!TaskPermission.contains(
					getPermissionChecker(), task, ActionKeys.VIEW)) {

				iterator.remove();
			}
		}

		return tasks;
	}

	public List<Task> getTasksByC_G_U_TT(
			long companyId, long groupId, long userId, int taskType)
		throws PortalException, SystemException {

		List<Task> tasks = taskLocalService.getTasksByC_G_U_TT(
			companyId, groupId, userId, taskType);

		tasks = ListUtil.copy(tasks);

		Iterator<Task> iterator = tasks.iterator();

		while (iterator.hasNext()) {
			Task task = iterator.next();

			if (!TaskPermission.contains(
					getPermissionChecker(), task, ActionKeys.VIEW)) {

				iterator.remove();
			}
		}

		return tasks;
	}

}