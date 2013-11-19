/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.timesheet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.timesheet.service.http.TaskSessionServiceSoap}.
 *
 * @author    Istvan Sajtos
 * @see       com.liferay.timesheet.service.http.TaskSessionServiceSoap
 * @generated
 */
public class TaskSessionSoap implements Serializable {
	public static TaskSessionSoap toSoapModel(TaskSession model) {
		TaskSessionSoap soapModel = new TaskSessionSoap();

		soapModel.setTaskSessionId(model.getTaskSessionId());
		soapModel.setTaskId(model.getTaskId());
		soapModel.setStartTime(model.getStartTime());
		soapModel.setEndTime(model.getEndTime());

		return soapModel;
	}

	public static TaskSessionSoap[] toSoapModels(TaskSession[] models) {
		TaskSessionSoap[] soapModels = new TaskSessionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TaskSessionSoap[][] toSoapModels(TaskSession[][] models) {
		TaskSessionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TaskSessionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TaskSessionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TaskSessionSoap[] toSoapModels(List<TaskSession> models) {
		List<TaskSessionSoap> soapModels = new ArrayList<TaskSessionSoap>(models.size());

		for (TaskSession model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TaskSessionSoap[soapModels.size()]);
	}

	public TaskSessionSoap() {
	}

	public long getPrimaryKey() {
		return _taskSessionId;
	}

	public void setPrimaryKey(long pk) {
		setTaskSessionId(pk);
	}

	public long getTaskSessionId() {
		return _taskSessionId;
	}

	public void setTaskSessionId(long taskSessionId) {
		_taskSessionId = taskSessionId;
	}

	public long getTaskId() {
		return _taskId;
	}

	public void setTaskId(long taskId) {
		_taskId = taskId;
	}

	public Date getStartTime() {
		return _startTime;
	}

	public void setStartTime(Date startTime) {
		_startTime = startTime;
	}

	public Date getEndTime() {
		return _endTime;
	}

	public void setEndTime(Date endTime) {
		_endTime = endTime;
	}

	private long _taskSessionId;
	private long _taskId;
	private Date _startTime;
	private Date _endTime;
}