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
 * This class is a wrapper for {@link Day}.
 * </p>
 *
 * @author Istvan Sajtos, Zsolt Szabo
 * @see Day
 * @generated
 */
public class DayWrapper implements Day, ModelWrapper<Day> {
	public DayWrapper(Day day) {
		_day = day;
	}

	@Override
	public Class<?> getModelClass() {
		return Day.class;
	}

	@Override
	public String getModelClassName() {
		return Day.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("dayId", getDayId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("date", getDate());
		attributes.put("type", getType());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long dayId = (Long)attributes.get("dayId");

		if (dayId != null) {
			setDayId(dayId);
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

		Date date = (Date)attributes.get("date");

		if (date != null) {
			setDate(date);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}
	}

	/**
	* Returns the primary key of this day.
	*
	* @return the primary key of this day
	*/
	@Override
	public long getPrimaryKey() {
		return _day.getPrimaryKey();
	}

	/**
	* Sets the primary key of this day.
	*
	* @param primaryKey the primary key of this day
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_day.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the day ID of this day.
	*
	* @return the day ID of this day
	*/
	@Override
	public long getDayId() {
		return _day.getDayId();
	}

	/**
	* Sets the day ID of this day.
	*
	* @param dayId the day ID of this day
	*/
	@Override
	public void setDayId(long dayId) {
		_day.setDayId(dayId);
	}

	/**
	* Returns the group ID of this day.
	*
	* @return the group ID of this day
	*/
	@Override
	public long getGroupId() {
		return _day.getGroupId();
	}

	/**
	* Sets the group ID of this day.
	*
	* @param groupId the group ID of this day
	*/
	@Override
	public void setGroupId(long groupId) {
		_day.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this day.
	*
	* @return the company ID of this day
	*/
	@Override
	public long getCompanyId() {
		return _day.getCompanyId();
	}

	/**
	* Sets the company ID of this day.
	*
	* @param companyId the company ID of this day
	*/
	@Override
	public void setCompanyId(long companyId) {
		_day.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this day.
	*
	* @return the user ID of this day
	*/
	@Override
	public long getUserId() {
		return _day.getUserId();
	}

	/**
	* Sets the user ID of this day.
	*
	* @param userId the user ID of this day
	*/
	@Override
	public void setUserId(long userId) {
		_day.setUserId(userId);
	}

	/**
	* Returns the user uuid of this day.
	*
	* @return the user uuid of this day
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _day.getUserUuid();
	}

	/**
	* Sets the user uuid of this day.
	*
	* @param userUuid the user uuid of this day
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_day.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this day.
	*
	* @return the user name of this day
	*/
	@Override
	public java.lang.String getUserName() {
		return _day.getUserName();
	}

	/**
	* Sets the user name of this day.
	*
	* @param userName the user name of this day
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_day.setUserName(userName);
	}

	/**
	* Returns the create date of this day.
	*
	* @return the create date of this day
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _day.getCreateDate();
	}

	/**
	* Sets the create date of this day.
	*
	* @param createDate the create date of this day
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_day.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this day.
	*
	* @return the modified date of this day
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _day.getModifiedDate();
	}

	/**
	* Sets the modified date of this day.
	*
	* @param modifiedDate the modified date of this day
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_day.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the date of this day.
	*
	* @return the date of this day
	*/
	@Override
	public java.util.Date getDate() {
		return _day.getDate();
	}

	/**
	* Sets the date of this day.
	*
	* @param date the date of this day
	*/
	@Override
	public void setDate(java.util.Date date) {
		_day.setDate(date);
	}

	/**
	* Returns the type of this day.
	*
	* @return the type of this day
	*/
	@Override
	public int getType() {
		return _day.getType();
	}

	/**
	* Sets the type of this day.
	*
	* @param type the type of this day
	*/
	@Override
	public void setType(int type) {
		_day.setType(type);
	}

	@Override
	public boolean isNew() {
		return _day.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_day.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _day.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_day.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _day.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _day.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_day.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _day.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_day.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_day.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_day.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new DayWrapper((Day)_day.clone());
	}

	@Override
	public int compareTo(com.liferay.timesheet.model.Day day) {
		return _day.compareTo(day);
	}

	@Override
	public int hashCode() {
		return _day.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.timesheet.model.Day> toCacheModel() {
		return _day.toCacheModel();
	}

	@Override
	public com.liferay.timesheet.model.Day toEscapedModel() {
		return new DayWrapper(_day.toEscapedModel());
	}

	@Override
	public com.liferay.timesheet.model.Day toUnescapedModel() {
		return new DayWrapper(_day.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _day.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _day.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_day.persist();
	}

	@Override
	public java.lang.String getDayType() {
		return _day.getDayType();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DayWrapper)) {
			return false;
		}

		DayWrapper dayWrapper = (DayWrapper)obj;

		if (Validator.equals(_day, dayWrapper._day)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public Day getWrappedDay() {
		return _day;
	}

	@Override
	public Day getWrappedModel() {
		return _day;
	}

	@Override
	public void resetOriginalValues() {
		_day.resetOriginalValues();
	}

	private Day _day;
}