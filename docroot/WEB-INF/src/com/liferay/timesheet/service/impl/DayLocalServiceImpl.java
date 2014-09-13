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

package com.liferay.timesheet.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.timesheet.model.Day;
import com.liferay.timesheet.service.base.DayLocalServiceBaseImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The implementation of the day local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.timesheet.service.DayLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Istvan Sajtos, Zsolt Szabo
 * @see com.liferay.timesheet.service.base.DayLocalServiceBaseImpl
 * @see com.liferay.timesheet.service.DayLocalServiceUtil
 */
public class DayLocalServiceImpl extends DayLocalServiceBaseImpl {

	public Day addDay(
			long userId, Date date, int type, ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		long groupId = serviceContext.getScopeGroupId();

		long dayId = counterLocalService.increment();

		Day day = dayPersistence.create(dayId);

		Date now = new Date();

		day.setCompanyId(user.getCompanyId());
		day.setCreateDate(now);
		day.setGroupId(groupId);
		day.setModifiedDate(now);
		day.setDate(date);
		day.setType(type);

		dayPersistence.update(day);

		return day;
	}

	public List<Day> getDays(long companyId, int type) throws SystemException {
		return dayPersistence.findByC_T(companyId, type);
	}

	public List<Day> getDays(long companyId, int[] types)
		throws SystemException {

		return getDays(companyId, types, false);
	}

	public List<Day> getDays(long companyId, int[] types, boolean sort)
		throws SystemException {

		List<Day> days = new ArrayList<Day>();

		for (int i = 0; i < types.length; i++) {
			days.addAll(getDays(companyId, types[i]));
		}

		if (sort) {
			Collections.sort(days);
		}

		return days;
	}

	public Map<String, Day> getDaysMap(long companyId, int type)
		throws SystemException {

		List<Day> days = getDays(companyId, type);

		Map<String, Day> daysMap = new HashMap<String, Day>();

		for (Day day : days) {
			daysMap.put(String.valueOf(day.getDate().getTime()), day);
		}

		return daysMap;
	}

	public Map<String, Day> getDaysMap(long companyId, int[] types)
		throws SystemException {

		Map<String, Day> daysMap = new HashMap<String, Day>();

		for (int i = 0; i < types.length; i++) {
			daysMap.putAll(getDaysMap(companyId, types[i]));
		}

		return daysMap;
	}

}