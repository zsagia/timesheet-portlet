package com.liferay.timesheet.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("TaskSessionTimeConverter")
public class TaskSessionTimeConverter implements Converter {

	@Override
	public Object getAsObject(
		FacesContext context, UIComponent component, String time) {

		Date fullDate = null;
		String todayWithoutTime = null;
		String todayWithTime = null;
		SimpleDateFormat dateFormat = null;

		if (time == "") {
			return null;
		}

		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		todayWithoutTime = dateFormat.format(new Date());
		todayWithTime = todayWithoutTime.concat("-").concat(time);

		dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmm");
		try {
			fullDate = dateFormat.parse(todayWithTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return fullDate;
	}

	@Override
	public String getAsString(
		FacesContext context, UIComponent component, Object date) {

		Date fullDate = (Date)date;
		SimpleDateFormat dateFormat = new SimpleDateFormat("HHmm");
		String time = dateFormat.format(fullDate);
		
		return time;
	}

}