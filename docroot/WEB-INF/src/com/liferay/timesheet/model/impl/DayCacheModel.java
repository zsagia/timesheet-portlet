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

import com.liferay.timesheet.model.Day;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Day in entity cache.
 *
 * @author Istvan Sajtos, Zsolt Szabo
 * @see Day
 * @generated
 */
public class DayCacheModel implements CacheModel<Day>, Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{dayId=");
		sb.append(dayId);
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
		sb.append(", date=");
		sb.append(date);
		sb.append(", type=");
		sb.append(type);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Day toEntityModel() {
		DayImpl dayImpl = new DayImpl();

		dayImpl.setDayId(dayId);
		dayImpl.setGroupId(groupId);
		dayImpl.setCompanyId(companyId);
		dayImpl.setUserId(userId);

		if (userName == null) {
			dayImpl.setUserName(StringPool.BLANK);
		}
		else {
			dayImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			dayImpl.setCreateDate(null);
		}
		else {
			dayImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			dayImpl.setModifiedDate(null);
		}
		else {
			dayImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (date == Long.MIN_VALUE) {
			dayImpl.setDate(null);
		}
		else {
			dayImpl.setDate(new Date(date));
		}

		dayImpl.setType(type);

		dayImpl.resetOriginalValues();

		return dayImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		dayId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		date = objectInput.readLong();
		type = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(dayId);
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
		objectOutput.writeLong(date);
		objectOutput.writeInt(type);
	}

	public long dayId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long date;
	public int type;
}