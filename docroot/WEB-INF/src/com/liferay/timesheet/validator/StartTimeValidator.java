package com.liferay.timesheet.validator;

import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.service.TaskSessionLocalServiceUtil;
import com.liferay.timesheet.util.TimeCalculatorUtil;
import com.liferay.timesheet.util.TimesheetUtil;

import java.util.Date;
import java.util.List;

import javax.faces.validator.FacesValidator;

/**
* @author Istvan Sajtos
* @author Zsolt Szabo
*/

@FacesValidator("StartTimeValidator")
public class StartTimeValidator extends AbstractValidator {

	@Override
	public void doValidate(Date time) throws Exception {
		if (time != null) {
			long userId = TimesheetUtil.getCurrentUserId();
			Date startTime = time;
			Date now = new Date();

			TimeSheetValidatorUtil.validateFutureStartTime(startTime, now);
			TimeSheetValidatorUtil.validateLatestEndTime(startTime);

			Date today = TimesheetUtil.getTodayWithoutTime();

			TaskSession lastTaskSession =
				TaskSessionLocalServiceUtil.getLastTaskSessionsByD_U(
					today, userId);

			if (lastTaskSession != null) {
				List<TaskSession> taskSessionList =
					TaskSessionLocalServiceUtil.getTaskSessionsByD_U(
						today, userId);

				TimeSheetValidatorUtil.validateWorkDuration(
					TimeCalculatorUtil.summerizeDayTime(
						taskSessionList, startTime));

				TimeSheetValidatorUtil.validateStartTime(
					lastTaskSession, startTime);
			}
			else {
				TimeSheetValidatorUtil.validateWorkStart(startTime);
			}
		}
	}

}