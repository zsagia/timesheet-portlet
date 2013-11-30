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
import com.liferay.timesheet.NoSuchTaskException;
import com.liferay.timesheet.model.Task;
import com.liferay.timesheet.service.base.TaskLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

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
 * @see com.liferay.timesheet.service.base.TaskLocalServiceBaseImpl
 * @see com.liferay.timesheet.service.TaskLocalServiceUtil
 */
public class TaskLocalServiceImpl extends TaskLocalServiceBaseImpl {

	public Task addTask(String taskName, long creatorId)
		throws PortalException, SystemException {

		long taskId = counterLocalService.increment();

		User user = userPersistence.findByPrimaryKey(creatorId);

		Task task = taskPersistence.create(taskId);

		Date createDate = new Date();

		task.setCompanyId(user.getCompanyId());
		task.setCreateDate(createDate);
		task.setTaskName(taskName);
		task.setCreatorId(creatorId);

		taskPersistence.update(task, false);

		return task;
	}

	public Task getTaskByTN_CR(String taskName, long creatorId) {
		Task task = null;

		try {
			task = taskPersistence.findByTN_CR(taskName, creatorId);
		} catch (NoSuchTaskException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		}

		return task;
	}

	public List<Task> getTasksByCreatorId(long creatorId)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(creatorId);

		return taskPersistence.findByC_CR(user.getCompanyId(), creatorId);
	}

}