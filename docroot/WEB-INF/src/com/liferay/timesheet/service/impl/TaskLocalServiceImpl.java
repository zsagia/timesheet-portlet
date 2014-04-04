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
import com.liferay.timesheet.model.Task;
import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.service.base.TaskLocalServiceBaseImpl;
import com.liferay.timesheet.util.TaskConstants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The implementation of the task local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.timesheet.service.TaskLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Istvan Sajtos
 * @author Zsolt Szabo
 * @see com.liferay.timesheet.service.base.TaskLocalServiceBaseImpl
 * @see com.liferay.timesheet.service.TaskLocalServiceUtil
 */
public class TaskLocalServiceImpl extends TaskLocalServiceBaseImpl {

	public Task addTask(
			long userId, String taskName, long projectId, String description,
			int taskType, ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		long groupId = serviceContext.getScopeGroupId();

		long taskId = counterLocalService.increment();

		Task task = taskPersistence.create(taskId);

		Date now = new Date();

		task.setCompanyId(user.getCompanyId());
		task.setCreateDate(now);
		task.setGroupId(groupId);
		task.setModifiedDate(now);
		task.setProjectId(projectId);
		task.setTaskName(taskName);
		task.setTaskType(taskType);
		task.setUserId(userId);
		task.setUserName(user.getFullName());

		taskPersistence.update(task);

		return task;
	}

	public Task getTaskByTN_CR(String taskName, long creatorId)
		throws PortalException, SystemException {

		return taskPersistence.findByTN_CR(taskName, creatorId);
	}

	public List<Task> getTasksByCreatorId(long creatorId)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(creatorId);

		return taskPersistence.findByC_CR(user.getCompanyId(), creatorId);
	}

	public Task getTaskByType(int taskType) throws SystemException {
		List<Task> taskList = taskPersistence.findByTaskType(taskType);

		if (!taskList.isEmpty()) {
			return taskList.get(0);
		}

		return null;
	}

	public List<Task> getTasksByUserId(long userId)
		throws PortalException, SystemException {

		return getTasksByU_T(userId, TaskConstants.GENERAL_TASK);
	}

	public List<Task> getTasksByU_T(long userId, int taskType)
			throws PortalException, SystemException {

		List<TaskSession> taskSessionList =
			taskSessionPersistence.findByUserId(userId);

		if (taskSessionList == null) {
			return Collections.emptyList();
		}

		Set<Long> taskIds = new HashSet<Long>();

		List<Task> taskList = new ArrayList<Task>();

		for (TaskSession taskSession : taskSessionList) {
			Task task = taskSession.getTask();

			long taskId = task.getTaskId();

			if (!taskIds.contains(taskId) && (task.getTaskType() == taskType)) {
				taskIds.add(taskId);

				taskList.add(task);
			}
		}

		return taskList;
	}

}