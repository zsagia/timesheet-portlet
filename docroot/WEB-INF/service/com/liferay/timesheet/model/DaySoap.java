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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Istvan Sajtos, Zsolt Szabo
 * @generated
 */
public class DaySoap implements Serializable {
	public static DaySoap toSoapModel(Day model) {
		DaySoap soapModel = new DaySoap();

		soapModel.setDayId(model.getDayId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setDate(model.getDate());
		soapModel.setType(model.getType());

		return soapModel;
	}

	public static DaySoap[] toSoapModels(Day[] models) {
		DaySoap[] soapModels = new DaySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DaySoap[][] toSoapModels(Day[][] models) {
		DaySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DaySoap[models.length][models[0].length];
		}
		else {
			soapModels = new DaySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DaySoap[] toSoapModels(List<Day> models) {
		List<DaySoap> soapModels = new ArrayList<DaySoap>(models.size());

		for (Day model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DaySoap[soapModels.size()]);
	}

	public DaySoap() {
	}

	public long getPrimaryKey() {
		return _dayId;
	}

	public void setPrimaryKey(long pk) {
		setDayId(pk);
	}

	public long getDayId() {
		return _dayId;
	}

	public void setDayId(long dayId) {
		_dayId = dayId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public Date getDate() {
		return _date;
	}

	public void setDate(Date date) {
		_date = date;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	private long _dayId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private Date _date;
	private int _type;
}