package com.liferay.timesheet.bean.view;

import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupServiceUtil;
import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.service.TaskSessionLocalServiceUtil;
import com.liferay.timesheet.util.ProjectTreeNode;
import com.liferay.timesheet.util.TimeCalculatorUtil;
import com.liferay.timesheet.util.TimesheetUtil;

import java.io.Serializable;
import java.util.Collections;
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
			setTodayWithoutTime(TimesheetUtil.getTodayWithoutTime());

			setCurrentTaskSession(
				TaskSessionLocalServiceUtil.getCurrentTaskSession(
					TimesheetUtil.getCurrentUserId()));

			setOwnerGroupList(GroupServiceUtil.getGroups(
				TimesheetUtil.getCompanyId(), 0, false));

			if (getOwnerGroupList().size() > 0) {
				setSelectedOwnerGroup(getOwnerGroupList().get(0));

				setRoot(new ProjectTreeNode(null, null));

				generateTreeNodes(
					true, getSelectedOwnerGroup().getGroupId(),
					getRoot());
			}
		} catch (Exception e) {
			List<Group> ownerGroupList = Collections.emptyList();

			setOwnerGroupList(ownerGroupList);
		}
	}

	public String getDayTime() throws Exception {
		long userId = TimesheetUtil.getCurrentUserId();

		List<TaskSession> taskSessions =
			TaskSessionLocalServiceUtil.getTaskSessionsByD_U(
				TimesheetUtil.getTodayWithoutTime(), userId);

		long time = TimeCalculatorUtil.summerizeDayTime(taskSessions);

		return TimeCalculatorUtil.getStringFromTime(time);
	}

	public String getMonthTime() throws Exception {
		long userId = TimesheetUtil.getCurrentUserId();

		long time = TimeCalculatorUtil.summerizeMonthTime(
			TimesheetUtil.getCompanyId(),
			TimesheetUtil.getTodayWithoutTime(), userId);

		return TimeCalculatorUtil.getStringFromTime(time);
	}

	public String getWeekTime() throws Exception {
		long userId = TimesheetUtil.getCurrentUserId();

		long time = TimeCalculatorUtil.summerizeWeekTime(
			TimesheetUtil.getCompanyId(),
			TimesheetUtil.getTodayWithoutTime(), userId);

		return TimeCalculatorUtil.getStringFromTime(time);
	}

	public void onOwnerGroupSelect() throws Exception {
		setRoot(new ProjectTreeNode(null, null));

		generateTreeNodes(
			true, getSelectedOwnerGroup().getGroupId(), getRoot());

		setSelectedProjectNode(null);
	}

	private static final long serialVersionUID = -1159224264202167114L;
}