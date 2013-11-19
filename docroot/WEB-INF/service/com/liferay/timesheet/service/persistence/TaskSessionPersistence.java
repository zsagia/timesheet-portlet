/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
	* Removes all the task sessions from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
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