package com.liferay.timesheet.validator;

import com.liferay.timesheet.CurrentTaskSessionIsAlreadyEndedException;
import com.liferay.timesheet.EndTimeException;
import com.liferay.timesheet.NoCurrentTaskSessionException;
import com.liferay.timesheet.StartTimeException;
import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.util.DateTimeConverterUtil;
import com.liferay.timesheet.util.PortletPropsKeys;
import com.liferay.timesheet.util.PortletPropsValues;

import java.text.ParseException;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.validator.ValidatorException;

/**
* @author Zsolt Szabo
* @author Istvan Sajtos
*/
public class DateTimeValidatorUtil {

	public static void validateStartTime(
			TaskSession taskSession, Date startTime)
		throws ParseException, StartTimeException {

		Date previousStartTime = taskSession.getStartTime();
		Date endTime = taskSession.getEndTime();

		if (!previousStartTime.before(startTime) ||
				((endTime != null) && endTime.after(startTime))) {

			throw new StartTimeException();
		}

		Date latestEndRestriction = 
			DateTimeConverterUtil.getDateFromMilitaryTime(
				PortletPropsValues.RESTRICTIONS_ENDTIME_LATEST);

		if (startTime.after(latestEndRestriction)) {
			throw new ValidatorException(new FacesMessage(
				"The given time exceeds the restriction defined for "
				+ "finishing work: " 
				+ PortletPropsValues.RESTRICTIONS_ENDTIME_LATEST));

			/* Placeholder for notification sending code. */
		}
	}

	public static void validateEndTime(
			TaskSession taskSession, Date endDate)
		throws CurrentTaskSessionIsAlreadyEndedException, EndTimeException,
			NoCurrentTaskSessionException {

		if (taskSession == null) {
			throw new NoCurrentTaskSessionException();
		}

		Date endTime = taskSession.getEndTime();

		if (endTime != null) {
			throw new CurrentTaskSessionIsAlreadyEndedException();
		}

		Date startTime = taskSession.getStartTime();

		if (endDate.before(startTime)) {
			throw new EndTimeException();
		}
	}

	public static void validateWorkStart(Date workStart) {
		Date earliestStartRestriction = DateTimeConverterUtil.
			getDateFromMilitaryTime(PortletPropsValues.
				RESTRICTIONS_STARTTIME_EARLIEST);
		Date latestStartRestriction = DateTimeConverterUtil.
			getDateFromMilitaryTime(PortletPropsValues.
				RESTRICTIONS_BASETIME_START);

		if (workStart.before(earliestStartRestriction) ||
			workStart.after(latestStartRestriction)) {

			throw new ValidatorException(new FacesMessage(
				"The given time doesn't fit the time frame defined for work "
				+ "start: " 
				+ PortletPropsValues.RESTRICTIONS_STARTTIME_EARLIEST + "-"
				+ PortletPropsValues.RESTRICTIONS_BASETIME_START));

			/* Placeholder for notification sending code. */
		}
	}

}