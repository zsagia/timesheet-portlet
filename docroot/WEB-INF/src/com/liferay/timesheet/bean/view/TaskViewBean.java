package com.liferay.timesheet.bean.view;

import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.primefaces.ProjectTreeNode;
import com.liferay.timesheet.primefaces.util.TreeNodeUtil;
import com.liferay.timesheet.service.TaskSessionLocalServiceUtil;
import com.liferay.timesheet.util.TimeCalculatorUtil;
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
			setTodayWithoutTime(TimeSheetUtil.getTodayWithoutTime());

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
				userId, TimeSheetUtil.getTodayWithoutTime());

		long time = TimeCalculatorUtil.summerizeTime(taskSessions);

		return TimeCalculatorUtil.getStringFromTime(time);
	}

	public String getMonthTime() throws Exception {
		long userId = TimeSheetUtil.getCurrentUserId();

		long time = TimeCalculatorUtil.summerizeMonthTime(
			TimeSheetUtil.getCompanyId(),
			TimeSheetUtil.getTodayWithoutTime(), userId);

		return TimeCalculatorUtil.getStringFromTime(time);
	}

	public String getWeekTime() throws Exception {
		long userId = TimeSheetUtil.getCurrentUserId();

		long time = TimeCalculatorUtil.summerizeWeekTime(
			TimeSheetUtil.getCompanyId(),
			TimeSheetUtil.getTodayWithoutTime(), userId);

		return TimeCalculatorUtil.getStringFromTime(time);
	}

	private static final long serialVersionUID = -1159224264202167114L;
}