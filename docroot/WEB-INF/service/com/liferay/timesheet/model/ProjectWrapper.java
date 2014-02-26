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
 * This class is a wrapper for {@link Project}.
 * </p>
 *
 * @author    Istvan Sajtos, Zsolt Szabo
 * @see       Project
 * @generated
 */
public class ProjectWrapper implements Project, ModelWrapper<Project> {
	public ProjectWrapper(Project project) {
		_project = project;
	}

	public Class<?> getModelClass() {
		return Project.class;
	}

	public String getModelClassName() {
		return Project.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("projectId", getProjectId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("creatorId", getCreatorId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("enabled", getEnabled());
		attributes.put("projectName", getProjectName());
		attributes.put("parentProjectId", getParentProjectId());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long projectId = (Long)attributes.get("projectId");

		if (projectId != null) {
			setProjectId(projectId);
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

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Boolean enabled = (Boolean)attributes.get("enabled");

		if (enabled != null) {
			setEnabled(enabled);
		}

		String projectName = (String)attributes.get("projectName");

		if (projectName != null) {
			setProjectName(projectName);
		}

		Long parentProjectId = (Long)attributes.get("parentProjectId");

		if (parentProjectId != null) {
			setParentProjectId(parentProjectId);
		}
	}

	/**
	* Returns the primary key of this project.
	*
	* @return the primary key of this project
	*/
	public long getPrimaryKey() {
		return _project.getPrimaryKey();
	}

	/**
	* Sets the primary key of this project.
	*
	* @param primaryKey the primary key of this project
	*/
	public void setPrimaryKey(long primaryKey) {
		_project.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this project.
	*
	* @return the uuid of this project
	*/
	public java.lang.String getUuid() {
		return _project.getUuid();
	}

	/**
	* Sets the uuid of this project.
	*
	* @param uuid the uuid of this project
	*/
	public void setUuid(java.lang.String uuid) {
		_project.setUuid(uuid);
	}

	/**
	* Returns the project ID of this project.
	*
	* @return the project ID of this project
	*/
	public long getProjectId() {
		return _project.getProjectId();
	}

	/**
	* Sets the project ID of this project.
	*
	* @param projectId the project ID of this project
	*/
	public void setProjectId(long projectId) {
		_project.setProjectId(projectId);
	}

	/**
	* Returns the company ID of this project.
	*
	* @return the company ID of this project
	*/
	public long getCompanyId() {
		return _project.getCompanyId();
	}

	/**
	* Sets the company ID of this project.
	*
	* @param companyId the company ID of this project
	*/
	public void setCompanyId(long companyId) {
		_project.setCompanyId(companyId);
	}

	/**
	* Returns the create date of this project.
	*
	* @return the create date of this project
	*/
	public java.util.Date getCreateDate() {
		return _project.getCreateDate();
	}

	/**
	* Sets the create date of this project.
	*
	* @param createDate the create date of this project
	*/
	public void setCreateDate(java.util.Date createDate) {
		_project.setCreateDate(createDate);
	}

	/**
	* Returns the creator ID of this project.
	*
	* @return the creator ID of this project
	*/
	public long getCreatorId() {
		return _project.getCreatorId();
	}

	/**
	* Sets the creator ID of this project.
	*
	* @param creatorId the creator ID of this project
	*/
	public void setCreatorId(long creatorId) {
		_project.setCreatorId(creatorId);
	}

	/**
	* Returns the modified date of this project.
	*
	* @return the modified date of this project
	*/
	public java.util.Date getModifiedDate() {
		return _project.getModifiedDate();
	}

	/**
	* Sets the modified date of this project.
	*
	* @param modifiedDate the modified date of this project
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_project.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the enabled of this project.
	*
	* @return the enabled of this project
	*/
	public java.lang.Boolean getEnabled() {
		return _project.getEnabled();
	}

	/**
	* Sets the enabled of this project.
	*
	* @param enabled the enabled of this project
	*/
	public void setEnabled(java.lang.Boolean enabled) {
		_project.setEnabled(enabled);
	}

	/**
	* Returns the project name of this project.
	*
	* @return the project name of this project
	*/
	public java.lang.String getProjectName() {
		return _project.getProjectName();
	}

	/**
	* Sets the project name of this project.
	*
	* @param projectName the project name of this project
	*/
	public void setProjectName(java.lang.String projectName) {
		_project.setProjectName(projectName);
	}

	/**
	* Returns the parent project ID of this project.
	*
	* @return the parent project ID of this project
	*/
	public java.lang.Long getParentProjectId() {
		return _project.getParentProjectId();
	}

	/**
	* Sets the parent project ID of this project.
	*
	* @param parentProjectId the parent project ID of this project
	*/
	public void setParentProjectId(java.lang.Long parentProjectId) {
		_project.setParentProjectId(parentProjectId);
	}

	public boolean isNew() {
		return _project.isNew();
	}

	public void setNew(boolean n) {
		_project.setNew(n);
	}

	public boolean isCachedModel() {
		return _project.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_project.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _project.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _project.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_project.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _project.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_project.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new ProjectWrapper((Project)_project.clone());
	}

	public int compareTo(com.liferay.timesheet.model.Project project) {
		return _project.compareTo(project);
	}

	@Override
	public int hashCode() {
		return _project.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.timesheet.model.Project> toCacheModel() {
		return _project.toCacheModel();
	}

	public com.liferay.timesheet.model.Project toEscapedModel() {
		return new ProjectWrapper(_project.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _project.toString();
	}

	public java.lang.String toXmlString() {
		return _project.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_project.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public Project getWrappedProject() {
		return _project;
	}

	public Project getWrappedModel() {
		return _project;
	}

	public void resetOriginalValues() {
		_project.resetOriginalValues();
	}

	private Project _project;
}