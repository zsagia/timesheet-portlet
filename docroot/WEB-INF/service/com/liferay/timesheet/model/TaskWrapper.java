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

package com.liferay.timesheet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Task}.
 * </p>
 *
 * @author Istvan Sajtos, Zsolt Szabo
 * @see Task
 * @generated
 */
public class TaskWrapper implements Task, ModelWrapper<Task> {
	public TaskWrapper(Task task) {
		_task = task;
	}

	@Override
	public Class<?> getModelClass() {
		return Task.class;
	}

	@Override
	public String getModelClassName() {
		return Task.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("taskId", getTaskId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("description", getDescription());
		attributes.put("projectId", getProjectId());
		attributes.put("taskName", getTaskName());
		attributes.put("taskType", getTaskType());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long taskId = (Long)attributes.get("taskId");

		if (taskId != null) {
			setTaskId(taskId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Long projectId = (Long)attributes.get("projectId");

		if (projectId != null) {
			setProjectId(projectId);
		}

		String taskName = (String)attributes.get("taskName");

		if (taskName != null) {
			setTaskName(taskName);
		}

		Integer taskType = (Integer)attributes.get("taskType");

		if (taskType != null) {
			setTaskType(taskType);
		}
	}

	/**
	* Returns the primary key of this task.
	*
	* @return the primary key of this task
	*/
	@Override
	public long getPrimaryKey() {
		return _task.getPrimaryKey();
	}

	/**
	* Sets the primary key of this task.
	*
	* @param primaryKey the primary key of this task
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_task.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the task ID of this task.
	*
	* @return the task ID of this task
	*/
	@Override
	public long getTaskId() {
		return _task.getTaskId();
	}

	/**
	* Sets the task ID of this task.
	*
	* @param taskId the task ID of this task
	*/
	@Override
	public void setTaskId(long taskId) {
		_task.setTaskId(taskId);
	}

	/**
	* Returns the group ID of this task.
	*
	* @return the group ID of this task
	*/
	@Override
	public long getGroupId() {
		return _task.getGroupId();
	}

	/**
	* Sets the group ID of this task.
	*
	* @param groupId the group ID of this task
	*/
	@Override
	public void setGroupId(long groupId) {
		_task.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this task.
	*
	* @return the company ID of this task
	*/
	@Override
	public long getCompanyId() {
		return _task.getCompanyId();
	}

	/**
	* Sets the company ID of this task.
	*
	* @param companyId the company ID of this task
	*/
	@Override
	public void setCompanyId(long companyId) {
		_task.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this task.
	*
	* @return the user ID of this task
	*/
	@Override
	public long getUserId() {
		return _task.getUserId();
	}

	/**
	* Sets the user ID of this task.
	*
	* @param userId the user ID of this task
	*/
	@Override
	public void setUserId(long userId) {
		_task.setUserId(userId);
	}

	/**
	* Returns the user uuid of this task.
	*
	* @return the user uuid of this task
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _task.getUserUuid();
	}

	/**
	* Sets the user uuid of this task.
	*
	* @param userUuid the user uuid of this task
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_task.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this task.
	*
	* @return the user name of this task
	*/
	@Override
	public java.lang.String getUserName() {
		return _task.getUserName();
	}

	/**
	* Sets the user name of this task.
	*
	* @param userName the user name of this task
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_task.setUserName(userName);
	}

	/**
	* Returns the create date of this task.
	*
	* @return the create date of this task
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _task.getCreateDate();
	}

	/**
	* Sets the create date of this task.
	*
	* @param createDate the create date of this task
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_task.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this task.
	*
	* @return the modified date of this task
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _task.getModifiedDate();
	}

	/**
	* Sets the modified date of this task.
	*
	* @param modifiedDate the modified date of this task
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_task.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the description of this task.
	*
	* @return the description of this task
	*/
	@Override
	public java.lang.String getDescription() {
		return _task.getDescription();
	}

	/**
	* Sets the description of this task.
	*
	* @param description the description of this task
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_task.setDescription(description);
	}

	/**
	* Returns the project ID of this task.
	*
	* @return the project ID of this task
	*/
	@Override
	public long getProjectId() {
		return _task.getProjectId();
	}

	/**
	* Sets the project ID of this task.
	*
	* @param projectId the project ID of this task
	*/
	@Override
	public void setProjectId(long projectId) {
		_task.setProjectId(projectId);
	}

	/**
	* Returns the task name of this task.
	*
	* @return the task name of this task
	*/
	@Override
	public java.lang.String getTaskName() {
		return _task.getTaskName();
	}

	/**
	* Sets the task name of this task.
	*
	* @param taskName the task name of this task
	*/
	@Override
	public void setTaskName(java.lang.String taskName) {
		_task.setTaskName(taskName);
	}

	/**
	* Returns the task type of this task.
	*
	* @return the task type of this task
	*/
	@Override
	public int getTaskType() {
		return _task.getTaskType();
	}

	/**
	* Sets the task type of this task.
	*
	* @param taskType the task type of this task
	*/
	@Override
	public void setTaskType(int taskType) {
		_task.setTaskType(taskType);
	}

	@Override
	public boolean isNew() {
		return _task.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_task.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _task.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_task.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _task.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _task.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_task.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _task.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_task.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_task.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_task.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new TaskWrapper((Task)_task.clone());
	}

	@Override
	public int compareTo(com.liferay.timesheet.model.Task task) {
		return _task.compareTo(task);
	}

	@Override
	public int hashCode() {
		return _task.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.timesheet.model.Task> toCacheModel() {
		return _task.toCacheModel();
	}

	@Override
	public com.liferay.timesheet.model.Task toEscapedModel() {
		return new TaskWrapper(_task.toEscapedModel());
	}

	@Override
	public com.liferay.timesheet.model.Task toUnescapedModel() {
		return new TaskWrapper(_task.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _task.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _task.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_task.persist();
	}

	@Override
	public long getDuration(long userId, java.util.Date date)
		throws com.liferay.portal.kernel.exception.SystemException,
			java.lang.Exception {
		return _task.getDuration(userId, date);
	}

	@Override
	public long getDuration(long userId, java.util.Date date1,
		java.util.Date date2)
		throws com.liferay.portal.kernel.exception.SystemException,
			java.lang.Exception {
		return _task.getDuration(userId, date1, date2);
	}

	@Override
	public java.lang.String getFormattedDuration(long userId,
		java.util.Date date)
		throws com.liferay.portal.kernel.exception.SystemException,
			java.lang.Exception {
		return _task.getFormattedDuration(userId, date);
	}

	@Override
	public java.lang.String getFormattedDuration(long userId,
		java.util.Date date1, java.util.Date date2)
		throws com.liferay.portal.kernel.exception.SystemException,
			java.lang.Exception {
		return _task.getFormattedDuration(userId, date1, date2);
	}

	@Override
	public com.liferay.timesheet.model.Project getProject() {
		return _task.getProject();
	}

	@Override
	public java.lang.String getProjectName() {
		return _task.getProjectName();
	}

	@Override
	public java.util.List<com.liferay.timesheet.model.TaskSession> getTaskSessionList(
		long userId, java.util.Date date)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _task.getTaskSessionList(userId, date);
	}

	@Override
	public java.util.List<com.liferay.timesheet.model.TaskSession> getTaskSessionList(
		long userId, java.util.Date date1, java.util.Date date2)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _task.getTaskSessionList(userId, date1, date2);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TaskWrapper)) {
			return false;
		}

		TaskWrapper taskWrapper = (TaskWrapper)obj;

		if (Validator.equals(_task, taskWrapper._task)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public Task getWrappedTask() {
		return _task;
	}

	@Override
	public Task getWrappedModel() {
		return _task;
	}

	@Override
	public void resetOriginalValues() {
		_task.resetOriginalValues();
	}

	private Task _task;
}