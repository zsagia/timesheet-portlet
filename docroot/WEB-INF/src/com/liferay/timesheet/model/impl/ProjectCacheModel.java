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

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.timesheet.model.Project;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Project in entity cache.
 *
 * @author Istvan Sajtos, Zsolt Szabo
 * @see Project
 * @generated
 */
public class ProjectCacheModel implements CacheModel<Project>, Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", projectId=");
		sb.append(projectId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", creatorId=");
		sb.append(creatorId);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", enabled=");
		sb.append(enabled);
		sb.append(", projectName=");
		sb.append(projectName);
		sb.append(", parentProjectId=");
		sb.append(parentProjectId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Project toEntityModel() {
		ProjectImpl projectImpl = new ProjectImpl();

		if (uuid == null) {
			projectImpl.setUuid(StringPool.BLANK);
		}
		else {
			projectImpl.setUuid(uuid);
		}

		projectImpl.setProjectId(projectId);
		projectImpl.setCompanyId(companyId);

		if (createDate == Long.MIN_VALUE) {
			projectImpl.setCreateDate(null);
		}
		else {
			projectImpl.setCreateDate(new Date(createDate));
		}

		projectImpl.setCreatorId(creatorId);

		if (modifiedDate == Long.MIN_VALUE) {
			projectImpl.setModifiedDate(null);
		}
		else {
			projectImpl.setModifiedDate(new Date(modifiedDate));
		}

		projectImpl.setEnabled(enabled);

		if (projectName == null) {
			projectImpl.setProjectName(StringPool.BLANK);
		}
		else {
			projectImpl.setProjectName(projectName);
		}

		projectImpl.setParentProjectId(parentProjectId);

		projectImpl.resetOriginalValues();

		return projectImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();
		projectId = objectInput.readLong();
		companyId = objectInput.readLong();
		createDate = objectInput.readLong();
		creatorId = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		enabled = objectInput.readBoolean();
		projectName = objectInput.readUTF();
		parentProjectId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(projectId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(creatorId);
		objectOutput.writeLong(modifiedDate);
		objectOutput.writeBoolean(enabled);

		if (projectName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(projectName);
		}

		objectOutput.writeLong(parentProjectId);
	}

	public String uuid;
	public long projectId;
	public long companyId;
	public long createDate;
	public long creatorId;
	public long modifiedDate;
	public Boolean enabled;
	public String projectName;
	public Long parentProjectId;
}