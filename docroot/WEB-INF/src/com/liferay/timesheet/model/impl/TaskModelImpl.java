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

package com.liferay.timesheet.model.impl;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import com.liferay.timesheet.model.Task;
import com.liferay.timesheet.model.TaskModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the Task service. Represents a row in the &quot;timesheet_Task&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.liferay.timesheet.model.TaskModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link TaskImpl}.
 * </p>
 *
 * @author Istvan Sajtos, Zsolt Szabo
 * @see TaskImpl
 * @see com.liferay.timesheet.model.Task
 * @see com.liferay.timesheet.model.TaskModel
 * @generated
 */
public class TaskModelImpl extends BaseModelImpl<Task> implements TaskModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a task model instance should use the {@link com.liferay.timesheet.model.Task} interface instead.
	 */
	public static final String TABLE_NAME = "timesheet_Task";
	public static final Object[][] TABLE_COLUMNS = {
			{ "taskId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "description", Types.VARCHAR },
			{ "projectId", Types.BIGINT },
			{ "taskName", Types.VARCHAR },
			{ "taskType", Types.INTEGER }
		};
	public static final String TABLE_SQL_CREATE = "create table timesheet_Task (taskId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,description VARCHAR(75) null,projectId LONG,taskName VARCHAR(75) null,taskType INTEGER)";
	public static final String TABLE_SQL_DROP = "drop table timesheet_Task";
	public static final String ORDER_BY_JPQL = " ORDER BY task.taskName ASC";
	public static final String ORDER_BY_SQL = " ORDER BY timesheet_Task.taskName ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.timesheet.model.Task"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.timesheet.model.Task"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.timesheet.model.Task"),
			true);
	public static long COMPANYID_COLUMN_BITMASK = 1L;
	public static long PROJECTID_COLUMN_BITMASK = 2L;
	public static long TASKNAME_COLUMN_BITMASK = 4L;
	public static long TASKTYPE_COLUMN_BITMASK = 8L;
	public static long USERID_COLUMN_BITMASK = 16L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.timesheet.model.Task"));

	public TaskModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _taskId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setTaskId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _taskId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
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

	@Override
	public long getTaskId() {
		return _taskId;
	}

	@Override
	public void setTaskId(long taskId) {
		_taskId = taskId;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_columnBitmask |= USERID_COLUMN_BITMASK;

		if (!_setOriginalUserId) {
			_setOriginalUserId = true;

			_originalUserId = _userId;
		}

		_userId = userId;
	}

	@Override
	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	@Override
	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	public long getOriginalUserId() {
		return _originalUserId;
	}

	@Override
	public String getUserName() {
		if (_userName == null) {
			return StringPool.BLANK;
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	@Override
	public String getDescription() {
		if (_description == null) {
			return StringPool.BLANK;
		}
		else {
			return _description;
		}
	}

	@Override
	public void setDescription(String description) {
		_description = description;
	}

	@Override
	public long getProjectId() {
		return _projectId;
	}

	@Override
	public void setProjectId(long projectId) {
		_columnBitmask |= PROJECTID_COLUMN_BITMASK;

		if (!_setOriginalProjectId) {
			_setOriginalProjectId = true;

			_originalProjectId = _projectId;
		}

		_projectId = projectId;
	}

	public long getOriginalProjectId() {
		return _originalProjectId;
	}

	@Override
	public String getTaskName() {
		if (_taskName == null) {
			return StringPool.BLANK;
		}
		else {
			return _taskName;
		}
	}

	@Override
	public void setTaskName(String taskName) {
		_columnBitmask = -1L;

		if (_originalTaskName == null) {
			_originalTaskName = _taskName;
		}

		_taskName = taskName;
	}

	public String getOriginalTaskName() {
		return GetterUtil.getString(_originalTaskName);
	}

	@Override
	public int getTaskType() {
		return _taskType;
	}

	@Override
	public void setTaskType(int taskType) {
		_columnBitmask |= TASKTYPE_COLUMN_BITMASK;

		if (!_setOriginalTaskType) {
			_setOriginalTaskType = true;

			_originalTaskType = _taskType;
		}

		_taskType = taskType;
	}

	public int getOriginalTaskType() {
		return _originalTaskType;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			Task.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Task toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (Task)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		TaskImpl taskImpl = new TaskImpl();

		taskImpl.setTaskId(getTaskId());
		taskImpl.setGroupId(getGroupId());
		taskImpl.setCompanyId(getCompanyId());
		taskImpl.setUserId(getUserId());
		taskImpl.setUserName(getUserName());
		taskImpl.setCreateDate(getCreateDate());
		taskImpl.setModifiedDate(getModifiedDate());
		taskImpl.setDescription(getDescription());
		taskImpl.setProjectId(getProjectId());
		taskImpl.setTaskName(getTaskName());
		taskImpl.setTaskType(getTaskType());

		taskImpl.resetOriginalValues();

		return taskImpl;
	}

	@Override
	public int compareTo(Task task) {
		int value = 0;

		value = getTaskName().compareTo(task.getTaskName());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Task)) {
			return false;
		}

		Task task = (Task)obj;

		long primaryKey = task.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public void resetOriginalValues() {
		TaskModelImpl taskModelImpl = this;

		taskModelImpl._originalCompanyId = taskModelImpl._companyId;

		taskModelImpl._setOriginalCompanyId = false;

		taskModelImpl._originalUserId = taskModelImpl._userId;

		taskModelImpl._setOriginalUserId = false;

		taskModelImpl._originalProjectId = taskModelImpl._projectId;

		taskModelImpl._setOriginalProjectId = false;

		taskModelImpl._originalTaskName = taskModelImpl._taskName;

		taskModelImpl._originalTaskType = taskModelImpl._taskType;

		taskModelImpl._setOriginalTaskType = false;

		taskModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Task> toCacheModel() {
		TaskCacheModel taskCacheModel = new TaskCacheModel();

		taskCacheModel.taskId = getTaskId();

		taskCacheModel.groupId = getGroupId();

		taskCacheModel.companyId = getCompanyId();

		taskCacheModel.userId = getUserId();

		taskCacheModel.userName = getUserName();

		String userName = taskCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			taskCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			taskCacheModel.createDate = createDate.getTime();
		}
		else {
			taskCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			taskCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			taskCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		taskCacheModel.description = getDescription();

		String description = taskCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			taskCacheModel.description = null;
		}

		taskCacheModel.projectId = getProjectId();

		taskCacheModel.taskName = getTaskName();

		String taskName = taskCacheModel.taskName;

		if ((taskName != null) && (taskName.length() == 0)) {
			taskCacheModel.taskName = null;
		}

		taskCacheModel.taskType = getTaskType();

		return taskCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{taskId=");
		sb.append(getTaskId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", projectId=");
		sb.append(getProjectId());
		sb.append(", taskName=");
		sb.append(getTaskName());
		sb.append(", taskType=");
		sb.append(getTaskType());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(37);

		sb.append("<model><model-name>");
		sb.append("com.liferay.timesheet.model.Task");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>taskId</column-name><column-value><![CDATA[");
		sb.append(getTaskId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>projectId</column-name><column-value><![CDATA[");
		sb.append(getProjectId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>taskName</column-name><column-value><![CDATA[");
		sb.append(getTaskName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>taskType</column-name><column-value><![CDATA[");
		sb.append(getTaskType());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = Task.class.getClassLoader();
	private static Class<?>[] _escapedModelInterfaces = new Class[] { Task.class };
	private long _taskId;
	private long _groupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userUuid;
	private long _originalUserId;
	private boolean _setOriginalUserId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _description;
	private long _projectId;
	private long _originalProjectId;
	private boolean _setOriginalProjectId;
	private String _taskName;
	private String _originalTaskName;
	private int _taskType;
	private int _originalTaskType;
	private boolean _setOriginalTaskType;
	private long _columnBitmask;
	private Task _escapedModel;
}