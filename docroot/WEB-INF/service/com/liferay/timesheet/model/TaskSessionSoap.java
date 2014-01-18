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

package com.liferay.timesheet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author    Istvan Sajtos, Zsolt Szabo
 * @generated
 */
public class TaskSessionSoap implements Serializable {
	public static TaskSessionSoap toSoapModel(TaskSession model) {
		TaskSessionSoap soapModel = new TaskSessionSoap();

		soapModel.setTaskSessionId(model.getTaskSessionId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setUserId(model.getUserId());
		soapModel.setEndTime(model.getEndTime());
		soapModel.setStartTime(model.getStartTime());
		soapModel.setTaskId(model.getTaskId());

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

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public Date getEndTime() {
		return _endTime;
	}

	public void setEndTime(Date endTime) {
		_endTime = endTime;
	}

	public Date getStartTime() {
		return _startTime;
	}

	public void setStartTime(Date startTime) {
		_startTime = startTime;
	}

	public long getTaskId() {
		return _taskId;
	}

	public void setTaskId(long taskId) {
		_taskId = taskId;
	}

	private long _taskSessionId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _userId;
	private Date _endTime;
	private Date _startTime;
	private long _taskId;
}