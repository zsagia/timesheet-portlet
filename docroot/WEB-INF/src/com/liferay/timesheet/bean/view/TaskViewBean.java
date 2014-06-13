package com.liferay.timesheet.bean.view;

import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.primefaces.ProjectTreeNode;
import com.liferay.timesheet.primefaces.util.TreeNodeUtil;
import com.liferay.timesheet.service.TaskSessionLocalServiceUtil;
import com.liferay.timesheet.util.TimeCalculatorUtil;
import com.liferay.timesheet.util.DateTimeUtil;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class TaskViewBean extends AbstractViewBean implements Serializable {

	public TaskViewBean() {
		init();
	}

	public void init() {
		try {
			setTodayWithoutTime(DateTimeUtil.getTodayWithoutTime());

			setCurrentTaskSession(
				TaskSessionLocalServiceUtil.getCurrentTaskSession(
					DateTimeUtil.getCurrentUserId()));

			setRoot(new ProjectTreeNode(null, null));

			TreeNodeUtil.generateProjectTreeNodes(true, getRoot());
		} catch (Exception e) {
			
		}
	}

	public String getDayTime() throws Exception {
		long userId = DateTimeUtil.getCurrentUserId();

		List<TaskSession> taskSessions =
			TaskSessionLocalServiceUtil.getTaskSessionsByU_D(
				userId, DateTimeUtil.getTodayWithoutTime());

		long time = TimeCalculatorUtil.summerizeTime(taskSessions);

		return TimeCalculatorUtil.getStringFromTime(time);
	}

	public String getMonthTime() throws Exception {
		long userId = DateTimeUtil.getCurrentUserId();

		long time = TimeCalculatorUtil.summerizeMonthTime(
			DateTimeUtil.getCompanyId(),
			DateTimeUtil.getTodayWithoutTime(), userId);

		return TimeCalculatorUtil.getStringFromTime(time);
	}

	public String getWeekTime() throws Exception {
		long userId = DateTimeUtil.getCurrentUserId();

		long time = TimeCalculatorUtil.summerizeWeekTime(
			DateTimeUtil.getCompanyId(),
			DateTimeUtil.getTodayWithoutTime(), userId);

		return TimeCalculatorUtil.getStringFromTime(time);
	}

	private static final long serialVersionUID = -1159224264202167114L;
}