/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.timesheet.model.impl;

import com.liferay.faces.util.lang.StringPool;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.timesheet.model.Project;
import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.service.ProjectLocalServiceUtil;
import com.liferay.timesheet.service.TaskSessionLocalServiceUtil;
import com.liferay.timesheet.util.TimeCalculatorUtil;
import com.liferay.timesheet.util.TimeSheetUtil;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * The extended model implementation for the Task service. Represents a row in the &quot;timesheet_Task&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.timesheet.model.Task} interface.
 * </p>
 *
 * @author Istvan Sajtos
 * @author Zsolt Szabo
 */
public class TaskImpl extends TaskBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a task model instance should use the {@link com.liferay.timesheet.model.Task} interface instead.
	 */
	public TaskImpl() {
	}

	public long getDuration(long userId, Date date)
		throws SystemException, Exception {

		return TimeCalculatorUtil.summerizeTime(
			getTaskSessionList(
				userId, date, TimeSheetUtil.getIncrementedDay(date)));
	}

	public long getDuration(long userId, Date date1, Date date2)
		throws SystemException, Exception {

		return TimeCalculatorUtil.summerizeTime(
			getTaskSessionList(userId, date1, date2));
	}

	public String getFormattedDuration(long userId, Date date)
		throws SystemException, Exception {

		return TimeCalculatorUtil.getStringFromTime(getDuration(userId, date));
	}

	public String getFormattedDuration(long userId, Date date1, Date date2)
		throws SystemException, Exception {

		return TimeCalculatorUtil.getStringFromTime(
			getDuration(userId, date1, date2));
	}

	public Project getProject() {
		Project project = null;

		try {
			project = ProjectLocalServiceUtil.getProject(getProjectId());
		} catch (Exception e) {}

		return project;
	}

	public String getProjectName() {
		Project project = getProject();

		if (project == null) {
			return StringPool.BLANK;
		}

		return project.getProjectName();
	}

	public List<TaskSession> getTaskSessionList(
		long userId, Date date) throws SystemException {

		List<TaskSession> taskSessionList =
			TaskSessionLocalServiceUtil.getTaskSessionsByU_T_D(
				userId, getTaskId(), date);

		if (taskSessionList == null) {
			taskSessionList = Collections.emptyList();
		}

		return taskSessionList;
	}

	public List<TaskSession> getTaskSessionList(
		long userId, Date date1, Date date2) throws SystemException {

		List<TaskSession> taskSessionList =
			TaskSessionLocalServiceUtil.getTaskSessionsByU_T_I(
				userId, getTaskId(), date1, date2);

		if (taskSessionList == null) {
			taskSessionList = Collections.emptyList();
		}

		return taskSessionList;
	}

}