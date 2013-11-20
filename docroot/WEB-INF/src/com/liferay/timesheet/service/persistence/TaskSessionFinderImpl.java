package com.liferay.timesheet.service.persistence;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.model.impl.TaskSessionImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskSessionFinderImpl extends BasePersistenceImpl<TaskSession>
	implements TaskSessionFinder {

	public static String FIND_BY_DATE_USERID =
		TaskSessionFinder.class.getName() + ".findByD_U";

	public List<TaskSession> findByD_U(
		Date date, long userId, int start, int end) {

		Session session = null;

		session = openSession();

		String sql = CustomSQLUtil.get(FIND_BY_DATE_USERID);

		SQLQuery q = session.createSQLQuery(sql);

		q.addScalar("startTime", Type.DATE);
		q.addScalar("endTime", Type.DATE);
		q.addScalar("taskName", Type.STRING);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);
		qPos.add(date);
		qPos.add(date);

		List<Object[]> queriedTaskSessions =
			(List<Object[]>) QueryUtil.list(
				q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		List<TaskSession> taskSessions =
			assembleTaskSessions(queriedTaskSessions);

		return taskSessions;
	}

	private List<TaskSession> assembleTaskSessions(
		List<Object[]> queriedTaskSessions) {

		List<TaskSession> taskSessions = new ArrayList<TaskSession>();

		for (Object[] queriedTaskSession : queriedTaskSessions) {
			TaskSessionImpl taskSession = new TaskSessionImpl();

			taskSession.setStartTime((Date) queriedTaskSession[0]);
			taskSession.setEndTime((Date) queriedTaskSession[1]);
			taskSession.setTaskName((String) queriedTaskSession[2]);

			taskSessions.add(taskSession);
		}

		return taskSessions;
	}

}