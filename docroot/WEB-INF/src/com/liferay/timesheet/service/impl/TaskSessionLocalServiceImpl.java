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

import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.service.base.TaskSessionLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * The implementation of the task session local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.timesheet.service.TaskSessionLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Istvan Sajtos
 * @author Zsolt Szabo
 * @see com.liferay.timesheet.service.base.TaskSessionLocalServiceBaseImpl
 * @see com.liferay.timesheet.service.TaskSessionLocalServiceUtil
 */
public class TaskSessionLocalServiceImpl
	extends TaskSessionLocalServiceBaseImpl {

	public TaskSession addTaskSession(
			long userId, Date startTime, Date endTime, long taskId,
			String description, ServiceContext serviceContext)
		throws NoSuchUserException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		long groupId = serviceContext.getScopeGroupId();

		long taskSessionId = counterLocalService.increment();

		TaskSession taskSession = taskSessionPersistence.create(taskSessionId);

		Date now = new Date();

		taskSession.setCompanyId(user.getCompanyId());
		taskSession.setCreateDate(now);
		taskSession.setDescription(description);
		taskSession.setEndTime(endTime);
		taskSession.setGroupId(groupId);
		taskSession.setModifiedDate(now);
		taskSession.setStartTime(startTime);
		taskSession.setTaskId(taskId);
		taskSession.setUserId(user.getUserId());
		taskSession.setUserName(user.getFullName());

		taskSessionPersistence.update(taskSession);

		return taskSession;
	}

	public TaskSession addTaskSession(
			long userId, Date startTime, long taskId, String description,
			ServiceContext serviceContext)
		throws NoSuchUserException, SystemException {

		return addTaskSession(
			userId, startTime, null, taskId, description, serviceContext);
	}

	public TaskSession getCurrentTaskSession(long userId)
		throws SystemException {

		return taskSessionPersistence.fetchByU_E(userId, null);
	}

	public TaskSession getLastTaskSessionsByU_D(long userId, Date date)
		throws SystemException {

		List<TaskSession> taskSessions = taskSessionPersistence.findByU_GtS(
			userId, date);

		if ((taskSessions != null) && !taskSessions.isEmpty()) {
			return taskSessions.get(0);
		}

		return null;
	}

	public List<TaskSession> getTaskSessionsByC_U_I(
			long companyId, long userId, Date date1, Date date2)
		throws SystemException {

		List<TaskSession> taskSessions = taskSessionFinder.findByC_U_I(
			companyId, userId, date1, date2);

		return taskSessions;
	}

	public List<TaskSession> getTaskSessionsByU_D(long userId, Date date)
		throws SystemException {

		List<TaskSession> taskSessions = taskSessionPersistence.findByU_GtS(
			userId, date);

		return taskSessions;
	}

	public List<TaskSession> getTaskSessionsByU_T_D(
			long userId, long taskId, Date date)
		throws SystemException {

		List<TaskSession> taskSessions = taskSessionPersistence.findByU_T_GtS(
			userId, taskId, date);

		return taskSessions;
	}

	public List<TaskSession> getTaskSessionsByU_T_I(
			long userId, long taskId, Date date1, Date date2)
		throws SystemException {

		List<TaskSession> taskSessions = taskSessionFinder.findByU_T_I(
			userId, taskId, date1, date2);

		return taskSessions;
	}

	public TaskSession updateTaskSession(TaskSession taskSession)
		throws SystemException {

		Date now = new Date();

		taskSession.setModifiedDate(now);

		taskSessionPersistence.update(taskSession);

		return taskSession;
	}

}