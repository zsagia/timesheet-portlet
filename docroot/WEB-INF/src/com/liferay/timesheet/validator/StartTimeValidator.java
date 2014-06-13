package com.liferay.timesheet.validator;

import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.service.TaskSessionLocalServiceUtil;
import com.liferay.timesheet.util.DateTimeCalculatorUtil;
import com.liferay.timesheet.util.DateTimeUtil;
import com.liferay.timesheet.util.PortletPropsValues;
import com.liferay.timesheet.util.TimeSheetUtil;

import java.util.Date;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;

/**
* @author Istvan Sajtos
* @author Zsolt Szabo
*/

@FacesValidator("StartTimeValidator")
public class StartTimeValidator extends AbstractValidator {

	@Override
	public void doValidate(
			FacesContext context, UIComponent component, Object value)
		throws Exception {

		if (value != null) {
			long userId = TimeSheetUtil.getCurrentUserId();
			Date startTime = (Date)value;
			Date now = new Date();

			TimeSheetValidatorUtil.validateFutureTime(startTime, now);

			Date latestEndTimeRestriction =
				DateTimeUtil.getDateFromMilitaryTime(
					PortletPropsValues.RESTRICTIONS_ENDTIME_LATEST);

			TimeSheetValidatorUtil.validateLatestEndTime(
				startTime, latestEndTimeRestriction);

			Date today = DateTimeUtil.getTodayWithoutTime();

			TaskSession lastTaskSession =
				TaskSessionLocalServiceUtil.getLastTaskSessionsByU_D(
					userId, today);

			if (lastTaskSession != null) {
				List<TaskSession> taskSessionList =
					TaskSessionLocalServiceUtil.getTaskSessionsByU_D(
						userId, today);

				TimeSheetValidatorUtil.validateStartTime(
					lastTaskSession.getStartTime(),
					lastTaskSession.getEndTime(), startTime);

				TimeSheetValidatorUtil.validateWorkDuration(
					DateTimeCalculatorUtil.summerizeTime(
						taskSessionList, startTime));
			}
			else {
				Date earliestStartRestriction =
					DateTimeUtil.getDateFromMilitaryTime(
						PortletPropsValues.RESTRICTIONS_STARTTIME_EARLIEST);

				TimeSheetValidatorUtil.validateWorkStart(
					startTime, earliestStartRestriction);
			}
		}
	}

}