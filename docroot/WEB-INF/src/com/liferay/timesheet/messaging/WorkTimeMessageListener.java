package com.liferay.timesheet.messaging;

import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.service.TaskSessionLocalServiceUtil;
import com.liferay.timesheet.util.DateTimeConverterUtil;
import com.liferay.timesheet.util.PortletPropsValues;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * @author Istvan Sajtos
 */
public class WorkTimeMessageListener extends BaseMessageListener {

	private static final Logger logger =
			LoggerFactory.getLogger(WorkTimeMessageListener.class);

	@Override
	protected void doReceive(Message message) throws Exception {
		List<User> users = UserLocalServiceUtil.getUsers(
			0, UserLocalServiceUtil.getUsersCount());

		for (User user : users) {
			TimeZone userTimeZone = user.getTimeZone();
			Calendar calendar = CalendarFactoryUtil.getCalendar(userTimeZone);

			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);

			Date today = calendar.getTime();

			TaskSession lastTaskSession =
				TaskSessionLocalServiceUtil.getLastTaskSessionsByD_U(
					today, user.getUserId());
			Date lastEndTime = lastTaskSession.getEndTime();

			Date baseTimeEndRestriction = 
				DateTimeConverterUtil.getDateFromMilitaryTime(
					PortletPropsValues.RESTRICTIONS_BASETIME_END);

			if (lastEndTime.before(baseTimeEndRestriction)) {
				logger.info(user.getUserId() + "closed his task at "
					+ lastEndTime);
				/* Placeholder for notification sending code. */
			}
		}
	}

}
