package com.liferay.timesheet.bean.view;

import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.primefaces.ProjectTreeNode;
import com.liferay.timesheet.primefaces.util.TreeNodeUtil;
import com.liferay.timesheet.service.TaskSessionLocalServiceUtil;
import com.liferay.timesheet.util.DateTimeCalculatorUtil;
import com.liferay.timesheet.util.DateTimeUtil;
import com.liferay.timesheet.util.TimeSheetUtil;

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
					TimeSheetUtil.getCurrentUserId()));

			setRoot(new ProjectTreeNode(null, null));

			TreeNodeUtil.generateProjectTreeNodes(true, getRoot());
		} catch (Exception e) {
			
		}
	}

	public String getDayTime() throws Exception {
		long userId = TimeSheetUtil.getCurrentUserId();

		List<TaskSession> taskSessions =
			TaskSessionLocalServiceUtil.getTaskSessionsByU_D(
				userId, DateTimeUtil.getTodayWithoutTime());

		long time = DateTimeCalculatorUtil.summerizeTime(taskSessions);

		return DateTimeCalculatorUtil.getStringFromTime(time);
	}

	public String getMonthTime() throws Exception {
		long userId = TimeSheetUtil.getCurrentUserId();

		long time = DateTimeCalculatorUtil.summerizeMonthTime(
			TimeSheetUtil.getCompanyId(),
			DateTimeUtil.getTodayWithoutTime(), userId);

		return DateTimeCalculatorUtil.getStringFromTime(time);
	}

	public String getWeekTime() throws Exception {
		long userId = TimeSheetUtil.getCurrentUserId();

		long time = DateTimeCalculatorUtil.summerizeWeekTime(
			TimeSheetUtil.getCompanyId(),
			DateTimeUtil.getTodayWithoutTime(), userId);

		return DateTimeCalculatorUtil.getStringFromTime(time);
	}

	private static final long serialVersionUID = -1159224264202167114L;
}