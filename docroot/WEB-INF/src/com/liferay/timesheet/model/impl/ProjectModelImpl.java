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
import com.liferay.portal.kernel.lar.StagedModelType;
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

import com.liferay.timesheet.model.Project;
import com.liferay.timesheet.model.ProjectModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the Project service. Represents a row in the &quot;timesheet_Project&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.liferay.timesheet.model.ProjectModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ProjectImpl}.
 * </p>
 *
 * @author Istvan Sajtos, Zsolt Szabo
 * @see ProjectImpl
 * @see com.liferay.timesheet.model.Project
 * @see com.liferay.timesheet.model.ProjectModel
 * @generated
 */
public class ProjectModelImpl extends BaseModelImpl<Project>
	implements ProjectModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a project model instance should use the {@link com.liferay.timesheet.model.Project} interface instead.
	 */
	public static final String TABLE_NAME = "timesheet_Project";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "projectId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "departmentId", Types.BIGINT },
			{ "description", Types.VARCHAR },
			{ "enabled", Types.BOOLEAN },
			{ "parentProjectId", Types.BIGINT },
			{ "projectName", Types.VARCHAR }
		};
	public static final String TABLE_SQL_CREATE = "create table timesheet_Project (uuid_ VARCHAR(75) null,projectId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,departmentId LONG,description VARCHAR(75) null,enabled BOOLEAN,parentProjectId LONG,projectName VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table timesheet_Project";
	public static final String ORDER_BY_JPQL = " ORDER BY project.projectName ASC";
	public static final String ORDER_BY_SQL = " ORDER BY timesheet_Project.projectName ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.timesheet.model.Project"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.timesheet.model.Project"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.timesheet.model.Project"),
			true);
	public static long COMPANYID_COLUMN_BITMASK = 1L;
	public static long DEPARTMENTID_COLUMN_BITMASK = 2L;
	public static long GROUPID_COLUMN_BITMASK = 4L;
	public static long PARENTPROJECTID_COLUMN_BITMASK = 8L;
	public static long UUID_COLUMN_BITMASK = 16L;
	public static long PROJECTNAME_COLUMN_BITMASK = 32L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.timesheet.model.Project"));

	public ProjectModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _projectId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setProjectId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _projectId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Project.class;
	}

	@Override
	public String getModelClassName() {
		return Project.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("projectId", getProjectId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("departmentId", getDepartmentId());
		attributes.put("description", getDescription());
		attributes.put("enabled", getEnabled());
		attributes.put("parentProjectId", getParentProjectId());
		attributes.put("projectName", getProjectName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long projectId = (Long)attributes.get("projectId");

		if (projectId != null) {
			setProjectId(projectId);
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

		Long departmentId = (Long)attributes.get("departmentId");

		if (departmentId != null) {
			setDepartmentId(departmentId);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Boolean enabled = (Boolean)attributes.get("enabled");

		if (enabled != null) {
			setEnabled(enabled);
		}

		Long parentProjectId = (Long)attributes.get("parentProjectId");

		if (parentProjectId != null) {
			setParentProjectId(parentProjectId);
		}

		String projectName = (String)attributes.get("projectName");

		if (projectName != null) {
			setProjectName(projectName);
		}
	}

	@Override
	public String getUuid() {
		if (_uuid == null) {
			return StringPool.BLANK;
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@Override
	public long getProjectId() {
		return _projectId;
	}

	@Override
	public void setProjectId(long projectId) {
		_projectId = projectId;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
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
	public Long getDepartmentId() {
		return _departmentId;
	}

	@Override
	public void setDepartmentId(Long departmentId) {
		_columnBitmask |= DEPARTMENTID_COLUMN_BITMASK;

		if (!_setOriginalDepartmentId) {
			_setOriginalDepartmentId = true;

			_originalDepartmentId = _departmentId;
		}

		_departmentId = departmentId;
	}

	public Long getOriginalDepartmentId() {
		return _originalDepartmentId;
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
	public Boolean getEnabled() {
		return _enabled;
	}

	@Override
	public void setEnabled(Boolean enabled) {
		_enabled = enabled;
	}

	@Override
	public Long getParentProjectId() {
		return _parentProjectId;
	}

	@Override
	public void setParentProjectId(Long parentProjectId) {
		_columnBitmask |= PARENTPROJECTID_COLUMN_BITMASK;

		if (!_setOriginalParentProjectId) {
			_setOriginalParentProjectId = true;

			_originalParentProjectId = _parentProjectId;
		}

		_parentProjectId = parentProjectId;
	}

	public Long getOriginalParentProjectId() {
		return _originalParentProjectId;
	}

	@Override
	public String getProjectName() {
		if (_projectName == null) {
			return StringPool.BLANK;
		}
		else {
			return _projectName;
		}
	}

	@Override
	public void setProjectName(String projectName) {
		_columnBitmask = -1L;

		_projectName = projectName;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				Project.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			Project.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Project toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (Project)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		ProjectImpl projectImpl = new ProjectImpl();

		projectImpl.setUuid(getUuid());
		projectImpl.setProjectId(getProjectId());
		projectImpl.setGroupId(getGroupId());
		projectImpl.setCompanyId(getCompanyId());
		projectImpl.setUserId(getUserId());
		projectImpl.setUserName(getUserName());
		projectImpl.setCreateDate(getCreateDate());
		projectImpl.setModifiedDate(getModifiedDate());
		projectImpl.setDepartmentId(getDepartmentId());
		projectImpl.setDescription(getDescription());
		projectImpl.setEnabled(getEnabled());
		projectImpl.setParentProjectId(getParentProjectId());
		projectImpl.setProjectName(getProjectName());

		projectImpl.resetOriginalValues();

		return projectImpl;
	}

	@Override
	public int compareTo(Project project) {
		int value = 0;

		value = getProjectName().compareTo(project.getProjectName());

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

		if (!(obj instanceof Project)) {
			return false;
		}

		Project project = (Project)obj;

		long primaryKey = project.getPrimaryKey();

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
		ProjectModelImpl projectModelImpl = this;

		projectModelImpl._originalUuid = projectModelImpl._uuid;

		projectModelImpl._originalGroupId = projectModelImpl._groupId;

		projectModelImpl._setOriginalGroupId = false;

		projectModelImpl._originalCompanyId = projectModelImpl._companyId;

		projectModelImpl._setOriginalCompanyId = false;

		projectModelImpl._originalDepartmentId = projectModelImpl._departmentId;

		projectModelImpl._setOriginalDepartmentId = false;

		projectModelImpl._originalParentProjectId = projectModelImpl._parentProjectId;

		projectModelImpl._setOriginalParentProjectId = false;

		projectModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Project> toCacheModel() {
		ProjectCacheModel projectCacheModel = new ProjectCacheModel();

		projectCacheModel.uuid = getUuid();

		String uuid = projectCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			projectCacheModel.uuid = null;
		}

		projectCacheModel.projectId = getProjectId();

		projectCacheModel.groupId = getGroupId();

		projectCacheModel.companyId = getCompanyId();

		projectCacheModel.userId = getUserId();

		projectCacheModel.userName = getUserName();

		String userName = projectCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			projectCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			projectCacheModel.createDate = createDate.getTime();
		}
		else {
			projectCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			projectCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			projectCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		projectCacheModel.departmentId = getDepartmentId();

		projectCacheModel.description = getDescription();

		String description = projectCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			projectCacheModel.description = null;
		}

		projectCacheModel.enabled = getEnabled();

		projectCacheModel.parentProjectId = getParentProjectId();

		projectCacheModel.projectName = getProjectName();

		String projectName = projectCacheModel.projectName;

		if ((projectName != null) && (projectName.length() == 0)) {
			projectCacheModel.projectName = null;
		}

		return projectCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", projectId=");
		sb.append(getProjectId());
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
		sb.append(", departmentId=");
		sb.append(getDepartmentId());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", enabled=");
		sb.append(getEnabled());
		sb.append(", parentProjectId=");
		sb.append(getParentProjectId());
		sb.append(", projectName=");
		sb.append(getProjectName());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(43);

		sb.append("<model><model-name>");
		sb.append("com.liferay.timesheet.model.Project");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>projectId</column-name><column-value><![CDATA[");
		sb.append(getProjectId());
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
			"<column><column-name>departmentId</column-name><column-value><![CDATA[");
		sb.append(getDepartmentId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>enabled</column-name><column-value><![CDATA[");
		sb.append(getEnabled());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>parentProjectId</column-name><column-value><![CDATA[");
		sb.append(getParentProjectId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>projectName</column-name><column-value><![CDATA[");
		sb.append(getProjectName());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = Project.class.getClassLoader();
	private static Class<?>[] _escapedModelInterfaces = new Class[] {
			Project.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _projectId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private Long _departmentId;
	private Long _originalDepartmentId;
	private boolean _setOriginalDepartmentId;
	private String _description;
	private Boolean _enabled;
	private Long _parentProjectId;
	private Long _originalParentProjectId;
	private boolean _setOriginalParentProjectId;
	private String _projectName;
	private long _columnBitmask;
	private Project _escapedModel;
}