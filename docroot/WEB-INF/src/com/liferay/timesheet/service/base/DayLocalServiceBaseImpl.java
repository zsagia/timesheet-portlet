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
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.service.persistence.UserPersistence;

import com.liferay.timesheet.model.Day;
import com.liferay.timesheet.service.DayLocalService;
import com.liferay.timesheet.service.persistence.DayPersistence;
import com.liferay.timesheet.service.persistence.ProjectPersistence;
import com.liferay.timesheet.service.persistence.TaskPersistence;
import com.liferay.timesheet.service.persistence.TaskSessionFinder;
import com.liferay.timesheet.service.persistence.TaskSessionPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the day local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.timesheet.service.impl.DayLocalServiceImpl}.
 * </p>
 *
 * @author Istvan Sajtos, Zsolt Szabo
 * @see com.liferay.timesheet.service.impl.DayLocalServiceImpl
 * @see com.liferay.timesheet.service.DayLocalServiceUtil
 * @generated
 */
public abstract class DayLocalServiceBaseImpl extends BaseLocalServiceImpl
	implements DayLocalService, IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.timesheet.service.DayLocalServiceUtil} to access the day local service.
	 */

	/**
	 * Adds the day to the database. Also notifies the appropriate model listeners.
	 *
	 * @param day the day
	 * @return the day that was added
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Day addDay(Day day) throws SystemException {
		day.setNew(true);

		return dayPersistence.update(day);
	}

	/**
	 * Creates a new day with the primary key. Does not add the day to the database.
	 *
	 * @param dayId the primary key for the new day
	 * @return the new day
	 */
	@Override
	public Day createDay(long dayId) {
		return dayPersistence.create(dayId);
	}

	/**
	 * Deletes the day with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dayId the primary key of the day
	 * @return the day that was removed
	 * @throws PortalException if a day with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Day deleteDay(long dayId) throws PortalException, SystemException {
		return dayPersistence.remove(dayId);
	}

	/**
	 * Deletes the day from the database. Also notifies the appropriate model listeners.
	 *
	 * @param day the day
	 * @return the day that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Day deleteDay(Day day) throws SystemException {
		return dayPersistence.remove(day);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(Day.class,
			clazz.getClassLoader());
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
	public List dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return dayPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.DayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return dayPersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.DayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return dayPersistence.findWithDynamicQuery(dynamicQuery, start, end,
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
	public long dynamicQueryCount(DynamicQuery dynamicQuery)
		throws SystemException {
		return dayPersistence.countWithDynamicQuery(dynamicQuery);
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
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) throws SystemException {
		return dayPersistence.countWithDynamicQuery(dynamicQuery, projection);
	}

	@Override
	public Day fetchDay(long dayId) throws SystemException {
		return dayPersistence.fetchByPrimaryKey(dayId);
	}

	/**
	 * Returns the day with the primary key.
	 *
	 * @param dayId the primary key of the day
	 * @return the day
	 * @throws PortalException if a day with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Day getDay(long dayId) throws PortalException, SystemException {
		return dayPersistence.findByPrimaryKey(dayId);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException, SystemException {
		return dayPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the daies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.DayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of daies
	 * @param end the upper bound of the range of daies (not inclusive)
	 * @return the range of daies
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Day> getDaies(int start, int end) throws SystemException {
		return dayPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of daies.
	 *
	 * @return the number of daies
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int getDaiesCount() throws SystemException {
		return dayPersistence.countAll();
	}

	/**
	 * Updates the day in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param day the day
	 * @return the day that was updated
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Day updateDay(Day day) throws SystemException {
		return dayPersistence.update(day);
	}

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

		PersistedModelLocalServiceRegistryUtil.register("com.liferay.timesheet.model.Day",
			dayLocalService);
	}

	public void destroy() {
		PersistedModelLocalServiceRegistryUtil.unregister(
			"com.liferay.timesheet.model.Day");
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
		return Day.class;
	}

	protected String getModelClassName() {
		return Day.class.getName();
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = dayPersistence.getDataSource();

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
	@BeanReference(type = TaskPersistence.class)
	protected TaskPersistence taskPersistence;
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
	private DayLocalServiceClpInvoker _clpInvoker = new DayLocalServiceClpInvoker();
}