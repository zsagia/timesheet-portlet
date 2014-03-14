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
 * This class is a wrapper for {@link TaskSession}.
 * </p>
 *
 * @author Istvan Sajtos, Zsolt Szabo
 * @see TaskSession
 * @generated
 */
public class TaskSessionWrapper implements TaskSession,
	ModelWrapper<TaskSession> {
	public TaskSessionWrapper(TaskSession taskSession) {
		_taskSession = taskSession;
	}

	@Override
	public Class<?> getModelClass() {
		return TaskSession.class;
	}

	@Override
	public String getModelClassName() {
		return TaskSession.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("taskSessionId", getTaskSessionId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("description", getDescription());
		attributes.put("endTime", getEndTime());
		attributes.put("startTime", getStartTime());
		attributes.put("taskId", getTaskId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long taskSessionId = (Long)attributes.get("taskSessionId");

		if (taskSessionId != null) {
			setTaskSessionId(taskSessionId);
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

		Date endTime = (Date)attributes.get("endTime");

		if (endTime != null) {
			setEndTime(endTime);
		}

		Date startTime = (Date)attributes.get("startTime");

		if (startTime != null) {
			setStartTime(startTime);
		}

		Long taskId = (Long)attributes.get("taskId");

		if (taskId != null) {
			setTaskId(taskId);
		}
	}

	/**
	* Returns the primary key of this task session.
	*
	* @return the primary key of this task session
	*/
	@Override
	public long getPrimaryKey() {
		return _taskSession.getPrimaryKey();
	}

	/**
	* Sets the primary key of this task session.
	*
	* @param primaryKey the primary key of this task session
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_taskSession.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the task session ID of this task session.
	*
	* @return the task session ID of this task session
	*/
	@Override
	public long getTaskSessionId() {
		return _taskSession.getTaskSessionId();
	}

	/**
	* Sets the task session ID of this task session.
	*
	* @param taskSessionId the task session ID of this task session
	*/
	@Override
	public void setTaskSessionId(long taskSessionId) {
		_taskSession.setTaskSessionId(taskSessionId);
	}

	/**
	* Returns the group ID of this task session.
	*
	* @return the group ID of this task session
	*/
	@Override
	public long getGroupId() {
		return _taskSession.getGroupId();
	}

	/**
	* Sets the group ID of this task session.
	*
	* @param groupId the group ID of this task session
	*/
	@Override
	public void setGroupId(long groupId) {
		_taskSession.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this task session.
	*
	* @return the company ID of this task session
	*/
	@Override
	public long getCompanyId() {
		return _taskSession.getCompanyId();
	}

	/**
	* Sets the company ID of this task session.
	*
	* @param companyId the company ID of this task session
	*/
	@Override
	public void setCompanyId(long companyId) {
		_taskSession.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this task session.
	*
	* @return the user ID of this task session
	*/
	@Override
	public long getUserId() {
		return _taskSession.getUserId();
	}

	/**
	* Sets the user ID of this task session.
	*
	* @param userId the user ID of this task session
	*/
	@Override
	public void setUserId(long userId) {
		_taskSession.setUserId(userId);
	}

	/**
	* Returns the user uuid of this task session.
	*
	* @return the user uuid of this task session
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _taskSession.getUserUuid();
	}

	/**
	* Sets the user uuid of this task session.
	*
	* @param userUuid the user uuid of this task session
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_taskSession.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this task session.
	*
	* @return the user name of this task session
	*/
	@Override
	public java.lang.String getUserName() {
		return _taskSession.getUserName();
	}

	/**
	* Sets the user name of this task session.
	*
	* @param userName the user name of this task session
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_taskSession.setUserName(userName);
	}

	/**
	* Returns the create date of this task session.
	*
	* @return the create date of this task session
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _taskSession.getCreateDate();
	}

	/**
	* Sets the create date of this task session.
	*
	* @param createDate the create date of this task session
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_taskSession.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this task session.
	*
	* @return the modified date of this task session
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _taskSession.getModifiedDate();
	}

	/**
	* Sets the modified date of this task session.
	*
	* @param modifiedDate the modified date of this task session
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_taskSession.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the description of this task session.
	*
	* @return the description of this task session
	*/
	@Override
	public java.lang.String getDescription() {
		return _taskSession.getDescription();
	}

	/**
	* Sets the description of this task session.
	*
	* @param description the description of this task session
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_taskSession.setDescription(description);
	}

	/**
	* Returns the end time of this task session.
	*
	* @return the end time of this task session
	*/
	@Override
	public java.util.Date getEndTime() {
		return _taskSession.getEndTime();
	}

	/**
	* Sets the end time of this task session.
	*
	* @param endTime the end time of this task session
	*/
	@Override
	public void setEndTime(java.util.Date endTime) {
		_taskSession.setEndTime(endTime);
	}

	/**
	* Returns the start time of this task session.
	*
	* @return the start time of this task session
	*/
	@Override
	public java.util.Date getStartTime() {
		return _taskSession.getStartTime();
	}

	/**
	* Sets the start time of this task session.
	*
	* @param startTime the start time of this task session
	*/
	@Override
	public void setStartTime(java.util.Date startTime) {
		_taskSession.setStartTime(startTime);
	}

	/**
	* Returns the task ID of this task session.
	*
	* @return the task ID of this task session
	*/
	@Override
	public long getTaskId() {
		return _taskSession.getTaskId();
	}

	/**
	* Sets the task ID of this task session.
	*
	* @param taskId the task ID of this task session
	*/
	@Override
	public void setTaskId(long taskId) {
		_taskSession.setTaskId(taskId);
	}

	@Override
	public boolean isNew() {
		return _taskSession.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_taskSession.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _taskSession.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_taskSession.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _taskSession.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _taskSession.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_taskSession.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _taskSession.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_taskSession.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_taskSession.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_taskSession.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new TaskSessionWrapper((TaskSession)_taskSession.clone());
	}

	@Override
	public int compareTo(com.liferay.timesheet.model.TaskSession taskSession) {
		return _taskSession.compareTo(taskSession);
	}

	@Override
	public int hashCode() {
		return _taskSession.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.timesheet.model.TaskSession> toCacheModel() {
		return _taskSession.toCacheModel();
	}

	@Override
	public com.liferay.timesheet.model.TaskSession toEscapedModel() {
		return new TaskSessionWrapper(_taskSession.toEscapedModel());
	}

	@Override
	public com.liferay.timesheet.model.TaskSession toUnescapedModel() {
		return new TaskSessionWrapper(_taskSession.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _taskSession.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _taskSession.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_taskSession.persist();
	}

	@Override
	public com.liferay.timesheet.model.Task getTask()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _taskSession.getTask();
	}

	@Override
	public long getDuration() throws java.lang.Exception {
		return _taskSession.getDuration();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TaskSessionWrapper)) {
			return false;
		}

		TaskSessionWrapper taskSessionWrapper = (TaskSessionWrapper)obj;

		if (Validator.equals(_taskSession, taskSessionWrapper._taskSession)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public TaskSession getWrappedTaskSession() {
		return _taskSession;
	}

	@Override
	public TaskSession getWrappedModel() {
		return _taskSession;
	}

	@Override
	public void resetOriginalValues() {
		_taskSession.resetOriginalValues();
	}

	private TaskSession _taskSession;
}