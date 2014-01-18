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

import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Task}.
 * </p>
 *
 * @author    Istvan Sajtos, Zsolt Szabo
 * @see       Task
 * @generated
 */
public class TaskWrapper implements Task, ModelWrapper<Task> {
	public TaskWrapper(Task task) {
		_task = task;
	}

	public Class<?> getModelClass() {
		return Task.class;
	}

	public String getModelClassName() {
		return Task.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("taskId", getTaskId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("creatorId", getCreatorId());
		attributes.put("taskName", getTaskName());
		attributes.put("projectId", getProjectId());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long taskId = (Long)attributes.get("taskId");

		if (taskId != null) {
			setTaskId(taskId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Long creatorId = (Long)attributes.get("creatorId");

		if (creatorId != null) {
			setCreatorId(creatorId);
		}

		String taskName = (String)attributes.get("taskName");

		if (taskName != null) {
			setTaskName(taskName);
		}

		Long projectId = (Long)attributes.get("projectId");

		if (projectId != null) {
			setProjectId(projectId);
		}
	}

	/**
	* Returns the primary key of this task.
	*
	* @return the primary key of this task
	*/
	public long getPrimaryKey() {
		return _task.getPrimaryKey();
	}

	/**
	* Sets the primary key of this task.
	*
	* @param primaryKey the primary key of this task
	*/
	public void setPrimaryKey(long primaryKey) {
		_task.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the task ID of this task.
	*
	* @return the task ID of this task
	*/
	public long getTaskId() {
		return _task.getTaskId();
	}

	/**
	* Sets the task ID of this task.
	*
	* @param taskId the task ID of this task
	*/
	public void setTaskId(long taskId) {
		_task.setTaskId(taskId);
	}

	/**
	* Returns the company ID of this task.
	*
	* @return the company ID of this task
	*/
	public long getCompanyId() {
		return _task.getCompanyId();
	}

	/**
	* Sets the company ID of this task.
	*
	* @param companyId the company ID of this task
	*/
	public void setCompanyId(long companyId) {
		_task.setCompanyId(companyId);
	}

	/**
	* Returns the create date of this task.
	*
	* @return the create date of this task
	*/
	public java.util.Date getCreateDate() {
		return _task.getCreateDate();
	}

	/**
	* Sets the create date of this task.
	*
	* @param createDate the create date of this task
	*/
	public void setCreateDate(java.util.Date createDate) {
		_task.setCreateDate(createDate);
	}

	/**
	* Returns the creator ID of this task.
	*
	* @return the creator ID of this task
	*/
	public long getCreatorId() {
		return _task.getCreatorId();
	}

	/**
	* Sets the creator ID of this task.
	*
	* @param creatorId the creator ID of this task
	*/
	public void setCreatorId(long creatorId) {
		_task.setCreatorId(creatorId);
	}

	/**
	* Returns the task name of this task.
	*
	* @return the task name of this task
	*/
	public java.lang.String getTaskName() {
		return _task.getTaskName();
	}

	/**
	* Sets the task name of this task.
	*
	* @param taskName the task name of this task
	*/
	public void setTaskName(java.lang.String taskName) {
		_task.setTaskName(taskName);
	}

	/**
	* Returns the project ID of this task.
	*
	* @return the project ID of this task
	*/
	public long getProjectId() {
		return _task.getProjectId();
	}

	/**
	* Sets the project ID of this task.
	*
	* @param projectId the project ID of this task
	*/
	public void setProjectId(long projectId) {
		_task.setProjectId(projectId);
	}

	public boolean isNew() {
		return _task.isNew();
	}

	public void setNew(boolean n) {
		_task.setNew(n);
	}

	public boolean isCachedModel() {
		return _task.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_task.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _task.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _task.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_task.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _task.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_task.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new TaskWrapper((Task)_task.clone());
	}

	public int compareTo(com.liferay.timesheet.model.Task task) {
		return _task.compareTo(task);
	}

	@Override
	public int hashCode() {
		return _task.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.timesheet.model.Task> toCacheModel() {
		return _task.toCacheModel();
	}

	public com.liferay.timesheet.model.Task toEscapedModel() {
		return new TaskWrapper(_task.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _task.toString();
	}

	public java.lang.String toXmlString() {
		return _task.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_task.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public Task getWrappedTask() {
		return _task;
	}

	public Task getWrappedModel() {
		return _task;
	}

	public void resetOriginalValues() {
		_task.resetOriginalValues();
	}

	private Task _task;
}