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

package com.liferay.timesheet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * The utility for the task session local service. This utility wraps {@link com.liferay.timesheet.service.impl.TaskSessionLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Istvan Sajtos
 * @see TaskSessionLocalService
 * @see com.liferay.timesheet.service.base.TaskSessionLocalServiceBaseImpl
 * @see com.liferay.timesheet.service.impl.TaskSessionLocalServiceImpl
 * @generated
 */
public class TaskSessionLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.timesheet.service.impl.TaskSessionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the task session to the database. Also notifies the appropriate model listeners.
	*
	* @param taskSession the task session
	* @return the task session that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.timesheet.model.TaskSession addTaskSession(
		com.liferay.timesheet.model.TaskSession taskSession)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addTaskSession(taskSession);
	}

	/**
	* Creates a new task session with the primary key. Does not add the task session to the database.
	*
	* @param taskSessionId the primary key for the new task session
	* @return the new task session
	*/
	public static com.liferay.timesheet.model.TaskSession createTaskSession(
		long taskSessionId) {
		return getService().createTaskSession(taskSessionId);
	}

	/**
	* Deletes the task session with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param taskSessionId the primary key of the task session
	* @return the task session that was removed
	* @throws PortalException if a task session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.timesheet.model.TaskSession deleteTaskSession(
		long taskSessionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteTaskSession(taskSessionId);
	}

	/**
	* Deletes the task session from the database. Also notifies the appropriate model listeners.
	*
	* @param taskSession the task session
	* @return the task session that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.timesheet.model.TaskSession deleteTaskSession(
		com.liferay.timesheet.model.TaskSession taskSession)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteTaskSession(taskSession);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	public static com.liferay.timesheet.model.TaskSession fetchTaskSession(
		long taskSessionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchTaskSession(taskSessionId);
	}

	/**
	* Returns the task session with the primary key.
	*
	* @param taskSessionId the primary key of the task session
	* @return the task session
	* @throws PortalException if a task session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.timesheet.model.TaskSession getTaskSession(
		long taskSessionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getTaskSession(taskSessionId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
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
	public static java.util.List<com.liferay.timesheet.model.TaskSession> getTaskSessions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTaskSessions(start, end);
	}

	/**
	* Returns the number of task sessions.
	*
	* @return the number of task sessions
	* @throws SystemException if a system exception occurred
	*/
	public static int getTaskSessionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTaskSessionsCount();
	}

	/**
	* Updates the task session in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param taskSession the task session
	* @return the task session that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.timesheet.model.TaskSession updateTaskSession(
		com.liferay.timesheet.model.TaskSession taskSession)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateTaskSession(taskSession);
	}

	/**
	* Updates the task session in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param taskSession the task session
	* @param merge whether to merge the task session with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the task session that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.timesheet.model.TaskSession updateTaskSession(
		com.liferay.timesheet.model.TaskSession taskSession, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateTaskSession(taskSession, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static com.liferay.timesheet.model.TaskSession addTaskSession(
		java.util.Date startTime, java.util.Date endTime, long taskId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addTaskSession(startTime, endTime, taskId);
	}

	public static java.util.List<com.liferay.timesheet.model.TaskSession> getTaskSessionsByD_U(
		java.util.Date date, long userId) {
		return getService().getTaskSessionsByD_U(date, userId);
	}

	public static void clearService() {
		_service = null;
	}

	public static TaskSessionLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					TaskSessionLocalService.class.getName());

			if (invokableLocalService instanceof TaskSessionLocalService) {
				_service = (TaskSessionLocalService)invokableLocalService;
			}
			else {
				_service = new TaskSessionLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(TaskSessionLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(TaskSessionLocalService service) {
	}

	private static TaskSessionLocalService _service;
}