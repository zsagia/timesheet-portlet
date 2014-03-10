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

import com.liferay.timesheet.model.TaskSession;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing TaskSession in entity cache.
 *
 * @author Istvan Sajtos, Zsolt Szabo
 * @see TaskSession
 * @generated
 */
public class TaskSessionCacheModel implements CacheModel<TaskSession>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{taskSessionId=");
		sb.append(taskSessionId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", description=");
		sb.append(description);
		sb.append(", endTime=");
		sb.append(endTime);
		sb.append(", startTime=");
		sb.append(startTime);
		sb.append(", taskId=");
		sb.append(taskId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TaskSession toEntityModel() {
		TaskSessionImpl taskSessionImpl = new TaskSessionImpl();

		taskSessionImpl.setTaskSessionId(taskSessionId);
		taskSessionImpl.setGroupId(groupId);
		taskSessionImpl.setCompanyId(companyId);
		taskSessionImpl.setUserId(userId);

		if (userName == null) {
			taskSessionImpl.setUserName(StringPool.BLANK);
		}
		else {
			taskSessionImpl.setUserName(userName);
		}

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

		if (description == null) {
			taskSessionImpl.setDescription(StringPool.BLANK);
		}
		else {
			taskSessionImpl.setDescription(description);
		}

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

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		taskSessionId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		description = objectInput.readUTF();
		endTime = objectInput.readLong();
		startTime = objectInput.readLong();
		taskId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(taskSessionId);
		objectOutput.writeLong(groupId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeLong(endTime);
		objectOutput.writeLong(startTime);
		objectOutput.writeLong(taskId);
	}

	public long taskSessionId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String description;
	public long endTime;
	public long startTime;
	public long taskId;
}