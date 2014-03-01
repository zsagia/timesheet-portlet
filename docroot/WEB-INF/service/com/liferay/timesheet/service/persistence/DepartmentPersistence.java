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

package com.liferay.timesheet.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.timesheet.model.Department;

/**
 * The persistence interface for the department service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Istvan Sajtos, Zsolt Szabo
 * @see DepartmentPersistenceImpl
 * @see DepartmentUtil
 * @generated
 */
public interface DepartmentPersistence extends BasePersistence<Department> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DepartmentUtil} to access the department persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the department in the entity cache if it is enabled.
	*
	* @param department the department
	*/
	public void cacheResult(com.liferay.timesheet.model.Department department);

	/**
	* Caches the departments in the entity cache if it is enabled.
	*
	* @param departments the departments
	*/
	public void cacheResult(
		java.util.List<com.liferay.timesheet.model.Department> departments);

	/**
	* Creates a new department with the primary key. Does not add the department to the database.
	*
	* @param departmentId the primary key for the new department
	* @return the new department
	*/
	public com.liferay.timesheet.model.Department create(long departmentId);

	/**
	* Removes the department with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param departmentId the primary key of the department
	* @return the department that was removed
	* @throws com.liferay.timesheet.NoSuchDepartmentException if a department with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Department remove(long departmentId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchDepartmentException;

	public com.liferay.timesheet.model.Department updateImpl(
		com.liferay.timesheet.model.Department department)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the department with the primary key or throws a {@link com.liferay.timesheet.NoSuchDepartmentException} if it could not be found.
	*
	* @param departmentId the primary key of the department
	* @return the department
	* @throws com.liferay.timesheet.NoSuchDepartmentException if a department with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Department findByPrimaryKey(
		long departmentId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchDepartmentException;

	/**
	* Returns the department with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param departmentId the primary key of the department
	* @return the department, or <code>null</code> if a department with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Department fetchByPrimaryKey(
		long departmentId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the departments.
	*
	* @return the departments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.timesheet.model.Department> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the departments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.DepartmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of departments
	* @param end the upper bound of the range of departments (not inclusive)
	* @return the range of departments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.timesheet.model.Department> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the departments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.DepartmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of departments
	* @param end the upper bound of the range of departments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of departments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.timesheet.model.Department> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the departments from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of departments.
	*
	* @return the number of departments
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}