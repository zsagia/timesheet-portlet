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

package com.liferay.timesheet.service.persistence;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

import com.liferay.timesheet.model.Day;
import com.liferay.timesheet.service.DayLocalServiceUtil;

/**
 * @author Istvan Sajtos, Zsolt Szabo
 * @generated
 */
public abstract class DayActionableDynamicQuery
	extends BaseActionableDynamicQuery {
	public DayActionableDynamicQuery() throws SystemException {
		setBaseLocalService(DayLocalServiceUtil.getService());
		setClass(Day.class);

		setClassLoader(com.liferay.timesheet.service.ClpSerializer.class.getClassLoader());

		setPrimaryKeyPropertyName("dayId");
	}
}