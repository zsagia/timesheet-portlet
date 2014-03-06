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

import com.liferay.timesheet.model.Project;

/**
 * The persistence interface for the project service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Istvan Sajtos, Zsolt Szabo
 * @see ProjectPersistenceImpl
 * @see ProjectUtil
 * @generated
 */
public interface ProjectPersistence extends BasePersistence<Project> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ProjectUtil} to access the project persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the projects where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.timesheet.model.Project> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the projects where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.ProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of projects
	* @param end the upper bound of the range of projects (not inclusive)
	* @return the range of matching projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.timesheet.model.Project> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the projects where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.ProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of projects
	* @param end the upper bound of the range of projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.timesheet.model.Project> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first project in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching project
	* @throws com.liferay.timesheet.NoSuchProjectException if a matching project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Project findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchProjectException;

	/**
	* Returns the first project in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching project, or <code>null</code> if a matching project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Project fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last project in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching project
	* @throws com.liferay.timesheet.NoSuchProjectException if a matching project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Project findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchProjectException;

	/**
	* Returns the last project in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching project, or <code>null</code> if a matching project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Project fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the projects before and after the current project in the ordered set where uuid = &#63;.
	*
	* @param projectId the primary key of the current project
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next project
	* @throws com.liferay.timesheet.NoSuchProjectException if a project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Project[] findByUuid_PrevAndNext(
		long projectId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchProjectException;

	/**
	* Removes all the projects where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of projects where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching projects
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the projects where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.timesheet.model.Project> findByUuid_C(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the projects where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.ProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of projects
	* @param end the upper bound of the range of projects (not inclusive)
	* @return the range of matching projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.timesheet.model.Project> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the projects where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.ProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of projects
	* @param end the upper bound of the range of projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.timesheet.model.Project> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first project in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching project
	* @throws com.liferay.timesheet.NoSuchProjectException if a matching project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Project findByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchProjectException;

	/**
	* Returns the first project in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching project, or <code>null</code> if a matching project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Project fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last project in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching project
	* @throws com.liferay.timesheet.NoSuchProjectException if a matching project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Project findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchProjectException;

	/**
	* Returns the last project in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching project, or <code>null</code> if a matching project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Project fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the projects before and after the current project in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param projectId the primary key of the current project
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next project
	* @throws com.liferay.timesheet.NoSuchProjectException if a project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Project[] findByUuid_C_PrevAndNext(
		long projectId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchProjectException;

	/**
	* Removes all the projects where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of projects where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching projects
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the projects where departmentId = &#63;.
	*
	* @param departmentId the department ID
	* @return the matching projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.timesheet.model.Project> findByDepartmentId(
		java.lang.Long departmentId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the projects where departmentId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.ProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param departmentId the department ID
	* @param start the lower bound of the range of projects
	* @param end the upper bound of the range of projects (not inclusive)
	* @return the range of matching projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.timesheet.model.Project> findByDepartmentId(
		java.lang.Long departmentId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the projects where departmentId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.ProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param departmentId the department ID
	* @param start the lower bound of the range of projects
	* @param end the upper bound of the range of projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.timesheet.model.Project> findByDepartmentId(
		java.lang.Long departmentId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first project in the ordered set where departmentId = &#63;.
	*
	* @param departmentId the department ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching project
	* @throws com.liferay.timesheet.NoSuchProjectException if a matching project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Project findByDepartmentId_First(
		java.lang.Long departmentId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchProjectException;

	/**
	* Returns the first project in the ordered set where departmentId = &#63;.
	*
	* @param departmentId the department ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching project, or <code>null</code> if a matching project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Project fetchByDepartmentId_First(
		java.lang.Long departmentId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last project in the ordered set where departmentId = &#63;.
	*
	* @param departmentId the department ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching project
	* @throws com.liferay.timesheet.NoSuchProjectException if a matching project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Project findByDepartmentId_Last(
		java.lang.Long departmentId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchProjectException;

	/**
	* Returns the last project in the ordered set where departmentId = &#63;.
	*
	* @param departmentId the department ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching project, or <code>null</code> if a matching project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Project fetchByDepartmentId_Last(
		java.lang.Long departmentId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the projects before and after the current project in the ordered set where departmentId = &#63;.
	*
	* @param projectId the primary key of the current project
	* @param departmentId the department ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next project
	* @throws com.liferay.timesheet.NoSuchProjectException if a project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Project[] findByDepartmentId_PrevAndNext(
		long projectId, java.lang.Long departmentId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchProjectException;

	/**
	* Removes all the projects where departmentId = &#63; from the database.
	*
	* @param departmentId the department ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByDepartmentId(java.lang.Long departmentId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of projects where departmentId = &#63;.
	*
	* @param departmentId the department ID
	* @return the number of matching projects
	* @throws SystemException if a system exception occurred
	*/
	public int countByDepartmentId(java.lang.Long departmentId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the projects where departmentId = &#63; and parentProjectId = &#63;.
	*
	* @param departmentId the department ID
	* @param parentProjectId the parent project ID
	* @return the matching projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.timesheet.model.Project> findByD_PP(
		java.lang.Long departmentId, java.lang.Long parentProjectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the projects where departmentId = &#63; and parentProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.ProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param departmentId the department ID
	* @param parentProjectId the parent project ID
	* @param start the lower bound of the range of projects
	* @param end the upper bound of the range of projects (not inclusive)
	* @return the range of matching projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.timesheet.model.Project> findByD_PP(
		java.lang.Long departmentId, java.lang.Long parentProjectId, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the projects where departmentId = &#63; and parentProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.ProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param departmentId the department ID
	* @param parentProjectId the parent project ID
	* @param start the lower bound of the range of projects
	* @param end the upper bound of the range of projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.timesheet.model.Project> findByD_PP(
		java.lang.Long departmentId, java.lang.Long parentProjectId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first project in the ordered set where departmentId = &#63; and parentProjectId = &#63;.
	*
	* @param departmentId the department ID
	* @param parentProjectId the parent project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching project
	* @throws com.liferay.timesheet.NoSuchProjectException if a matching project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Project findByD_PP_First(
		java.lang.Long departmentId, java.lang.Long parentProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchProjectException;

	/**
	* Returns the first project in the ordered set where departmentId = &#63; and parentProjectId = &#63;.
	*
	* @param departmentId the department ID
	* @param parentProjectId the parent project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching project, or <code>null</code> if a matching project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Project fetchByD_PP_First(
		java.lang.Long departmentId, java.lang.Long parentProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last project in the ordered set where departmentId = &#63; and parentProjectId = &#63;.
	*
	* @param departmentId the department ID
	* @param parentProjectId the parent project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching project
	* @throws com.liferay.timesheet.NoSuchProjectException if a matching project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Project findByD_PP_Last(
		java.lang.Long departmentId, java.lang.Long parentProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchProjectException;

	/**
	* Returns the last project in the ordered set where departmentId = &#63; and parentProjectId = &#63;.
	*
	* @param departmentId the department ID
	* @param parentProjectId the parent project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching project, or <code>null</code> if a matching project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Project fetchByD_PP_Last(
		java.lang.Long departmentId, java.lang.Long parentProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the projects before and after the current project in the ordered set where departmentId = &#63; and parentProjectId = &#63;.
	*
	* @param projectId the primary key of the current project
	* @param departmentId the department ID
	* @param parentProjectId the parent project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next project
	* @throws com.liferay.timesheet.NoSuchProjectException if a project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Project[] findByD_PP_PrevAndNext(
		long projectId, java.lang.Long departmentId,
		java.lang.Long parentProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchProjectException;

	/**
	* Removes all the projects where departmentId = &#63; and parentProjectId = &#63; from the database.
	*
	* @param departmentId the department ID
	* @param parentProjectId the parent project ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByD_PP(java.lang.Long departmentId,
		java.lang.Long parentProjectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of projects where departmentId = &#63; and parentProjectId = &#63;.
	*
	* @param departmentId the department ID
	* @param parentProjectId the parent project ID
	* @return the number of matching projects
	* @throws SystemException if a system exception occurred
	*/
	public int countByD_PP(java.lang.Long departmentId,
		java.lang.Long parentProjectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the projects where parentProjectId = &#63;.
	*
	* @param parentProjectId the parent project ID
	* @return the matching projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.timesheet.model.Project> findByParentProjectId(
		java.lang.Long parentProjectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the projects where parentProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.ProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentProjectId the parent project ID
	* @param start the lower bound of the range of projects
	* @param end the upper bound of the range of projects (not inclusive)
	* @return the range of matching projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.timesheet.model.Project> findByParentProjectId(
		java.lang.Long parentProjectId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the projects where parentProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.ProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentProjectId the parent project ID
	* @param start the lower bound of the range of projects
	* @param end the upper bound of the range of projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.timesheet.model.Project> findByParentProjectId(
		java.lang.Long parentProjectId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first project in the ordered set where parentProjectId = &#63;.
	*
	* @param parentProjectId the parent project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching project
	* @throws com.liferay.timesheet.NoSuchProjectException if a matching project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Project findByParentProjectId_First(
		java.lang.Long parentProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchProjectException;

	/**
	* Returns the first project in the ordered set where parentProjectId = &#63;.
	*
	* @param parentProjectId the parent project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching project, or <code>null</code> if a matching project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Project fetchByParentProjectId_First(
		java.lang.Long parentProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last project in the ordered set where parentProjectId = &#63;.
	*
	* @param parentProjectId the parent project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching project
	* @throws com.liferay.timesheet.NoSuchProjectException if a matching project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Project findByParentProjectId_Last(
		java.lang.Long parentProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchProjectException;

	/**
	* Returns the last project in the ordered set where parentProjectId = &#63;.
	*
	* @param parentProjectId the parent project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching project, or <code>null</code> if a matching project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Project fetchByParentProjectId_Last(
		java.lang.Long parentProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the projects before and after the current project in the ordered set where parentProjectId = &#63;.
	*
	* @param projectId the primary key of the current project
	* @param parentProjectId the parent project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next project
	* @throws com.liferay.timesheet.NoSuchProjectException if a project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Project[] findByParentProjectId_PrevAndNext(
		long projectId, java.lang.Long parentProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchProjectException;

	/**
	* Removes all the projects where parentProjectId = &#63; from the database.
	*
	* @param parentProjectId the parent project ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByParentProjectId(java.lang.Long parentProjectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of projects where parentProjectId = &#63;.
	*
	* @param parentProjectId the parent project ID
	* @return the number of matching projects
	* @throws SystemException if a system exception occurred
	*/
	public int countByParentProjectId(java.lang.Long parentProjectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the project in the entity cache if it is enabled.
	*
	* @param project the project
	*/
	public void cacheResult(com.liferay.timesheet.model.Project project);

	/**
	* Caches the projects in the entity cache if it is enabled.
	*
	* @param projects the projects
	*/
	public void cacheResult(
		java.util.List<com.liferay.timesheet.model.Project> projects);

	/**
	* Creates a new project with the primary key. Does not add the project to the database.
	*
	* @param projectId the primary key for the new project
	* @return the new project
	*/
	public com.liferay.timesheet.model.Project create(long projectId);

	/**
	* Removes the project with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param projectId the primary key of the project
	* @return the project that was removed
	* @throws com.liferay.timesheet.NoSuchProjectException if a project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Project remove(long projectId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchProjectException;

	public com.liferay.timesheet.model.Project updateImpl(
		com.liferay.timesheet.model.Project project)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the project with the primary key or throws a {@link com.liferay.timesheet.NoSuchProjectException} if it could not be found.
	*
	* @param projectId the primary key of the project
	* @return the project
	* @throws com.liferay.timesheet.NoSuchProjectException if a project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Project findByPrimaryKey(long projectId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchProjectException;

	/**
	* Returns the project with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param projectId the primary key of the project
	* @return the project, or <code>null</code> if a project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Project fetchByPrimaryKey(long projectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the projects.
	*
	* @return the projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.timesheet.model.Project> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.ProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projects
	* @param end the upper bound of the range of projects (not inclusive)
	* @return the range of projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.timesheet.model.Project> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.ProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projects
	* @param end the upper bound of the range of projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.timesheet.model.Project> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the projects from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of projects.
	*
	* @return the number of projects
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}