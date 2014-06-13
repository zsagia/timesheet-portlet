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


@FacesValidator("EndTimeValidator")
public class EndTimeValidator extends AbstractValidator {

	@Override
	public void doValidate(
			FacesContext context, UIComponent component, Object value)
		throws Exception {

		if (value != null) {
			long userId = TimeSheetUtil.getCurrentUserId();

			Date endTime = (Date)value;
			Date now = new Date();
			Date today = DateTimeUtil.getTodayWithoutTime();

			TimeSheetValidatorUtil.validateFutureTime(endTime, now);

			Date latestEndTimeRestriction =
				DateTimeUtil.getDateFromMilitaryTime(
					PortletPropsValues.RESTRICTIONS_ENDTIME_LATEST);

			TimeSheetValidatorUtil.validateLatestEndTime(
				endTime, latestEndTimeRestriction);

			TaskSession lastTaskSession =
				TaskSessionLocalServiceUtil.getLastTaskSessionsByU_D(
					userId, today);

			if (lastTaskSession != null) {
				TimeSheetValidatorUtil.validateEndTime(
					lastTaskSession.getStartTime(),
					lastTaskSession.getEndTime(), endTime);

				List<TaskSession> taskSessionList =
					TaskSessionLocalServiceUtil.getTaskSessionsByU_D(
						userId, today);

				TimeSheetValidatorUtil.validateWorkDuration(
					DateTimeCalculatorUtil.summerizeTime(
						taskSessionList, endTime));
			}
		}
	}

}