package com.liferay.timesheet.converter;

import com.liferay.timesheet.util.DateTimeUtil;

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

	public TimeSheetConverter() {
		setPattern("HHmm");
		setType("time");
	}

	@Override
	public Object getAsObject(
			FacesContext context, UIComponent component, String value)
		throws ConverterException{

		try {
			return DateTimeUtil.getDateFromMilitaryTime(value);
		} catch (Exception e) {
			return new ConverterException();
		}
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