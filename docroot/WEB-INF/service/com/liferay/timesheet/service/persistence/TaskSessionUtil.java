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
 * @author Istvan Sajtos, Zsolt Szabo
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
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
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
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static TaskSession update(TaskSession taskSession)
		throws SystemException {
		return getPersistence().update(taskSession);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static TaskSession update(TaskSession taskSession,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(taskSession, serviceContext);
	}

	/**
	* Returns all the task sessions where startTime &gt; &#63; and endTime = &#63;.
	*
	* @param startTime the start time
	* @param endTime the end time
	* @return the matching task sessions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.timesheet.model.TaskSession> findByS_E(
		java.util.Date startTime, java.util.Date endTime)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByS_E(startTime, endTime);
	}

	/**
	* Returns a range of all the task sessions where startTime &gt; &#63; and endTime = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.TaskSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param startTime the start time
	* @param endTime the end time
	* @param start the lower bound of the range of task sessions
	* @param end the upper bound of the range of task sessions (not inclusive)
	* @return the range of matching task sessions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.timesheet.model.TaskSession> findByS_E(
		java.util.Date startTime, java.util.Date endTime, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByS_E(startTime, endTime, start, end);
	}

	/**
	* Returns an ordered range of all the task sessions where startTime &gt; &#63; and endTime = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.TaskSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param startTime the start time
	* @param endTime the end time
	* @param start the lower bound of the range of task sessions
	* @param end the upper bound of the range of task sessions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching task sessions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.timesheet.model.TaskSession> findByS_E(
		java.util.Date startTime, java.util.Date endTime, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByS_E(startTime, endTime, start, end, orderByComparator);
	}

	/**
	* Returns the first task session in the ordered set where startTime &gt; &#63; and endTime = &#63;.
	*
	* @param startTime the start time
	* @param endTime the end time
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching task session
	* @throws com.liferay.timesheet.NoSuchTaskSessionException if a matching task session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.timesheet.model.TaskSession findByS_E_First(
		java.util.Date startTime, java.util.Date endTime,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchTaskSessionException {
		return getPersistence()
				   .findByS_E_First(startTime, endTime, orderByComparator);
	}

	/**
	* Returns the first task session in the ordered set where startTime &gt; &#63; and endTime = &#63;.
	*
	* @param startTime the start time
	* @param endTime the end time
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching task session, or <code>null</code> if a matching task session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.timesheet.model.TaskSession fetchByS_E_First(
		java.util.Date startTime, java.util.Date endTime,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByS_E_First(startTime, endTime, orderByComparator);
	}

	/**
	* Returns the last task session in the ordered set where startTime &gt; &#63; and endTime = &#63;.
	*
	* @param startTime the start time
	* @param endTime the end time
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching task session
	* @throws com.liferay.timesheet.NoSuchTaskSessionException if a matching task session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.timesheet.model.TaskSession findByS_E_Last(
		java.util.Date startTime, java.util.Date endTime,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchTaskSessionException {
		return getPersistence()
				   .findByS_E_Last(startTime, endTime, orderByComparator);
	}

	/**
	* Returns the last task session in the ordered set where startTime &gt; &#63; and endTime = &#63;.
	*
	* @param startTime the start time
	* @param endTime the end time
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching task session, or <code>null</code> if a matching task session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.timesheet.model.TaskSession fetchByS_E_Last(
		java.util.Date startTime, java.util.Date endTime,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByS_E_Last(startTime, endTime, orderByComparator);
	}

	/**
	* Returns the task sessions before and after the current task session in the ordered set where startTime &gt; &#63; and endTime = &#63;.
	*
	* @param taskSessionId the primary key of the current task session
	* @param startTime the start time
	* @param endTime the end time
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next task session
	* @throws com.liferay.timesheet.NoSuchTaskSessionException if a task session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.timesheet.model.TaskSession[] findByS_E_PrevAndNext(
		long taskSessionId, java.util.Date startTime, java.util.Date endTime,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchTaskSessionException {
		return getPersistence()
				   .findByS_E_PrevAndNext(taskSessionId, startTime, endTime,
			orderByComparator);
	}

	/**
	* Removes all the task sessions where startTime &gt; &#63; and endTime = &#63; from the database.
	*
	* @param startTime the start time
	* @param endTime the end time
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByS_E(java.util.Date startTime,
		java.util.Date endTime)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByS_E(startTime, endTime);
	}

	/**
	* Returns the number of task sessions where startTime &gt; &#63; and endTime = &#63;.
	*
	* @param startTime the start time
	* @param endTime the end time
	* @return the number of matching task sessions
	* @throws SystemException if a system exception occurred
	*/
	public static int countByS_E(java.util.Date startTime,
		java.util.Date endTime)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByS_E(startTime, endTime);
	}

	/**
	* Returns the task session where userId = &#63; and endTime = &#63; or throws a {@link com.liferay.timesheet.NoSuchTaskSessionException} if it could not be found.
	*
	* @param userId the user ID
	* @param endTime the end time
	* @return the matching task session
	* @throws com.liferay.timesheet.NoSuchTaskSessionException if a matching task session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.timesheet.model.TaskSession findByU_E(
		long userId, java.util.Date endTime)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchTaskSessionException {
		return getPersistence().findByU_E(userId, endTime);
	}

	/**
	* Returns the task session where userId = &#63; and endTime = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param endTime the end time
	* @return the matching task session, or <code>null</code> if a matching task session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.timesheet.model.TaskSession fetchByU_E(
		long userId, java.util.Date endTime)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByU_E(userId, endTime);
	}

	/**
	* Returns the task session where userId = &#63; and endTime = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param endTime the end time
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching task session, or <code>null</code> if a matching task session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.timesheet.model.TaskSession fetchByU_E(
		long userId, java.util.Date endTime, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByU_E(userId, endTime, retrieveFromCache);
	}

	/**
	* Removes the task session where userId = &#63; and endTime = &#63; from the database.
	*
	* @param userId the user ID
	* @param endTime the end time
	* @return the task session that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.timesheet.model.TaskSession removeByU_E(
		long userId, java.util.Date endTime)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchTaskSessionException {
		return getPersistence().removeByU_E(userId, endTime);
	}

	/**
	* Returns the number of task sessions where userId = &#63; and endTime = &#63;.
	*
	* @param userId the user ID
	* @param endTime the end time
	* @return the number of matching task sessions
	* @throws SystemException if a system exception occurred
	*/
	public static int countByU_E(long userId, java.util.Date endTime)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByU_E(userId, endTime);
	}

	/**
	* Returns all the task sessions where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching task sessions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.timesheet.model.TaskSession> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId);
	}

	/**
	* Returns a range of all the task sessions where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.TaskSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of task sessions
	* @param end the upper bound of the range of task sessions (not inclusive)
	* @return the range of matching task sessions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.timesheet.model.TaskSession> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	* Returns an ordered range of all the task sessions where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.TaskSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of task sessions
	* @param end the upper bound of the range of task sessions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching task sessions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.timesheet.model.TaskSession> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Returns the first task session in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching task session
	* @throws com.liferay.timesheet.NoSuchTaskSessionException if a matching task session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.timesheet.model.TaskSession findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchTaskSessionException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the first task session in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching task session, or <code>null</code> if a matching task session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.timesheet.model.TaskSession fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last task session in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching task session
	* @throws com.liferay.timesheet.NoSuchTaskSessionException if a matching task session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.timesheet.model.TaskSession findByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchTaskSessionException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the last task session in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching task session, or <code>null</code> if a matching task session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.timesheet.model.TaskSession fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

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
	public static com.liferay.timesheet.model.TaskSession[] findByUserId_PrevAndNext(
		long taskSessionId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchTaskSessionException {
		return getPersistence()
				   .findByUserId_PrevAndNext(taskSessionId, userId,
			orderByComparator);
	}

	/**
	* Removes all the task sessions where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Returns the number of task sessions where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching task sessions
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns all the task sessions where userId = &#63; and startTime &gt; &#63;.
	*
	* @param userId the user ID
	* @param startTime the start time
	* @return the matching task sessions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.timesheet.model.TaskSession> findByU_GtS(
		long userId, java.util.Date startTime)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_GtS(userId, startTime);
	}

	/**
	* Returns a range of all the task sessions where userId = &#63; and startTime &gt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.TaskSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param startTime the start time
	* @param start the lower bound of the range of task sessions
	* @param end the upper bound of the range of task sessions (not inclusive)
	* @return the range of matching task sessions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.timesheet.model.TaskSession> findByU_GtS(
		long userId, java.util.Date startTime, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_GtS(userId, startTime, start, end);
	}

	/**
	* Returns an ordered range of all the task sessions where userId = &#63; and startTime &gt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.TaskSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public static java.util.List<com.liferay.timesheet.model.TaskSession> findByU_GtS(
		long userId, java.util.Date startTime, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_GtS(userId, startTime, start, end, orderByComparator);
	}

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
	public static com.liferay.timesheet.model.TaskSession findByU_GtS_First(
		long userId, java.util.Date startTime,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchTaskSessionException {
		return getPersistence()
				   .findByU_GtS_First(userId, startTime, orderByComparator);
	}

	/**
	* Returns the first task session in the ordered set where userId = &#63; and startTime &gt; &#63;.
	*
	* @param userId the user ID
	* @param startTime the start time
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching task session, or <code>null</code> if a matching task session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.timesheet.model.TaskSession fetchByU_GtS_First(
		long userId, java.util.Date startTime,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByU_GtS_First(userId, startTime, orderByComparator);
	}

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
	public static com.liferay.timesheet.model.TaskSession findByU_GtS_Last(
		long userId, java.util.Date startTime,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchTaskSessionException {
		return getPersistence()
				   .findByU_GtS_Last(userId, startTime, orderByComparator);
	}

	/**
	* Returns the last task session in the ordered set where userId = &#63; and startTime &gt; &#63;.
	*
	* @param userId the user ID
	* @param startTime the start time
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching task session, or <code>null</code> if a matching task session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.timesheet.model.TaskSession fetchByU_GtS_Last(
		long userId, java.util.Date startTime,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByU_GtS_Last(userId, startTime, orderByComparator);
	}

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
	public static com.liferay.timesheet.model.TaskSession[] findByU_GtS_PrevAndNext(
		long taskSessionId, long userId, java.util.Date startTime,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchTaskSessionException {
		return getPersistence()
				   .findByU_GtS_PrevAndNext(taskSessionId, userId, startTime,
			orderByComparator);
	}

	/**
	* Removes all the task sessions where userId = &#63; and startTime &gt; &#63; from the database.
	*
	* @param userId the user ID
	* @param startTime the start time
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByU_GtS(long userId, java.util.Date startTime)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByU_GtS(userId, startTime);
	}

	/**
	* Returns the number of task sessions where userId = &#63; and startTime &gt; &#63;.
	*
	* @param userId the user ID
	* @param startTime the start time
	* @return the number of matching task sessions
	* @throws SystemException if a system exception occurred
	*/
	public static int countByU_GtS(long userId, java.util.Date startTime)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByU_GtS(userId, startTime);
	}

	/**
	* Returns all the task sessions where userId = &#63; and taskId = &#63; and startTime &gt; &#63;.
	*
	* @param userId the user ID
	* @param taskId the task ID
	* @param startTime the start time
	* @return the matching task sessions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.timesheet.model.TaskSession> findByU_T_GtS(
		long userId, long taskId, java.util.Date startTime)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_T_GtS(userId, taskId, startTime);
	}

	/**
	* Returns a range of all the task sessions where userId = &#63; and taskId = &#63; and startTime &gt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.TaskSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param taskId the task ID
	* @param startTime the start time
	* @param start the lower bound of the range of task sessions
	* @param end the upper bound of the range of task sessions (not inclusive)
	* @return the range of matching task sessions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.timesheet.model.TaskSession> findByU_T_GtS(
		long userId, long taskId, java.util.Date startTime, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_T_GtS(userId, taskId, startTime, start, end);
	}

	/**
	* Returns an ordered range of all the task sessions where userId = &#63; and taskId = &#63; and startTime &gt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.TaskSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param taskId the task ID
	* @param startTime the start time
	* @param start the lower bound of the range of task sessions
	* @param end the upper bound of the range of task sessions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching task sessions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.timesheet.model.TaskSession> findByU_T_GtS(
		long userId, long taskId, java.util.Date startTime, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_T_GtS(userId, taskId, startTime, start, end,
			orderByComparator);
	}

	/**
	* Returns the first task session in the ordered set where userId = &#63; and taskId = &#63; and startTime &gt; &#63;.
	*
	* @param userId the user ID
	* @param taskId the task ID
	* @param startTime the start time
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching task session
	* @throws com.liferay.timesheet.NoSuchTaskSessionException if a matching task session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.timesheet.model.TaskSession findByU_T_GtS_First(
		long userId, long taskId, java.util.Date startTime,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchTaskSessionException {
		return getPersistence()
				   .findByU_T_GtS_First(userId, taskId, startTime,
			orderByComparator);
	}

	/**
	* Returns the first task session in the ordered set where userId = &#63; and taskId = &#63; and startTime &gt; &#63;.
	*
	* @param userId the user ID
	* @param taskId the task ID
	* @param startTime the start time
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching task session, or <code>null</code> if a matching task session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.timesheet.model.TaskSession fetchByU_T_GtS_First(
		long userId, long taskId, java.util.Date startTime,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByU_T_GtS_First(userId, taskId, startTime,
			orderByComparator);
	}

	/**
	* Returns the last task session in the ordered set where userId = &#63; and taskId = &#63; and startTime &gt; &#63;.
	*
	* @param userId the user ID
	* @param taskId the task ID
	* @param startTime the start time
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching task session
	* @throws com.liferay.timesheet.NoSuchTaskSessionException if a matching task session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.timesheet.model.TaskSession findByU_T_GtS_Last(
		long userId, long taskId, java.util.Date startTime,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchTaskSessionException {
		return getPersistence()
				   .findByU_T_GtS_Last(userId, taskId, startTime,
			orderByComparator);
	}

	/**
	* Returns the last task session in the ordered set where userId = &#63; and taskId = &#63; and startTime &gt; &#63;.
	*
	* @param userId the user ID
	* @param taskId the task ID
	* @param startTime the start time
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching task session, or <code>null</code> if a matching task session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.timesheet.model.TaskSession fetchByU_T_GtS_Last(
		long userId, long taskId, java.util.Date startTime,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByU_T_GtS_Last(userId, taskId, startTime,
			orderByComparator);
	}

	/**
	* Returns the task sessions before and after the current task session in the ordered set where userId = &#63; and taskId = &#63; and startTime &gt; &#63;.
	*
	* @param taskSessionId the primary key of the current task session
	* @param userId the user ID
	* @param taskId the task ID
	* @param startTime the start time
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next task session
	* @throws com.liferay.timesheet.NoSuchTaskSessionException if a task session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.timesheet.model.TaskSession[] findByU_T_GtS_PrevAndNext(
		long taskSessionId, long userId, long taskId, java.util.Date startTime,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchTaskSessionException {
		return getPersistence()
				   .findByU_T_GtS_PrevAndNext(taskSessionId, userId, taskId,
			startTime, orderByComparator);
	}

	/**
	* Removes all the task sessions where userId = &#63; and taskId = &#63; and startTime &gt; &#63; from the database.
	*
	* @param userId the user ID
	* @param taskId the task ID
	* @param startTime the start time
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByU_T_GtS(long userId, long taskId,
		java.util.Date startTime)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByU_T_GtS(userId, taskId, startTime);
	}

	/**
	* Returns the number of task sessions where userId = &#63; and taskId = &#63; and startTime &gt; &#63;.
	*
	* @param userId the user ID
	* @param taskId the task ID
	* @param startTime the start time
	* @return the number of matching task sessions
	* @throws SystemException if a system exception occurred
	*/
	public static int countByU_T_GtS(long userId, long taskId,
		java.util.Date startTime)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByU_T_GtS(userId, taskId, startTime);
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
		com.liferay.timesheet.model.TaskSession taskSession)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(taskSession);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.TaskSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.TaskSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(TaskSessionPersistence persistence) {
	}

	private static TaskSessionPersistence _persistence;
}