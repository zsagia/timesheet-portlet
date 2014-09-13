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

package com.liferay.timesheet.service.base;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.BaseServiceImpl;
import com.liferay.portal.service.persistence.UserPersistence;

import com.liferay.timesheet.model.Task;
import com.liferay.timesheet.service.TaskService;
import com.liferay.timesheet.service.persistence.DayPersistence;
import com.liferay.timesheet.service.persistence.ProjectPersistence;
import com.liferay.timesheet.service.persistence.TaskFinder;
import com.liferay.timesheet.service.persistence.TaskPersistence;
import com.liferay.timesheet.service.persistence.TaskSessionFinder;
import com.liferay.timesheet.service.persistence.TaskSessionPersistence;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the task remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.timesheet.service.impl.TaskServiceImpl}.
 * </p>
 *
 * @author Istvan Sajtos, Zsolt Szabo
 * @see com.liferay.timesheet.service.impl.TaskServiceImpl
 * @see com.liferay.timesheet.service.TaskServiceUtil
 * @generated
 */
public abstract class TaskServiceBaseImpl extends BaseServiceImpl
	implements TaskService, IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.timesheet.service.TaskServiceUtil} to access the task remote service.
	 */

	/**
	 * Returns the day local service.
	 *
	 * @return the day local service
	 */
	public com.liferay.timesheet.service.DayLocalService getDayLocalService() {
		return dayLocalService;
	}

	/**
	 * Sets the day local service.
	 *
	 * @param dayLocalService the day local service
	 */
	public void setDayLocalService(
		com.liferay.timesheet.service.DayLocalService dayLocalService) {
		this.dayLocalService = dayLocalService;
	}

	/**
	 * Returns the day persistence.
	 *
	 * @return the day persistence
	 */
	public DayPersistence getDayPersistence() {
		return dayPersistence;
	}

	/**
	 * Sets the day persistence.
	 *
	 * @param dayPersistence the day persistence
	 */
	public void setDayPersistence(DayPersistence dayPersistence) {
		this.dayPersistence = dayPersistence;
	}

	/**
	 * Returns the project local service.
	 *
	 * @return the project local service
	 */
	public com.liferay.timesheet.service.ProjectLocalService getProjectLocalService() {
		return projectLocalService;
	}

	/**
	 * Sets the project local service.
	 *
	 * @param projectLocalService the project local service
	 */
	public void setProjectLocalService(
		com.liferay.timesheet.service.ProjectLocalService projectLocalService) {
		this.projectLocalService = projectLocalService;
	}

	/**
	 * Returns the project remote service.
	 *
	 * @return the project remote service
	 */
	public com.liferay.timesheet.service.ProjectService getProjectService() {
		return projectService;
	}

	/**
	 * Sets the project remote service.
	 *
	 * @param projectService the project remote service
	 */
	public void setProjectService(
		com.liferay.timesheet.service.ProjectService projectService) {
		this.projectService = projectService;
	}

	/**
	 * Returns the project persistence.
	 *
	 * @return the project persistence
	 */
	public ProjectPersistence getProjectPersistence() {
		return projectPersistence;
	}

	/**
	 * Sets the project persistence.
	 *
	 * @param projectPersistence the project persistence
	 */
	public void setProjectPersistence(ProjectPersistence projectPersistence) {
		this.projectPersistence = projectPersistence;
	}

	/**
	 * Returns the task local service.
	 *
	 * @return the task local service
	 */
	public com.liferay.timesheet.service.TaskLocalService getTaskLocalService() {
		return taskLocalService;
	}

	/**
	 * Sets the task local service.
	 *
	 * @param taskLocalService the task local service
	 */
	public void setTaskLocalService(
		com.liferay.timesheet.service.TaskLocalService taskLocalService) {
		this.taskLocalService = taskLocalService;
	}

	/**
	 * Returns the task remote service.
	 *
	 * @return the task remote service
	 */
	public com.liferay.timesheet.service.TaskService getTaskService() {
		return taskService;
	}

	/**
	 * Sets the task remote service.
	 *
	 * @param taskService the task remote service
	 */
	public void setTaskService(
		com.liferay.timesheet.service.TaskService taskService) {
		this.taskService = taskService;
	}

	/**
	 * Returns the task persistence.
	 *
	 * @return the task persistence
	 */
	public TaskPersistence getTaskPersistence() {
		return taskPersistence;
	}

	/**
	 * Sets the task persistence.
	 *
	 * @param taskPersistence the task persistence
	 */
	public void setTaskPersistence(TaskPersistence taskPersistence) {
		this.taskPersistence = taskPersistence;
	}

	/**
	 * Returns the task finder.
	 *
	 * @return the task finder
	 */
	public TaskFinder getTaskFinder() {
		return taskFinder;
	}

	/**
	 * Sets the task finder.
	 *
	 * @param taskFinder the task finder
	 */
	public void setTaskFinder(TaskFinder taskFinder) {
		this.taskFinder = taskFinder;
	}

	/**
	 * Returns the task session local service.
	 *
	 * @return the task session local service
	 */
	public com.liferay.timesheet.service.TaskSessionLocalService getTaskSessionLocalService() {
		return taskSessionLocalService;
	}

	/**
	 * Sets the task session local service.
	 *
	 * @param taskSessionLocalService the task session local service
	 */
	public void setTaskSessionLocalService(
		com.liferay.timesheet.service.TaskSessionLocalService taskSessionLocalService) {
		this.taskSessionLocalService = taskSessionLocalService;
	}

	/**
	 * Returns the task session persistence.
	 *
	 * @return the task session persistence
	 */
	public TaskSessionPersistence getTaskSessionPersistence() {
		return taskSessionPersistence;
	}

	/**
	 * Sets the task session persistence.
	 *
	 * @param taskSessionPersistence the task session persistence
	 */
	public void setTaskSessionPersistence(
		TaskSessionPersistence taskSessionPersistence) {
		this.taskSessionPersistence = taskSessionPersistence;
	}

	/**
	 * Returns the task session finder.
	 *
	 * @return the task session finder
	 */
	public TaskSessionFinder getTaskSessionFinder() {
		return taskSessionFinder;
	}

	/**
	 * Sets the task session finder.
	 *
	 * @param taskSessionFinder the task session finder
	 */
	public void setTaskSessionFinder(TaskSessionFinder taskSessionFinder) {
		this.taskSessionFinder = taskSessionFinder;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.service.UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		Class<?> clazz = getClass();

		_classLoader = clazz.getClassLoader();
	}

	public void destroy() {
	}

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	@Override
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier the Spring bean ID for this bean
	 */
	@Override
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	@Override
	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		if (contextClassLoader != _classLoader) {
			currentThread.setContextClassLoader(_classLoader);
		}

		try {
			return _clpInvoker.invokeMethod(name, parameterTypes, arguments);
		}
		finally {
			if (contextClassLoader != _classLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	protected Class<?> getModelClass() {
		return Task.class;
	}

	protected String getModelClassName() {
		return Task.class.getName();
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = taskPersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.liferay.timesheet.service.DayLocalService.class)
	protected com.liferay.timesheet.service.DayLocalService dayLocalService;
	@BeanReference(type = DayPersistence.class)
	protected DayPersistence dayPersistence;
	@BeanReference(type = com.liferay.timesheet.service.ProjectLocalService.class)
	protected com.liferay.timesheet.service.ProjectLocalService projectLocalService;
	@BeanReference(type = com.liferay.timesheet.service.ProjectService.class)
	protected com.liferay.timesheet.service.ProjectService projectService;
	@BeanReference(type = ProjectPersistence.class)
	protected ProjectPersistence projectPersistence;
	@BeanReference(type = com.liferay.timesheet.service.TaskLocalService.class)
	protected com.liferay.timesheet.service.TaskLocalService taskLocalService;
	@BeanReference(type = com.liferay.timesheet.service.TaskService.class)
	protected com.liferay.timesheet.service.TaskService taskService;
	@BeanReference(type = TaskPersistence.class)
	protected TaskPersistence taskPersistence;
	@BeanReference(type = TaskFinder.class)
	protected TaskFinder taskFinder;
	@BeanReference(type = com.liferay.timesheet.service.TaskSessionLocalService.class)
	protected com.liferay.timesheet.service.TaskSessionLocalService taskSessionLocalService;
	@BeanReference(type = TaskSessionPersistence.class)
	protected TaskSessionPersistence taskSessionPersistence;
	@BeanReference(type = TaskSessionFinder.class)
	protected TaskSessionFinder taskSessionFinder;
	@BeanReference(type = com.liferay.counter.service.CounterLocalService.class)
	protected com.liferay.counter.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.service.ResourceLocalService.class)
	protected com.liferay.portal.service.ResourceLocalService resourceLocalService;
	@BeanReference(type = com.liferay.portal.service.UserLocalService.class)
	protected com.liferay.portal.service.UserLocalService userLocalService;
	@BeanReference(type = com.liferay.portal.service.UserService.class)
	protected com.liferay.portal.service.UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private String _beanIdentifier;
	private ClassLoader _classLoader;
	private TaskServiceClpInvoker _clpInvoker = new TaskServiceClpInvoker();
}