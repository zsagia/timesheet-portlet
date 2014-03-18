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

	public static String FIND_BY_COMPANYID_INTERVAL_USERID =
		TaskSessionFinder.class.getName() + ".findByC_I_U";

	public List<TaskSession> findByC_I_U(
		long companyId, Date date1, Date date2, long userId) {

		Session session = null;

		session = openSession();

		String sql = CustomSQLUtil.get(FIND_BY_COMPANYID_INTERVAL_USERID);

		SQLQuery q = session.createSQLQuery(sql);

		q.addScalar("taskSessionId", Type.LONG);
		q.addScalar("companyId", Type.LONG);
		q.addScalar("groupId", Type.LONG);
		q.addScalar("userId", Type.LONG);
		q.addScalar("userName", Type.STRING);
		q.addScalar("createDate", Type.TIMESTAMP);
		q.addScalar("modifiedDate", Type.TIMESTAMP);
		q.addScalar("startTime", Type.TIMESTAMP);
		q.addScalar("endTime", Type.TIMESTAMP);
		q.addScalar("description", Type.STRING);
		q.addScalar("taskId", Type.LONG);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);
		qPos.add(userId);
		qPos.add(date1);
		qPos.add(date2);

		List<Object[]> queriedTaskSessions =
			(List<Object[]>)QueryUtil.list(
				q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		List<TaskSession> taskSessions = assembleTaskSessions(
			queriedTaskSessions);

		return taskSessions;
	}

	private List<TaskSession> assembleTaskSessions(
		List<Object[]> queriedTaskSessions) {

		List<TaskSession> taskSessions = new ArrayList<TaskSession>();

		for (Object[] queriedTaskSession : queriedTaskSessions) {
			TaskSessionImpl taskSession = new TaskSessionImpl();

			taskSession.setTaskSessionId((Long)queriedTaskSession[0]);
			taskSession.setCompanyId((Long)queriedTaskSession[1]);
			taskSession.setGroupId((Long)queriedTaskSession[2]);
			taskSession.setUserId((Long)queriedTaskSession[3]);
			taskSession.setUserName((String)queriedTaskSession[4]);
			taskSession.setCreateDate((Date)queriedTaskSession[5]);
			taskSession.setModifiedDate((Date)queriedTaskSession[6]);
			taskSession.setStartTime((Date)queriedTaskSession[7]);
			taskSession.setEndTime((Date)queriedTaskSession[8]);
			taskSession.setDescription((String)queriedTaskSession[9]);
			taskSession.setTaskId((Long)queriedTaskSession[10]);

			taskSessions.add(taskSession);
		}

		return taskSessions;
	}

}