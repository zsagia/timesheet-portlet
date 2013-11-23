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

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CalendarUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.timesheet.NoSuchTaskSessionException;
import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.model.impl.TaskSessionImpl;
import com.liferay.timesheet.model.impl.TaskSessionModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * The persistence implementation for the task session service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Istvan Sajtos
 * @see TaskSessionPersistence
 * @see TaskSessionUtil
 * @generated
 */
public class TaskSessionPersistenceImpl extends BasePersistenceImpl<TaskSession>
	implements TaskSessionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TaskSessionUtil} to access the task session persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TaskSessionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_GTS = new FinderPath(TaskSessionModelImpl.ENTITY_CACHE_ENABLED,
			TaskSessionModelImpl.FINDER_CACHE_ENABLED, TaskSessionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByU_GtS",
			new String[] {
				Long.class.getName(), Date.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_GTS = new FinderPath(TaskSessionModelImpl.ENTITY_CACHE_ENABLED,
			TaskSessionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByU_GtS",
			new String[] { Long.class.getName(), Date.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TaskSessionModelImpl.ENTITY_CACHE_ENABLED,
			TaskSessionModelImpl.FINDER_CACHE_ENABLED, TaskSessionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TaskSessionModelImpl.ENTITY_CACHE_ENABLED,
			TaskSessionModelImpl.FINDER_CACHE_ENABLED, TaskSessionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TaskSessionModelImpl.ENTITY_CACHE_ENABLED,
			TaskSessionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the task session in the entity cache if it is enabled.
	 *
	 * @param taskSession the task session
	 */
	public void cacheResult(TaskSession taskSession) {
		EntityCacheUtil.putResult(TaskSessionModelImpl.ENTITY_CACHE_ENABLED,
			TaskSessionImpl.class, taskSession.getPrimaryKey(), taskSession);

		taskSession.resetOriginalValues();
	}

	/**
	 * Caches the task sessions in the entity cache if it is enabled.
	 *
	 * @param taskSessions the task sessions
	 */
	public void cacheResult(List<TaskSession> taskSessions) {
		for (TaskSession taskSession : taskSessions) {
			if (EntityCacheUtil.getResult(
						TaskSessionModelImpl.ENTITY_CACHE_ENABLED,
						TaskSessionImpl.class, taskSession.getPrimaryKey()) == null) {
				cacheResult(taskSession);
			}
			else {
				taskSession.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all task sessions.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(TaskSessionImpl.class.getName());
		}

		EntityCacheUtil.clearCache(TaskSessionImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the task session.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TaskSession taskSession) {
		EntityCacheUtil.removeResult(TaskSessionModelImpl.ENTITY_CACHE_ENABLED,
			TaskSessionImpl.class, taskSession.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<TaskSession> taskSessions) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TaskSession taskSession : taskSessions) {
			EntityCacheUtil.removeResult(TaskSessionModelImpl.ENTITY_CACHE_ENABLED,
				TaskSessionImpl.class, taskSession.getPrimaryKey());
		}
	}

	/**
	 * Creates a new task session with the primary key. Does not add the task session to the database.
	 *
	 * @param taskSessionId the primary key for the new task session
	 * @return the new task session
	 */
	public TaskSession create(long taskSessionId) {
		TaskSession taskSession = new TaskSessionImpl();

		taskSession.setNew(true);
		taskSession.setPrimaryKey(taskSessionId);

		return taskSession;
	}

	/**
	 * Removes the task session with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param taskSessionId the primary key of the task session
	 * @return the task session that was removed
	 * @throws com.liferay.timesheet.NoSuchTaskSessionException if a task session with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TaskSession remove(long taskSessionId)
		throws NoSuchTaskSessionException, SystemException {
		return remove(Long.valueOf(taskSessionId));
	}

	/**
	 * Removes the task session with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the task session
	 * @return the task session that was removed
	 * @throws com.liferay.timesheet.NoSuchTaskSessionException if a task session with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TaskSession remove(Serializable primaryKey)
		throws NoSuchTaskSessionException, SystemException {
		Session session = null;

		try {
			session = openSession();

			TaskSession taskSession = (TaskSession)session.get(TaskSessionImpl.class,
					primaryKey);

			if (taskSession == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTaskSessionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(taskSession);
		}
		catch (NoSuchTaskSessionException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected TaskSession removeImpl(TaskSession taskSession)
		throws SystemException {
		taskSession = toUnwrappedModel(taskSession);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, taskSession);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(taskSession);

		return taskSession;
	}

	@Override
	public TaskSession updateImpl(
		com.liferay.timesheet.model.TaskSession taskSession, boolean merge)
		throws SystemException {
		taskSession = toUnwrappedModel(taskSession);

		boolean isNew = taskSession.isNew();

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, taskSession, merge);

			taskSession.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !TaskSessionModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		EntityCacheUtil.putResult(TaskSessionModelImpl.ENTITY_CACHE_ENABLED,
			TaskSessionImpl.class, taskSession.getPrimaryKey(), taskSession);

		return taskSession;
	}

	protected TaskSession toUnwrappedModel(TaskSession taskSession) {
		if (taskSession instanceof TaskSessionImpl) {
			return taskSession;
		}

		TaskSessionImpl taskSessionImpl = new TaskSessionImpl();

		taskSessionImpl.setNew(taskSession.isNew());
		taskSessionImpl.setPrimaryKey(taskSession.getPrimaryKey());

		taskSessionImpl.setTaskSessionId(taskSession.getTaskSessionId());
		taskSessionImpl.setEndTime(taskSession.getEndTime());
		taskSessionImpl.setStartTime(taskSession.getStartTime());
		taskSessionImpl.setTaskId(taskSession.getTaskId());
		taskSessionImpl.setUserId(taskSession.getUserId());

		return taskSessionImpl;
	}

	/**
	 * Returns the task session with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the task session
	 * @return the task session
	 * @throws com.liferay.portal.NoSuchModelException if a task session with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TaskSession findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the task session with the primary key or throws a {@link com.liferay.timesheet.NoSuchTaskSessionException} if it could not be found.
	 *
	 * @param taskSessionId the primary key of the task session
	 * @return the task session
	 * @throws com.liferay.timesheet.NoSuchTaskSessionException if a task session with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TaskSession findByPrimaryKey(long taskSessionId)
		throws NoSuchTaskSessionException, SystemException {
		TaskSession taskSession = fetchByPrimaryKey(taskSessionId);

		if (taskSession == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + taskSessionId);
			}

			throw new NoSuchTaskSessionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				taskSessionId);
		}

		return taskSession;
	}

	/**
	 * Returns the task session with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the task session
	 * @return the task session, or <code>null</code> if a task session with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TaskSession fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the task session with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param taskSessionId the primary key of the task session
	 * @return the task session, or <code>null</code> if a task session with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TaskSession fetchByPrimaryKey(long taskSessionId)
		throws SystemException {
		TaskSession taskSession = (TaskSession)EntityCacheUtil.getResult(TaskSessionModelImpl.ENTITY_CACHE_ENABLED,
				TaskSessionImpl.class, taskSessionId);

		if (taskSession == _nullTaskSession) {
			return null;
		}

		if (taskSession == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				taskSession = (TaskSession)session.get(TaskSessionImpl.class,
						Long.valueOf(taskSessionId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (taskSession != null) {
					cacheResult(taskSession);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(TaskSessionModelImpl.ENTITY_CACHE_ENABLED,
						TaskSessionImpl.class, taskSessionId, _nullTaskSession);
				}

				closeSession(session);
			}
		}

		return taskSession;
	}

	/**
	 * Returns all the task sessions where userId = &#63; and startTime &gt; &#63;.
	 *
	 * @param userId the user ID
	 * @param startTime the start time
	 * @return the matching task sessions
	 * @throws SystemException if a system exception occurred
	 */
	public List<TaskSession> findByU_GtS(long userId, Date startTime)
		throws SystemException {
		return findByU_GtS(userId, startTime, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the task sessions where userId = &#63; and startTime &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param startTime the start time
	 * @param start the lower bound of the range of task sessions
	 * @param end the upper bound of the range of task sessions (not inclusive)
	 * @return the range of matching task sessions
	 * @throws SystemException if a system exception occurred
	 */
	public List<TaskSession> findByU_GtS(long userId, Date startTime,
		int start, int end) throws SystemException {
		return findByU_GtS(userId, startTime, start, end, null);
	}

	/**
	 * Returns an ordered range of all the task sessions where userId = &#63; and startTime &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param startTime the start time
	 * @param start the lower bound of the range of task sessions
	 * @param end the upper bound of the range of task sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching task sessions
	 * @throws SystemException if a system exception occurred
	 */
	public List<TaskSession> findByU_GtS(long userId, Date startTime,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_GTS;
		finderArgs = new Object[] {
				userId, startTime,
				
				start, end, orderByComparator
			};

		List<TaskSession> list = (List<TaskSession>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TaskSession taskSession : list) {
				if ((userId != taskSession.getUserId()) ||
						!Validator.equals(startTime, taskSession.getStartTime())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TASKSESSION_WHERE);

			query.append(_FINDER_COLUMN_U_GTS_USERID_2);

			if (startTime == null) {
				query.append(_FINDER_COLUMN_U_GTS_STARTTIME_1);
			}
			else {
				query.append(_FINDER_COLUMN_U_GTS_STARTTIME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (startTime != null) {
					qPos.add(CalendarUtil.getTimestamp(startTime));
				}

				list = (List<TaskSession>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first task session in the ordered set where userId = &#63; and startTime &gt; &#63;.
	 *
	 * @param userId the user ID
	 * @param startTime the start time
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching task session
	 * @throws com.liferay.timesheet.NoSuchTaskSessionException if a matching task session could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TaskSession findByU_GtS_First(long userId, Date startTime,
		OrderByComparator orderByComparator)
		throws NoSuchTaskSessionException, SystemException {
		TaskSession taskSession = fetchByU_GtS_First(userId, startTime,
				orderByComparator);

		if (taskSession != null) {
			return taskSession;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", startTime=");
		msg.append(startTime);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTaskSessionException(msg.toString());
	}

	/**
	 * Returns the first task session in the ordered set where userId = &#63; and startTime &gt; &#63;.
	 *
	 * @param userId the user ID
	 * @param startTime the start time
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching task session, or <code>null</code> if a matching task session could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TaskSession fetchByU_GtS_First(long userId, Date startTime,
		OrderByComparator orderByComparator) throws SystemException {
		List<TaskSession> list = findByU_GtS(userId, startTime, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last task session in the ordered set where userId = &#63; and startTime &gt; &#63;.
	 *
	 * @param userId the user ID
	 * @param startTime the start time
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching task session
	 * @throws com.liferay.timesheet.NoSuchTaskSessionException if a matching task session could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TaskSession findByU_GtS_Last(long userId, Date startTime,
		OrderByComparator orderByComparator)
		throws NoSuchTaskSessionException, SystemException {
		TaskSession taskSession = fetchByU_GtS_Last(userId, startTime,
				orderByComparator);

		if (taskSession != null) {
			return taskSession;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", startTime=");
		msg.append(startTime);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTaskSessionException(msg.toString());
	}

	/**
	 * Returns the last task session in the ordered set where userId = &#63; and startTime &gt; &#63;.
	 *
	 * @param userId the user ID
	 * @param startTime the start time
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching task session, or <code>null</code> if a matching task session could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TaskSession fetchByU_GtS_Last(long userId, Date startTime,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByU_GtS(userId, startTime);

		List<TaskSession> list = findByU_GtS(userId, startTime, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the task sessions before and after the current task session in the ordered set where userId = &#63; and startTime &gt; &#63;.
	 *
	 * @param taskSessionId the primary key of the current task session
	 * @param userId the user ID
	 * @param startTime the start time
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next task session
	 * @throws com.liferay.timesheet.NoSuchTaskSessionException if a task session with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TaskSession[] findByU_GtS_PrevAndNext(long taskSessionId,
		long userId, Date startTime, OrderByComparator orderByComparator)
		throws NoSuchTaskSessionException, SystemException {
		TaskSession taskSession = findByPrimaryKey(taskSessionId);

		Session session = null;

		try {
			session = openSession();

			TaskSession[] array = new TaskSessionImpl[3];

			array[0] = getByU_GtS_PrevAndNext(session, taskSession, userId,
					startTime, orderByComparator, true);

			array[1] = taskSession;

			array[2] = getByU_GtS_PrevAndNext(session, taskSession, userId,
					startTime, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TaskSession getByU_GtS_PrevAndNext(Session session,
		TaskSession taskSession, long userId, Date startTime,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TASKSESSION_WHERE);

		query.append(_FINDER_COLUMN_U_GTS_USERID_2);

		if (startTime == null) {
			query.append(_FINDER_COLUMN_U_GTS_STARTTIME_1);
		}
		else {
			query.append(_FINDER_COLUMN_U_GTS_STARTTIME_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (startTime != null) {
			qPos.add(CalendarUtil.getTimestamp(startTime));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(taskSession);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TaskSession> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the task sessions.
	 *
	 * @return the task sessions
	 * @throws SystemException if a system exception occurred
	 */
	public List<TaskSession> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the task sessions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of task sessions
	 * @param end the upper bound of the range of task sessions (not inclusive)
	 * @return the range of task sessions
	 * @throws SystemException if a system exception occurred
	 */
	public List<TaskSession> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the task sessions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of task sessions
	 * @param end the upper bound of the range of task sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of task sessions
	 * @throws SystemException if a system exception occurred
	 */
	public List<TaskSession> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<TaskSession> list = (List<TaskSession>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_TASKSESSION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TASKSESSION;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<TaskSession>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<TaskSession>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the task sessions where userId = &#63; and startTime &gt; &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param startTime the start time
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByU_GtS(long userId, Date startTime)
		throws SystemException {
		for (TaskSession taskSession : findByU_GtS(userId, startTime)) {
			remove(taskSession);
		}
	}

	/**
	 * Removes all the task sessions from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (TaskSession taskSession : findAll()) {
			remove(taskSession);
		}
	}

	/**
	 * Returns the number of task sessions where userId = &#63; and startTime &gt; &#63;.
	 *
	 * @param userId the user ID
	 * @param startTime the start time
	 * @return the number of matching task sessions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByU_GtS(long userId, Date startTime)
		throws SystemException {
		Object[] finderArgs = new Object[] { userId, startTime };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_GTS,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TASKSESSION_WHERE);

			query.append(_FINDER_COLUMN_U_GTS_USERID_2);

			if (startTime == null) {
				query.append(_FINDER_COLUMN_U_GTS_STARTTIME_1);
			}
			else {
				query.append(_FINDER_COLUMN_U_GTS_STARTTIME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (startTime != null) {
					qPos.add(CalendarUtil.getTimestamp(startTime));
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_GTS,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of task sessions.
	 *
	 * @return the number of task sessions
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TASKSESSION);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the task session persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.timesheet.model.TaskSession")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<TaskSession>> listenersList = new ArrayList<ModelListener<TaskSession>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<TaskSession>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(TaskSessionImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = TaskPersistence.class)
	protected TaskPersistence taskPersistence;
	@BeanReference(type = TaskSessionPersistence.class)
	protected TaskSessionPersistence taskSessionPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_TASKSESSION = "SELECT taskSession FROM TaskSession taskSession";
	private static final String _SQL_SELECT_TASKSESSION_WHERE = "SELECT taskSession FROM TaskSession taskSession WHERE ";
	private static final String _SQL_COUNT_TASKSESSION = "SELECT COUNT(taskSession) FROM TaskSession taskSession";
	private static final String _SQL_COUNT_TASKSESSION_WHERE = "SELECT COUNT(taskSession) FROM TaskSession taskSession WHERE ";
	private static final String _FINDER_COLUMN_U_GTS_USERID_2 = "taskSession.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_GTS_STARTTIME_1 = "taskSession.startTime > NULL";
	private static final String _FINDER_COLUMN_U_GTS_STARTTIME_2 = "taskSession.startTime > ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "taskSession.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TaskSession exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TaskSession exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(TaskSessionPersistenceImpl.class);
	private static TaskSession _nullTaskSession = new TaskSessionImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<TaskSession> toCacheModel() {
				return _nullTaskSessionCacheModel;
			}
		};

	private static CacheModel<TaskSession> _nullTaskSessionCacheModel = new CacheModel<TaskSession>() {
			public TaskSession toEntityModel() {
				return _nullTaskSession;
			}
		};
}