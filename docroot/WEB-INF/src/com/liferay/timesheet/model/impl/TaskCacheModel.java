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

package com.liferay.timesheet.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.timesheet.model.Task;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing Task in entity cache.
 *
 * @author Istvan Sajtos
 * @see Task
 * @generated
 */
public class TaskCacheModel implements CacheModel<Task>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{taskId=");
		sb.append(taskId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", taskName=");
		sb.append(taskName);
		sb.append("}");

		return sb.toString();
	}

	public Task toEntityModel() {
		TaskImpl taskImpl = new TaskImpl();

		taskImpl.setTaskId(taskId);
		taskImpl.setCompanyId(companyId);

		if (createDate == Long.MIN_VALUE) {
			taskImpl.setCreateDate(null);
		}
		else {
			taskImpl.setCreateDate(new Date(createDate));
		}

		taskImpl.setUserId(userId);

		if (taskName == null) {
			taskImpl.setTaskName(StringPool.BLANK);
		}
		else {
			taskImpl.setTaskName(taskName);
		}

		taskImpl.resetOriginalValues();

		return taskImpl;
	}

	public long taskId;
	public long companyId;
	public long createDate;
	public long userId;
	public String taskName;
}