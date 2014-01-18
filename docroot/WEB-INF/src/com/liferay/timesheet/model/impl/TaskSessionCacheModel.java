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
import com.liferay.portal.model.CacheModel;

import com.liferay.timesheet.model.TaskSession;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing TaskSession in entity cache.
 *
 * @author Istvan Sajtos, Zsolt Szabo
 * @see TaskSession
 * @generated
 */
public class TaskSessionCacheModel implements CacheModel<TaskSession>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{taskSessionId=");
		sb.append(taskSessionId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", endTime=");
		sb.append(endTime);
		sb.append(", startTime=");
		sb.append(startTime);
		sb.append(", taskId=");
		sb.append(taskId);
		sb.append("}");

		return sb.toString();
	}

	public TaskSession toEntityModel() {
		TaskSessionImpl taskSessionImpl = new TaskSessionImpl();

		taskSessionImpl.setTaskSessionId(taskSessionId);

		if (createDate == Long.MIN_VALUE) {
			taskSessionImpl.setCreateDate(null);
		}
		else {
			taskSessionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			taskSessionImpl.setModifiedDate(null);
		}
		else {
			taskSessionImpl.setModifiedDate(new Date(modifiedDate));
		}

		taskSessionImpl.setUserId(userId);

		if (endTime == Long.MIN_VALUE) {
			taskSessionImpl.setEndTime(null);
		}
		else {
			taskSessionImpl.setEndTime(new Date(endTime));
		}

		if (startTime == Long.MIN_VALUE) {
			taskSessionImpl.setStartTime(null);
		}
		else {
			taskSessionImpl.setStartTime(new Date(startTime));
		}

		taskSessionImpl.setTaskId(taskId);

		taskSessionImpl.resetOriginalValues();

		return taskSessionImpl;
	}

	public long taskSessionId;
	public long createDate;
	public long modifiedDate;
	public long userId;
	public long endTime;
	public long startTime;
	public long taskId;
}