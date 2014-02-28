/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.timesheet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TaskSessionLocalService}.
 *
 * @author Istvan Sajtos, Zsolt Szabo
 * @see TaskSessionLocalService
 * @generated
 */
public class TaskSessionLocalServiceWrapper implements TaskSessionLocalService,
	ServiceWrapper<TaskSessionLocalService> {
	public TaskSessionLocalServiceWrapper(
		TaskSessionLocalService taskSessionLocalService) {
		_taskSessionLocalService = taskSessionLocalService;
	}

	/**
	* Adds the task session to the database. Also notifies the appropriate model listeners.
	*
	* @param taskSession the task session
	* @return the task session that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.timesheet.model.TaskSession addTaskSession(
		com.liferay.timesheet.model.TaskSession taskSession)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _taskSessionLocalService.addTaskSession(taskSession);
	}

	/**
	* Creates a new task session with the primary key. Does not add the task session to the database.
	*
	* @param taskSessionId the primary key for the new task session
	* @return the new task session
	*/
	@Override
	public com.liferay.timesheet.model.TaskSession createTaskSession(
		long taskSessionId) {
		return _taskSessionLocalService.createTaskSession(taskSessionId);
	}

	/**
	* Deletes the task session with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param taskSessionId the primary key of the task session
	* @return the task session that was removed
	* @throws PortalException if a task session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.timesheet.model.TaskSession deleteTaskSession(
		long taskSessionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _taskSessionLocalService.deleteTaskSession(taskSessionId);
	}

	/**
	* Deletes the task session from the database. Also notifies the appropriate model listeners.
	*
	* @param taskSession the task session
	* @return the task session that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.timesheet.model.TaskSession deleteTaskSession(
		com.liferay.timesheet.model.TaskSession taskSession)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _taskSessionLocalService.deleteTaskSession(taskSession);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _taskSessionLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _taskSessionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.TaskSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _taskSessionLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.TaskSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _taskSessionLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _taskSessionLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _taskSessionLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.timesheet.model.TaskSession fetchTaskSession(
		long taskSessionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _taskSessionLocalService.fetchTaskSession(taskSessionId);
	}

	/**
	* Returns the task session with the primary key.
	*
	* @param taskSessionId the primary key of the task session
	* @return the task session
	* @throws PortalException if a task session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.timesheet.model.TaskSession getTaskSession(
		long taskSessionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _taskSessionLocalService.getTaskSession(taskSessionId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _taskSessionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the task sessions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.TaskSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of task sessions
	* @param end the upper bound of the range of task sessions (not inclusive)
	* @return the range of task sessions
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.liferay.timesheet.model.TaskSession> getTaskSessions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _taskSessionLocalService.getTaskSessions(start, end);
	}

	/**
	* Returns the number of task sessions.
	*
	* @return the number of task sessions
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getTaskSessionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _taskSessionLocalService.getTaskSessionsCount();
	}

	/**
	* Updates the task session in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param taskSession the task session
	* @return the task session that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.timesheet.model.TaskSession updateTaskSession(
		com.liferay.timesheet.model.TaskSession taskSession)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _taskSessionLocalService.updateTaskSession(taskSession);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _taskSessionLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_taskSessionLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _taskSessionLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public com.liferay.timesheet.model.TaskSession addTaskSession(
		java.util.Date startTime, java.util.Date endTime, long taskId,
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return _taskSessionLocalService.addTaskSession(startTime, endTime,
			taskId, userId);
	}

	@Override
	public com.liferay.timesheet.model.TaskSession addTaskSession(
		java.util.Date startTime, long taskId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _taskSessionLocalService.addTaskSession(startTime, taskId, userId);
	}

	@Override
	public com.liferay.timesheet.model.TaskSession getCurrentTaskSession(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return _taskSessionLocalService.getCurrentTaskSession(userId);
	}

	@Override
	public com.liferay.timesheet.model.TaskSession getLastTaskSessionsByD_U(
		java.util.Date date, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _taskSessionLocalService.getLastTaskSessionsByD_U(date, userId);
	}

	@Override
	public java.util.List<com.liferay.timesheet.model.TaskSession> getTaskSessionsByD_U(
		java.util.Date date, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _taskSessionLocalService.getTaskSessionsByD_U(date, userId);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public TaskSessionLocalService getWrappedTaskSessionLocalService() {
		return _taskSessionLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedTaskSessionLocalService(
		TaskSessionLocalService taskSessionLocalService) {
		_taskSessionLocalService = taskSessionLocalService;
	}

	@Override
	public TaskSessionLocalService getWrappedService() {
		return _taskSessionLocalService;
	}

	@Override
	public void setWrappedService(
		TaskSessionLocalService taskSessionLocalService) {
		_taskSessionLocalService = taskSessionLocalService;
	}

	private TaskSessionLocalService _taskSessionLocalService;
}