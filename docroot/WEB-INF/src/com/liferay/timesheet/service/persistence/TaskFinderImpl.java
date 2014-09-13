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

package com.liferay.timesheet.service.persistence;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.timesheet.model.Task;
import com.liferay.timesheet.model.impl.TaskImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class TaskFinderImpl extends BasePersistenceImpl<Task>
	implements TaskFinder {

	public static String FIND_BY_USERID_TASKTYPE =
		TaskFinder.class.getName() + ".findByU_TT";

	public List<Task> findByU_TT(long userId, int taskType) {

		Session session = null;

		session = openSession();

		String sql = CustomSQLUtil.get(FIND_BY_USERID_TASKTYPE);

		SQLQuery q = session.createSQLQuery(sql);

		q.addScalar("taskId", Type.LONG);
		q.addScalar("companyId", Type.LONG);
		q.addScalar("groupId", Type.LONG);
		q.addScalar("userId", Type.LONG);
		q.addScalar("userName", Type.STRING);
		q.addScalar("createDate", Type.TIMESTAMP);
		q.addScalar("modifiedDate", Type.TIMESTAMP);
		q.addScalar("description", Type.STRING);
		q.addScalar("projectId", Type.LONG);
		q.addScalar("taskName", Type.STRING);
		q.addScalar("taskType", Type.INTEGER);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);
		qPos.add(taskType);

		@SuppressWarnings("unchecked")
		List<Object[]> queriedTasks =
			(List<Object[]>)QueryUtil.list(
				q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		List<Task> tasks = assembleTasks(queriedTasks);

		return tasks;
	}

	private List<Task> assembleTasks(List<Object[]> queriedTasks) {
		List<Task> tasks = new ArrayList<Task>();

		for (Object[] queriedTask : queriedTasks) {
			Task task = new TaskImpl();

			task.setTaskId((Long)queriedTask[0]);
			task.setCompanyId((Long)queriedTask[1]);
			task.setGroupId((Long)queriedTask[2]);
			task.setUserId((Long)queriedTask[3]);
			task.setUserName((String)queriedTask[4]);
			task.setCreateDate((Date)queriedTask[5]);
			task.setModifiedDate((Date)queriedTask[6]);
			task.setDescription((String)queriedTask[7]);
			task.setProjectId((Long)queriedTask[8]);
			task.setTaskName((String)queriedTask[9]);
			task.setTaskType((Integer)queriedTask[10]);

			tasks.add(task);
		}

		return tasks;
	}

}