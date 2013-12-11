package com.liferay.timesheet.validator;

import com.liferay.timesheet.CurrentTaskSessionIsAlreadyEndedException;
import com.liferay.timesheet.EndTimeException;
import com.liferay.timesheet.NoCurrentTaskSessionException;
import com.liferay.timesheet.StartTimeException;
import com.liferay.timesheet.model.TaskSession;

import java.text.ParseException;
import java.util.Date;

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
	}

	public static void validateEndTime(
			TaskSession taskSession, Date endTime)
		throws CurrentTaskSessionIsAlreadyEndedException, EndTimeException,
			NoCurrentTaskSessionException {

		if (taskSession == null) {
			throw new NoCurrentTaskSessionException();
		}

		Date endDate = taskSession.getEndTime();

		if (endDate != null) {
			throw new CurrentTaskSessionIsAlreadyEndedException();
		}

		Date startDate = taskSession.getStartTime();

		if (endTime.before(startDate)) {
			throw new EndTimeException();
		}
	}

}