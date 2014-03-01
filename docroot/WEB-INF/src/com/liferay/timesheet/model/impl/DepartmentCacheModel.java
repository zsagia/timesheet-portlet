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

import com.liferay.timesheet.model.Department;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Department in entity cache.
 *
 * @author Istvan Sajtos, Zsolt Szabo
 * @see Department
 * @generated
 */
public class DepartmentCacheModel implements CacheModel<Department>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{departmentId=");
		sb.append(departmentId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", creatorId=");
		sb.append(creatorId);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", departmentName=");
		sb.append(departmentName);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Department toEntityModel() {
		DepartmentImpl departmentImpl = new DepartmentImpl();

		departmentImpl.setDepartmentId(departmentId);
		departmentImpl.setCompanyId(companyId);

		if (createDate == Long.MIN_VALUE) {
			departmentImpl.setCreateDate(null);
		}
		else {
			departmentImpl.setCreateDate(new Date(createDate));
		}

		departmentImpl.setCreatorId(creatorId);

		if (modifiedDate == Long.MIN_VALUE) {
			departmentImpl.setModifiedDate(null);
		}
		else {
			departmentImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (departmentName == null) {
			departmentImpl.setDepartmentName(StringPool.BLANK);
		}
		else {
			departmentImpl.setDepartmentName(departmentName);
		}

		departmentImpl.resetOriginalValues();

		return departmentImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		departmentId = objectInput.readLong();
		companyId = objectInput.readLong();
		createDate = objectInput.readLong();
		creatorId = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		departmentName = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(departmentId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(creatorId);
		objectOutput.writeLong(modifiedDate);

		if (departmentName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(departmentName);
		}
	}

	public long departmentId;
	public long companyId;
	public long createDate;
	public long creatorId;
	public long modifiedDate;
	public String departmentName;
}