package com.liferay.timesheet.bean;

import com.liferay.timesheet.NoSelectedTaskException;
import com.liferay.timesheet.service.TaskSessionLocalServiceUtil;
import com.liferay.timesheet.util.TimesheetUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class TaskSessionMultipleBean extends TaskSessionBaseBean {

	private static final long serialVersionUID = 6518160458023526615L;

	@ManagedProperty(value="#{param.selectedTaskId}")
	private long selectedTaskId;

	private Map<Long, Date> startTimes;

	public TaskSessionMultipleBean() {
		super();

		this.startTimes = new HashMap<Long, Date>();
	}

	@Override
	public String createTaskSession()
		throws Exception {

		if (selectedTaskId == 0) {
			throw new NoSelectedTaskException();
		}

		Date startDate = new Date();
		Date todayWithoutTime = null;

		Date startTime = startTimes.get(selectedTaskId);

		if (startTime != null) {
			todayWithoutTime = TimesheetUtil.getTodayWithoutTime();

			startDate =
				TimesheetUtil.addDateToDate(todayWithoutTime, startTime);
		}

		long userId = TimesheetUtil.getCurrentUserId();

		closeCurrentTaskSession(userId, startDate);

		TaskSessionLocalServiceUtil.addTaskSession(
			startDate, selectedTaskId, userId);

		return "success";
	}

	public long getSelectedTaskId() {
		return selectedTaskId;
	}

	public Map<Long, Date> getStartTimes() {
		return startTimes;
	}

	public void setSelectedTaskId(long selectedTaskId) {
		this.selectedTaskId = selectedTaskId;
	}

}