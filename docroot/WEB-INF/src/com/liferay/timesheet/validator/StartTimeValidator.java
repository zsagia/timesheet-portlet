package com.liferay.timesheet.validator;

import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.service.TaskSessionLocalServiceUtil;
import com.liferay.timesheet.util.TimeCalculatorUtil;
import com.liferay.timesheet.util.DateTimeUtil;

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
			long userId = DateTimeUtil.getCurrentUserId();
			Date startTime = time;
			Date now = new Date();

			TimeSheetValidatorUtil.validateFutureStartTime(startTime, now);
			TimeSheetValidatorUtil.validateLatestEndTime(startTime);

			Date today = DateTimeUtil.getTodayWithoutTime();

			TaskSession lastTaskSession =
				TaskSessionLocalServiceUtil.getLastTaskSessionsByU_D(
					userId, today);

			if (lastTaskSession != null) {
				List<TaskSession> taskSessionList =
					TaskSessionLocalServiceUtil.getTaskSessionsByU_D(
						userId, today);

				TimeSheetValidatorUtil.validateWorkDuration(
					TimeCalculatorUtil.summerizeTime(
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