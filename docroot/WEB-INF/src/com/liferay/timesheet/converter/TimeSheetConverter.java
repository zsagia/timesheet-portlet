package com.liferay.timesheet.converter;

import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.timesheet.util.DateTimeConverterUtil;
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

	public TimeSheetConverter() {
		setPattern("HHmm");
		setType("time");
	}

	@Override
	public Object getAsObject(
			FacesContext context, UIComponent component, String value)
		throws ConverterException{

		return DateTimeConverterUtil.getDateFromMilitaryTime(value);
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