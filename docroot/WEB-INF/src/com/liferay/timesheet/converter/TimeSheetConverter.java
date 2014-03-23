package com.liferay.timesheet.converter;

import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.timesheet.util.TimesheetUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

/**
* @author Istvan Sajtos
* @author Zsolt Szabo
*/

@FacesConverter("TimeSheetConverter")
public class TimeSheetConverter extends DateTimeConverter {

	private static final Logger logger =
		LoggerFactory.getLogger(TimeSheetConverter.class);

	public TimeSheetConverter() {
		setPattern("HHmm");
		setType("time");
	}

	@Override
	public Object getAsObject(
			FacesContext context, UIComponent component, String value)
		throws ConverterException{

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

	@Override
	public String getAsString(
		FacesContext context, UIComponent component, Object date) {

		Date fullDate = (Date)date;

		SimpleDateFormat dateFormat = new SimpleDateFormat(getPattern());

		String time = dateFormat.format(fullDate);

		return time;
	}

}