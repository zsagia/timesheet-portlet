package com.liferay.timesheet.converter;

import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.timesheet.util.TimesheetUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

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

		if (Validator.isNotNull(value)) {
			Date time = null;
			Date todayWithoutTime = null;

			try {
				time = (Date)super.getAsObject(context, component, value);

				todayWithoutTime = TimesheetUtil.getTodayWithoutTime();

				date = TimesheetUtil.addDateToDate(
					todayWithoutTime, time);
			} catch (ParseException e) {
				logger.error("date_conversion_is_failed");

				throw new ConverterException();
			}
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