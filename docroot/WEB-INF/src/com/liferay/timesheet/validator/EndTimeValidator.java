package com.liferay.timesheet.validator;

import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.service.TaskSessionLocalServiceUtil;
import com.liferay.timesheet.util.TimeCalculatorUtil;
import com.liferay.timesheet.util.TimesheetUtil;

import java.util.Date;
import java.util.List;

import javax.faces.validator.FacesValidator;


@FacesValidator("EndTimeValidator")
public class EndTimeValidator extends AbstractValidator {

	@Override
	public void doValidate(Date time) throws Exception {
		if (time != null) {
			long userId = TimesheetUtil.getCurrentUserId();

			Date endTime = time;
			Date now = new Date();
			Date today = TimesheetUtil.getTodayWithoutTime();

			TimeSheetValidatorUtil.validateFutureStartTime(endTime, now);
			TimeSheetValidatorUtil.validateLatestEndTime(endTime);

			TaskSession lastTaskSession =
				TaskSessionLocalServiceUtil.getLastTaskSessionsByD_U(
					today, userId);

			if (lastTaskSession != null) {
				TimeSheetValidatorUtil.validateEndTime(
					lastTaskSession, endTime);

				List<TaskSession> taskSessionList =
					TaskSessionLocalServiceUtil.getTaskSessionsByD_U(
						today, userId);

				TimeSheetValidatorUtil.validateWorkDuration(
					TimeCalculatorUtil.summerizeDayTime(
						taskSessionList, endTime));
			}
		}
	}

}