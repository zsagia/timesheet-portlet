package com.liferay.timesheet.validator;

import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.service.TaskSessionLocalServiceUtil;
import com.liferay.timesheet.util.DateTimeCalculatorUtil;
import com.liferay.timesheet.util.DateTimeUtil;
import com.liferay.timesheet.util.TimeSheetUtil;

import java.util.Date;
import java.util.List;

import javax.faces.validator.FacesValidator;


@FacesValidator("EndTimeValidator")
public class EndTimeValidator extends AbstractValidator {

	@Override
	public void doValidate(Date time) throws Exception {
		if (time != null) {
			long userId = TimeSheetUtil.getCurrentUserId();

			Date endTime = time;
			Date now = new Date();
			Date today = DateTimeUtil.getTodayWithoutTime();

			TimeSheetValidatorUtil.validateFutureStartTime(endTime, now);
			TimeSheetValidatorUtil.validateLatestEndTime(endTime);

			TaskSession lastTaskSession =
				TaskSessionLocalServiceUtil.getLastTaskSessionsByU_D(
					userId, today);

			if (lastTaskSession != null) {
				TimeSheetValidatorUtil.validateEndTime(
					lastTaskSession, endTime);

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