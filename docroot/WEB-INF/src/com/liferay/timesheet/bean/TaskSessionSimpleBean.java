package com.liferay.timesheet.bean;

import com.liferay.timesheet.NoSelectedTaskException;
import com.liferay.timesheet.service.TaskSessionLocalServiceUtil;
import com.liferay.timesheet.util.TimesheetUtil;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class TaskSessionSimpleBean extends TaskSessionBaseBean {

	private static final long serialVersionUID = -1208454421047462200L;

	private Date startTime;

	public TaskSessionSimpleBean() {
		super();
	}

	@Override
	public String createTaskSession() throws Exception {
		if (getSelectedTaskId() == 0) {
			throw new NoSelectedTaskException();
		}

		Date startDate = new Date();

		Date todayWithoutTime = null;

		if (getStartTime() != null) {
			todayWithoutTime = TimesheetUtil.getTodayWithoutTime();

			startDate =
				TimesheetUtil.addDateToDate(todayWithoutTime, getStartTime());
		}

		long userId = TimesheetUtil.getCurrentUserId();

		closeCurrentTaskSession(userId, startDate);

		TaskSessionLocalServiceUtil.addTaskSession(
			startDate, getSelectedTaskId(), userId);

		return "success";
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

}