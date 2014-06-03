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
 * Provides a wrapper for {@link DayLocalService}.
 *
 * @author Istvan Sajtos, Zsolt Szabo
 * @see DayLocalService
 * @generated
 */
public class DayLocalServiceWrapper implements DayLocalService,
	ServiceWrapper<DayLocalService> {
	public DayLocalServiceWrapper(DayLocalService dayLocalService) {
		_dayLocalService = dayLocalService;
	}

	/**
	* Adds the day to the database. Also notifies the appropriate model listeners.
	*
	* @param day the day
	* @return the day that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.timesheet.model.Day addDay(
		com.liferay.timesheet.model.Day day)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dayLocalService.addDay(day);
	}

	/**
	* Creates a new day with the primary key. Does not add the day to the database.
	*
	* @param dayId the primary key for the new day
	* @return the new day
	*/
	@Override
	public com.liferay.timesheet.model.Day createDay(long dayId) {
		return _dayLocalService.createDay(dayId);
	}

	/**
	* Deletes the day with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dayId the primary key of the day
	* @return the day that was removed
	* @throws PortalException if a day with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.timesheet.model.Day deleteDay(long dayId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dayLocalService.deleteDay(dayId);
	}

	/**
	* Deletes the day from the database. Also notifies the appropriate model listeners.
	*
	* @param day the day
	* @return the day that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.timesheet.model.Day deleteDay(
		com.liferay.timesheet.model.Day day)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dayLocalService.deleteDay(day);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _dayLocalService.dynamicQuery();
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
		return _dayLocalService.dynamicQuery(dynamicQuery);
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _dayLocalService.dynamicQuery(dynamicQuery, start, end);
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dayLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _dayLocalService.dynamicQueryCount(dynamicQuery);
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
		return _dayLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.liferay.timesheet.model.Day fetchDay(long dayId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dayLocalService.fetchDay(dayId);
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
	public com.liferay.timesheet.model.Day getDay(long dayId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dayLocalService.getDay(dayId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dayLocalService.getPersistedModel(primaryKeyObj);
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
	public java.util.List<com.liferay.timesheet.model.Day> getDaies(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _dayLocalService.getDaies(start, end);
	}

	/**
	* Returns the number of daies.
	*
	* @return the number of daies
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getDaiesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dayLocalService.getDaiesCount();
	}

	/**
	* Updates the day in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param day the day
	* @return the day that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.timesheet.model.Day updateDay(
		com.liferay.timesheet.model.Day day)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dayLocalService.updateDay(day);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _dayLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_dayLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _dayLocalService.invokeMethod(name, parameterTypes, arguments);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public DayLocalService getWrappedDayLocalService() {
		return _dayLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedDayLocalService(DayLocalService dayLocalService) {
		_dayLocalService = dayLocalService;
	}

	@Override
	public DayLocalService getWrappedService() {
		return _dayLocalService;
	}

	@Override
	public void setWrappedService(DayLocalService dayLocalService) {
		_dayLocalService = dayLocalService;
	}

	private DayLocalService _dayLocalService;
}