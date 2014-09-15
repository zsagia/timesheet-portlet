package com.liferay.timesheet.bean.validation;

import com.liferay.timesheet.util.DateTimeUtil;
import com.liferay.timesheet.util.DayValidatorUtil;
import com.liferay.timesheet.util.TimeSheetConstants;

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.application.Application;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
@ManagedBean(name ="validationBean")
@SessionScoped
public class ValidationBean implements Serializable {

	public void dayIsValid(ComponentSystemEvent event) throws Exception {
		FacesContext facesContext = FacesContext.getCurrentInstance();

		Date previousWorkingDay = DateTimeUtil.getPreviousWorkingDay(
			new Date());

		dayValidationMap = DayValidatorUtil.dayIsValid(previousWorkingDay);

		if (dayValidationMap.containsKey(
				TimeSheetConstants.VALIDATION_TYPE_ERROR)) {

			validPreviousWorkingDay = false;

			Application application = facesContext.getApplication();

			ConfigurableNavigationHandler navigationHandler =
			(ConfigurableNavigationHandler)application.getNavigationHandler();

			navigationHandler.performNavigation(
				TimeSheetConstants.VIEW_DAY_REMAKE + "?faces-redirect=true");
		}
		else {
			validPreviousWorkingDay = true;
		}
	}

	public Map<String, List<String>> getDayValidationMap() {
		return dayValidationMap;
	}

	public boolean isValidPreviousWorkingDay() {
		return validPreviousWorkingDay;
	}

	public void setDayValidationMap(
		Map<String, List<String>> dayValidationMap) {

		this.dayValidationMap = dayValidationMap;
	}

	public void setValidPreviousWorkingDay(boolean validPreviousWorkingDay) {
		this.validPreviousWorkingDay = validPreviousWorkingDay;
	}

	private static final long serialVersionUID = -1227272710033966470L;

	private Map<String, List<String>> dayValidationMap;
	private boolean validPreviousWorkingDay = true;

}