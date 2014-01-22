package com.liferay.timesheet.messaging;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.service.TaskSessionLocalServiceUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author Istvan Sajtos
 */
public class ClosingMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		Calendar calendar = GregorianCalendar.getInstance();

		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 00);

		Date endTime = calendar.getTime();

		List<TaskSession> taskSessions =
			TaskSessionLocalServiceUtil.getTaskSessions(
				0, TaskSessionLocalServiceUtil.getTaskSessionsCount());

		for (TaskSession taskSession : taskSessions) {
			if (taskSession.getEndTime() == null) {
				taskSession.setEndTime(endTime);

				TaskSessionLocalServiceUtil.updateTaskSession(taskSession);
			}
		}
	}
}