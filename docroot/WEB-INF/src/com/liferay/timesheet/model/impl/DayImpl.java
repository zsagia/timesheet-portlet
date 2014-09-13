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

import com.liferay.timesheet.model.Day;
import com.liferay.timesheet.model.DayConstants;

/**
 * The extended model implementation for the Day service. Represents a row in the &quot;timesheet_Day&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.timesheet.model.Day} interface.
 * </p>
 *
 * @author Istvan Sajtos, Zsolt Szabo
 */
public class DayImpl extends DayBaseImpl {
	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a day model instance should use the {@link com.liferay.timesheet.model.Day} interface instead.
	 */
	public DayImpl() {
	}

	public String getDayType() {
		String typeString = null;

		switch (getType()) {
			case DayConstants.TYPE_NATIONAL_HOLIDAY :
				typeString = DayConstants.TYPE_NATIONAL_HOLIDAY_LABEL;
				break;
			case DayConstants.TYPE_EXCEPTIONAL_HOLIDAY :
				typeString = DayConstants.TYPE_EXCEPTIONAL_HOLIDAY_LABEL;
				break;
			case DayConstants.TYPE_EXCEPTIONAL_WORKDAY :
				typeString = DayConstants.TYPE_EXCEPTIONAL_WORKDAY_LABEL;
		}

		return typeString;
	}

	@Override
	public int compareTo(Day day) {
		return getDate().before(day.getDate()) ? -1 : 1;
	}
}