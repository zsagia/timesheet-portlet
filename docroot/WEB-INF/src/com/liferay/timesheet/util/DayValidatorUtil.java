package com.liferay.timesheet.util;

import java.util.Date;

public class DayValidatorUtil {

	public static boolean existLunch(Date date) {
		return true;
	}

	public static boolean isTaskSessionBeforeBaseTime(Date date) {
		return true;
	}

	public static boolean dayIsValid(Date date) {
		if (!existLunch(date)) {
			return false;
		}

		if (!isTaskSessionBeforeBaseTime(date)) {
			return false;
		}

		return true;
	}
}