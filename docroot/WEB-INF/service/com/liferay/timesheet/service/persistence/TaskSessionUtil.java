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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.liferay.timesheet.model.TaskSession;

import java.util.List;

/**
 * The persistence utility for the task session service. This utility wraps {@link TaskSessionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Istvan Sajtos
 * @see TaskSessionPersistence
 * @see TaskSessionPersistenceImpl
 * @generated
 */
public class TaskSessionUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(TaskSession taskSession) {
		getPersistence().clearCache(taskSession);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<TaskSession> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TaskSession> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TaskSession> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static TaskSession update(TaskSession taskSession, boolean merge)
		throws SystemException {
		return getPersistence().update(taskSession, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static TaskSession update(TaskSession taskSession, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(taskSession, merge, serviceContext);
	}

	/**
	* Caches the task session in the entity cache if it is enabled.
	*
	* @param taskSession the task session
	*/
	public static void cacheResult(
		com.liferay.timesheet.model.TaskSession taskSession) {
		getPersistence().cacheResult(taskSession);
	}

	/**
	* Caches the task sessions in the entity cache if it is enabled.
	*
	* @param taskSessions the task sessions
	*/
	public static void cacheResult(
		java.util.List<com.liferay.timesheet.model.TaskSession> taskSessions) {
		getPersistence().cacheResult(taskSessions);
	}

	/**
	* Creates a new task session with the primary key. Does not add the task session to the database.
	*
	* @param taskSessionId the primary key for the new task session
	* @return the new task session
	*/
	public static com.liferay.timesheet.model.TaskSession create(
		long taskSessionId) {
		return getPersistence().create(taskSessionId);
	}

	/**
	* Removes the task session with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param taskSessionId the primary key of the task session
	* @return the task session that was removed
	* @throws com.liferay.timesheet.NoSuchTaskSessionException if a task session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.timesheet.model.TaskSession remove(
		long taskSessionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchTaskSessionException {
		return getPersistence().remove(taskSessionId);
	}

	public static com.liferay.timesheet.model.TaskSession updateImpl(
		com.liferay.timesheet.model.TaskSession taskSession, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(taskSession, merge);
	}

	/**
	* Returns the task session with the primary key or throws a {@link com.liferay.timesheet.NoSuchTaskSessionException} if it could not be found.
	*
	* @param taskSessionId the primary key of the task session
	* @return the task session
	* @throws com.liferay.timesheet.NoSuchTaskSessionException if a task session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.timesheet.model.TaskSession findByPrimaryKey(
		long taskSessionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchTaskSessionException {
		return getPersistence().findByPrimaryKey(taskSessionId);
	}

	/**
	* Returns the task session with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param taskSessionId the primary key of the task session
	* @return the task session, or <code>null</code> if a task session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.timesheet.model.TaskSession fetchByPrimaryKey(
		long taskSessionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(taskSessionId);
	}

	/**
	* Returns all the task sessions.
	*
	* @return the task sessions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.timesheet.model.TaskSession> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
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
	public static java.util.List<com.liferay.timesheet.model.TaskSession> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
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
	public static java.util.List<com.liferay.timesheet.model.TaskSession> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the task sessions from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of task sessions.
	*
	* @return the number of task sessions
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static TaskSessionPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (TaskSessionPersistence)PortletBeanLocatorUtil.locate(com.liferay.timesheet.service.ClpSerializer.getServletContextName(),
					TaskSessionPersistence.class.getName());

			ReferenceRegistry.registerReference(TaskSessionUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(TaskSessionPersistence persistence) {
	}

	private static TaskSessionPersistence _persistence;
}