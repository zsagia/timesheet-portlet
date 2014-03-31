package com.liferay.timesheet.util;

import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.timesheet.converter.TimeSheetConverter;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.faces.convert.ConverterException;

public class DateTimeConverterUtil {

	private static final Logger logger =
			LoggerFactory.getLogger(DateTimeConverterUtil.class);

	public static Date getDateFromMilitaryTime(String value) {
		Date date = null;

		try {
			User user = TimesheetUtil.getCurrentUser();
			TimeZone userTimeZone = user.getTimeZone();

			Calendar calendar = CalendarFactoryUtil.getCalendar(userTimeZone);

			if (Validator.isNotNull(value)) {
				int hour = Integer.valueOf(value.substring(0, 2));
				int minute = Integer.valueOf(
					value.substring(2, value.length()));

				calendar.set(Calendar.HOUR_OF_DAY, hour);
				calendar.set(Calendar.MINUTE, minute);
				calendar.set(Calendar.SECOND, 0);
				calendar.set(Calendar.MILLISECOND, 0);
			}

			date = calendar.getTime();

		} catch (Exception e) {
			logger.error("date_conversion_is_failed", e);

			throw new ConverterException();
		}

		return date;
	}

}
