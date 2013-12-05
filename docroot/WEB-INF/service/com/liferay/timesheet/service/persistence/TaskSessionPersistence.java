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

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.timesheet.model.TaskSession;

/**
 * The persistence interface for the task session service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Istvan Sajtos
 * @see TaskSessionPersistenceImpl
 * @see TaskSessionUtil
 * @generated
 */
public interface TaskSessionPersistence extends BasePersistence<TaskSession> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TaskSessionUtil} to access the task session persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the task session in the entity cache if it is enabled.
	*
	* @param taskSession the task session
	*/
	public void cacheResult(com.liferay.timesheet.model.TaskSession taskSession);

	/**
	* Caches the task sessions in the entity cache if it is enabled.
	*
	* @param taskSessions the task sessions
	*/
	public void cacheResult(
		java.util.List<com.liferay.timesheet.model.TaskSession> taskSessions);

	/**
	* Creates a new task session with the primary key. Does not add the task session to the database.
	*
	* @param taskSessionId the primary key for the new task session
	* @return the new task session
	*/
	public com.liferay.timesheet.model.TaskSession create(long taskSessionId);

	/**
	* Removes the task session with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param taskSessionId the primary key of the task session
	* @return the task session that was removed
	* @throws com.liferay.timesheet.NoSuchTaskSessionException if a task session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.TaskSession remove(long taskSessionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchTaskSessionException;

	public com.liferay.timesheet.model.TaskSession updateImpl(
		com.liferay.timesheet.model.TaskSession taskSession, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the task session with the primary key or throws a {@link com.liferay.timesheet.NoSuchTaskSessionException} if it could not be found.
	*
	* @param taskSessionId the primary key of the task session
	* @return the task session
	* @throws com.liferay.timesheet.NoSuchTaskSessionException if a task session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.TaskSession findByPrimaryKey(
		long taskSessionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchTaskSessionException;

	/**
	* Returns the task session with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param taskSessionId the primary key of the task session
	* @return the task session, or <code>null</code> if a task session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.TaskSession fetchByPrimaryKey(
		long taskSessionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the task session where userId = &#63; and endTime = &#63; or throws a {@link com.liferay.timesheet.NoSuchTaskSessionException} if it could not be found.
	*
	* @param userId the user ID
	* @param endTime the end time
	* @return the matching task session
	* @throws com.liferay.timesheet.NoSuchTaskSessionException if a matching task session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.TaskSession findByU_E(long userId,
		java.util.Date endTime)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchTaskSessionException;

	/**
	* Returns the task session where userId = &#63; and endTime = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param endTime the end time
	* @return the matching task session, or <code>null</code> if a matching task session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.TaskSession fetchByU_E(long userId,
		java.util.Date endTime)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the task session where userId = &#63; and endTime = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param endTime the end time
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching task session, or <code>null</code> if a matching task session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.TaskSession fetchByU_E(long userId,
		java.util.Date endTime, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the task sessions where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching task sessions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.timesheet.model.TaskSession> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the task sessions where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of task sessions
	* @param end the upper bound of the range of task sessions (not inclusive)
	* @return the range of matching task sessions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.timesheet.model.TaskSession> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the task sessions where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of task sessions
	* @param end the upper bound of the range of task sessions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching task sessions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.timesheet.model.TaskSession> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first task session in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching task session
	* @throws com.liferay.timesheet.NoSuchTaskSessionException if a matching task session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.TaskSession findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchTaskSessionException;

	/**
	* Returns the first task session in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching task session, or <code>null</code> if a matching task session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.TaskSession fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last task session in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching task session
	* @throws com.liferay.timesheet.NoSuchTaskSessionException if a matching task session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.TaskSession findByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchTaskSessionException;

	/**
	* Returns the last task session in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching task session, or <code>null</code> if a matching task session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.TaskSession fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the task sessions before and after the current task session in the ordered set where userId = &#63;.
	*
	* @param taskSessionId the primary key of the current task session
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next task session
	* @throws com.liferay.timesheet.NoSuchTaskSessionException if a task session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.TaskSession[] findByUserId_PrevAndNext(
		long taskSessionId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchTaskSessionException;

	/**
	* Returns all the task sessions where userId = &#63; and startTime &gt; &#63;.
	*
	* @param userId the user ID
	* @param startTime the start time
	* @return the matching task sessions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.timesheet.model.TaskSession> findByU_GtS(
		long userId, java.util.Date startTime)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.timesheet.model.TaskSession> findByU_GtS(
		long userId, java.util.Date startTime, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.timesheet.model.TaskSession> findByU_GtS(
		long userId, java.util.Date startTime, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.timesheet.model.TaskSession findByU_GtS_First(
		long userId, java.util.Date startTime,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchTaskSessionException;

	/**
	* Returns the first task session in the ordered set where userId = &#63; and startTime &gt; &#63;.
	*
	* @param userId the user ID
	* @param startTime the start time
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching task session, or <code>null</code> if a matching task session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.TaskSession fetchByU_GtS_First(
		long userId, java.util.Date startTime,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.timesheet.model.TaskSession findByU_GtS_Last(
		long userId, java.util.Date startTime,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchTaskSessionException;

	/**
	* Returns the last task session in the ordered set where userId = &#63; and startTime &gt; &#63;.
	*
	* @param userId the user ID
	* @param startTime the start time
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching task session, or <code>null</code> if a matching task session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.TaskSession fetchByU_GtS_Last(
		long userId, java.util.Date startTime,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.timesheet.model.TaskSession[] findByU_GtS_PrevAndNext(
		long taskSessionId, long userId, java.util.Date startTime,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchTaskSessionException;

	/**
	* Returns all the task sessions.
	*
	* @return the task sessions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.timesheet.model.TaskSession> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.timesheet.model.TaskSession> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.timesheet.model.TaskSession> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the task session where userId = &#63; and endTime = &#63; from the database.
	*
	* @param userId the user ID
	* @param endTime the end time
	* @return the task session that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.TaskSession removeByU_E(long userId,
		java.util.Date endTime)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchTaskSessionException;

	/**
	* Removes all the task sessions where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the task sessions where userId = &#63; and startTime &gt; &#63; from the database.
	*
	* @param userId the user ID
	* @param startTime the start time
	* @throws SystemException if a system exception occurred
	*/
	public void removeByU_GtS(long userId, java.util.Date startTime)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the task sessions from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of task sessions where userId = &#63; and endTime = &#63;.
	*
	* @param userId the user ID
	* @param endTime the end time
	* @return the number of matching task sessions
	* @throws SystemException if a system exception occurred
	*/
	public int countByU_E(long userId, java.util.Date endTime)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of task sessions where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching task sessions
	* @throws SystemException if a system exception occurred
	*/
	public int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of task sessions where userId = &#63; and startTime &gt; &#63;.
	*
	* @param userId the user ID
	* @param startTime the start time
	* @return the number of matching task sessions
	* @throws SystemException if a system exception occurred
	*/
	public int countByU_GtS(long userId, java.util.Date startTime)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of task sessions.
	*
	* @return the number of task sessions
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}