package com.liferay.timesheet.converter;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.timesheet.util.DateTimeUtil;
import com.liferay.timesheet.util.TimeSheetUtil;

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
			if (Validator.isNull(value)) {
				return null;
			}

			Object object = component.getAttributes().get("currentDate");

			currentDate = (object != null) ? (Date)object :
				DateTimeUtil.getTodayWithoutTime();

			return DateTimeUtil.getDateFromMilitaryTime(currentDate, value);
		} catch (Exception e) {
			throw new ConverterException(e);
		}
	}

	@Override
	public String getAsString(
		FacesContext context, UIComponent component, Object date) {

		String time = null;

		try {
			User currentUser = TimeSheetUtil.getCurrentUser();

			SimpleDateFormat dateFormat = new SimpleDateFormat(getPattern());

			dateFormat.setTimeZone(currentUser.getTimeZone());

			time = dateFormat.format(date);
		} catch (Exception e) {
			throw new ConverterException(e);
		}

		return time;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	private Date currentDate = null;

}