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

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.timesheet.NoSuchTaskException;
import com.liferay.timesheet.model.Task;
import com.liferay.timesheet.model.impl.TaskImpl;
import com.liferay.timesheet.model.impl.TaskModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the task service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Istvan Sajtos, Zsolt Szabo
 * @see TaskPersistence
 * @see TaskUtil
 * @generated
 */
public class TaskPersistenceImpl extends BasePersistenceImpl<Task>
	implements TaskPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TaskUtil} to access the task persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TaskImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TaskModelImpl.ENTITY_CACHE_ENABLED,
			TaskModelImpl.FINDER_CACHE_ENABLED, TaskImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TaskModelImpl.ENTITY_CACHE_ENABLED,
			TaskModelImpl.FINDER_CACHE_ENABLED, TaskImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TaskModelImpl.ENTITY_CACHE_ENABLED,
			TaskModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(TaskModelImpl.ENTITY_CACHE_ENABLED,
			TaskModelImpl.FINDER_CACHE_ENABLED, TaskImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(TaskModelImpl.ENTITY_CACHE_ENABLED,
			TaskModelImpl.FINDER_CACHE_ENABLED, TaskImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			TaskModelImpl.GROUPID_COLUMN_BITMASK |
			TaskModelImpl.TASKNAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(TaskModelImpl.ENTITY_CACHE_ENABLED,
			TaskModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the tasks where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Task> findByGroupId(long groupId) throws SystemException {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tasks where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.TaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of tasks
	 * @param end the upper bound of the range of tasks (not inclusive)
	 * @return the range of matching tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Task> findByGroupId(long groupId, int start, int end)
		throws SystemException {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.TaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of tasks
	 * @param end the upper bound of the range of tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Task> findByGroupId(long groupId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<Task> list = (List<Task>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Task task : list) {
				if ((groupId != task.getGroupId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TASK_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TaskModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<Task>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Task>(list);
				}
				else {
					list = (List<Task>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first task in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching task
	 * @throws com.liferay.timesheet.NoSuchTaskException if a matching task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Task findByGroupId_First(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskException, SystemException {
		Task task = fetchByGroupId_First(groupId, orderByComparator);

		if (task != null) {
			return task;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTaskException(msg.toString());
	}

	/**
	 * Returns the first task in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching task, or <code>null</code> if a matching task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Task fetchByGroupId_First(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Task> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last task in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching task
	 * @throws com.liferay.timesheet.NoSuchTaskException if a matching task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Task findByGroupId_Last(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskException, SystemException {
		Task task = fetchByGroupId_Last(groupId, orderByComparator);

		if (task != null) {
			return task;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTaskException(msg.toString());
	}

	/**
	 * Returns the last task in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching task, or <code>null</code> if a matching task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Task fetchByGroupId_Last(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<Task> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the tasks before and after the current task in the ordered set where groupId = &#63;.
	 *
	 * @param taskId the primary key of the current task
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next task
	 * @throws com.liferay.timesheet.NoSuchTaskException if a task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Task[] findByGroupId_PrevAndNext(long taskId, long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskException, SystemException {
		Task task = findByPrimaryKey(taskId);

		Session session = null;

		try {
			session = openSession();

			Task[] array = new TaskImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, task, groupId,
					orderByComparator, true);

			array[1] = task;

			array[2] = getByGroupId_PrevAndNext(session, task, groupId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Task getByGroupId_PrevAndNext(Session session, Task task,
		long groupId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TASK_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(TaskModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(task);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Task> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the tasks where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByGroupId(long groupId) throws SystemException {
		for (Task task : findByGroupId(groupId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(task);
		}
	}

	/**
	 * Returns the number of tasks where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByGroupId(long groupId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TASK_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "task.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PROJECTID =
		new FinderPath(TaskModelImpl.ENTITY_CACHE_ENABLED,
			TaskModelImpl.FINDER_CACHE_ENABLED, TaskImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByProjectId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROJECTID =
		new FinderPath(TaskModelImpl.ENTITY_CACHE_ENABLED,
			TaskModelImpl.FINDER_CACHE_ENABLED, TaskImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByProjectId",
			new String[] { Long.class.getName() },
			TaskModelImpl.PROJECTID_COLUMN_BITMASK |
			TaskModelImpl.TASKNAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PROJECTID = new FinderPath(TaskModelImpl.ENTITY_CACHE_ENABLED,
			TaskModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByProjectId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the tasks where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @return the matching tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Task> findByProjectId(long projectId) throws SystemException {
		return findByProjectId(projectId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the tasks where projectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.TaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param start the lower bound of the range of tasks
	 * @param end the upper bound of the range of tasks (not inclusive)
	 * @return the range of matching tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Task> findByProjectId(long projectId, int start, int end)
		throws SystemException {
		return findByProjectId(projectId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks where projectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.TaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param start the lower bound of the range of tasks
	 * @param end the upper bound of the range of tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Task> findByProjectId(long projectId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROJECTID;
			finderArgs = new Object[] { projectId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PROJECTID;
			finderArgs = new Object[] { projectId, start, end, orderByComparator };
		}

		List<Task> list = (List<Task>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Task task : list) {
				if ((projectId != task.getProjectId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TASK_WHERE);

			query.append(_FINDER_COLUMN_PROJECTID_PROJECTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TaskModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(projectId);

				if (!pagination) {
					list = (List<Task>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Task>(list);
				}
				else {
					list = (List<Task>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first task in the ordered set where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching task
	 * @throws com.liferay.timesheet.NoSuchTaskException if a matching task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Task findByProjectId_First(long projectId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskException, SystemException {
		Task task = fetchByProjectId_First(projectId, orderByComparator);

		if (task != null) {
			return task;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("projectId=");
		msg.append(projectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTaskException(msg.toString());
	}

	/**
	 * Returns the first task in the ordered set where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching task, or <code>null</code> if a matching task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Task fetchByProjectId_First(long projectId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Task> list = findByProjectId(projectId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last task in the ordered set where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching task
	 * @throws com.liferay.timesheet.NoSuchTaskException if a matching task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Task findByProjectId_Last(long projectId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskException, SystemException {
		Task task = fetchByProjectId_Last(projectId, orderByComparator);

		if (task != null) {
			return task;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("projectId=");
		msg.append(projectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTaskException(msg.toString());
	}

	/**
	 * Returns the last task in the ordered set where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching task, or <code>null</code> if a matching task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Task fetchByProjectId_Last(long projectId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByProjectId(projectId);

		if (count == 0) {
			return null;
		}

		List<Task> list = findByProjectId(projectId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the tasks before and after the current task in the ordered set where projectId = &#63;.
	 *
	 * @param taskId the primary key of the current task
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next task
	 * @throws com.liferay.timesheet.NoSuchTaskException if a task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Task[] findByProjectId_PrevAndNext(long taskId, long projectId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskException, SystemException {
		Task task = findByPrimaryKey(taskId);

		Session session = null;

		try {
			session = openSession();

			Task[] array = new TaskImpl[3];

			array[0] = getByProjectId_PrevAndNext(session, task, projectId,
					orderByComparator, true);

			array[1] = task;

			array[2] = getByProjectId_PrevAndNext(session, task, projectId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Task getByProjectId_PrevAndNext(Session session, Task task,
		long projectId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TASK_WHERE);

		query.append(_FINDER_COLUMN_PROJECTID_PROJECTID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(TaskModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(projectId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(task);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Task> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the tasks where projectId = &#63; from the database.
	 *
	 * @param projectId the project ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByProjectId(long projectId) throws SystemException {
		for (Task task : findByProjectId(projectId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(task);
		}
	}

	/**
	 * Returns the number of tasks where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @return the number of matching tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByProjectId(long projectId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PROJECTID;

		Object[] finderArgs = new Object[] { projectId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TASK_WHERE);

			query.append(_FINDER_COLUMN_PROJECTID_PROJECTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(projectId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_PROJECTID_PROJECTID_2 = "task.projectId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TASKTYPE = new FinderPath(TaskModelImpl.ENTITY_CACHE_ENABLED,
			TaskModelImpl.FINDER_CACHE_ENABLED, TaskImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTaskType",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TASKTYPE =
		new FinderPath(TaskModelImpl.ENTITY_CACHE_ENABLED,
			TaskModelImpl.FINDER_CACHE_ENABLED, TaskImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTaskType",
			new String[] { Integer.class.getName() },
			TaskModelImpl.TASKTYPE_COLUMN_BITMASK |
			TaskModelImpl.TASKNAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TASKTYPE = new FinderPath(TaskModelImpl.ENTITY_CACHE_ENABLED,
			TaskModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTaskType",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the tasks where taskType = &#63;.
	 *
	 * @param taskType the task type
	 * @return the matching tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Task> findByTaskType(int taskType) throws SystemException {
		return findByTaskType(taskType, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the tasks where taskType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.TaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param taskType the task type
	 * @param start the lower bound of the range of tasks
	 * @param end the upper bound of the range of tasks (not inclusive)
	 * @return the range of matching tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Task> findByTaskType(int taskType, int start, int end)
		throws SystemException {
		return findByTaskType(taskType, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks where taskType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.TaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param taskType the task type
	 * @param start the lower bound of the range of tasks
	 * @param end the upper bound of the range of tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Task> findByTaskType(int taskType, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TASKTYPE;
			finderArgs = new Object[] { taskType };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TASKTYPE;
			finderArgs = new Object[] { taskType, start, end, orderByComparator };
		}

		List<Task> list = (List<Task>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Task task : list) {
				if ((taskType != task.getTaskType())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TASK_WHERE);

			query.append(_FINDER_COLUMN_TASKTYPE_TASKTYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TaskModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(taskType);

				if (!pagination) {
					list = (List<Task>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Task>(list);
				}
				else {
					list = (List<Task>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first task in the ordered set where taskType = &#63;.
	 *
	 * @param taskType the task type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching task
	 * @throws com.liferay.timesheet.NoSuchTaskException if a matching task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Task findByTaskType_First(int taskType,
		OrderByComparator orderByComparator)
		throws NoSuchTaskException, SystemException {
		Task task = fetchByTaskType_First(taskType, orderByComparator);

		if (task != null) {
			return task;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("taskType=");
		msg.append(taskType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTaskException(msg.toString());
	}

	/**
	 * Returns the first task in the ordered set where taskType = &#63;.
	 *
	 * @param taskType the task type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching task, or <code>null</code> if a matching task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Task fetchByTaskType_First(int taskType,
		OrderByComparator orderByComparator) throws SystemException {
		List<Task> list = findByTaskType(taskType, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last task in the ordered set where taskType = &#63;.
	 *
	 * @param taskType the task type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching task
	 * @throws com.liferay.timesheet.NoSuchTaskException if a matching task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Task findByTaskType_Last(int taskType,
		OrderByComparator orderByComparator)
		throws NoSuchTaskException, SystemException {
		Task task = fetchByTaskType_Last(taskType, orderByComparator);

		if (task != null) {
			return task;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("taskType=");
		msg.append(taskType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTaskException(msg.toString());
	}

	/**
	 * Returns the last task in the ordered set where taskType = &#63;.
	 *
	 * @param taskType the task type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching task, or <code>null</code> if a matching task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Task fetchByTaskType_Last(int taskType,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByTaskType(taskType);

		if (count == 0) {
			return null;
		}

		List<Task> list = findByTaskType(taskType, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the tasks before and after the current task in the ordered set where taskType = &#63;.
	 *
	 * @param taskId the primary key of the current task
	 * @param taskType the task type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next task
	 * @throws com.liferay.timesheet.NoSuchTaskException if a task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Task[] findByTaskType_PrevAndNext(long taskId, int taskType,
		OrderByComparator orderByComparator)
		throws NoSuchTaskException, SystemException {
		Task task = findByPrimaryKey(taskId);

		Session session = null;

		try {
			session = openSession();

			Task[] array = new TaskImpl[3];

			array[0] = getByTaskType_PrevAndNext(session, task, taskType,
					orderByComparator, true);

			array[1] = task;

			array[2] = getByTaskType_PrevAndNext(session, task, taskType,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Task getByTaskType_PrevAndNext(Session session, Task task,
		int taskType, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TASK_WHERE);

		query.append(_FINDER_COLUMN_TASKTYPE_TASKTYPE_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(TaskModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(taskType);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(task);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Task> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the tasks where taskType = &#63; from the database.
	 *
	 * @param taskType the task type
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByTaskType(int taskType) throws SystemException {
		for (Task task : findByTaskType(taskType, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(task);
		}
	}

	/**
	 * Returns the number of tasks where taskType = &#63;.
	 *
	 * @param taskType the task type
	 * @return the number of matching tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByTaskType(int taskType) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_TASKTYPE;

		Object[] finderArgs = new Object[] { taskType };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TASK_WHERE);

			query.append(_FINDER_COLUMN_TASKTYPE_TASKTYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(taskType);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_TASKTYPE_TASKTYPE_2 = "task.taskType = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_G_TT = new FinderPath(TaskModelImpl.ENTITY_CACHE_ENABLED,
			TaskModelImpl.FINDER_CACHE_ENABLED, TaskImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_G_TT",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_G_TT =
		new FinderPath(TaskModelImpl.ENTITY_CACHE_ENABLED,
			TaskModelImpl.FINDER_CACHE_ENABLED, TaskImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_G_TT",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			TaskModelImpl.COMPANYID_COLUMN_BITMASK |
			TaskModelImpl.GROUPID_COLUMN_BITMASK |
			TaskModelImpl.TASKTYPE_COLUMN_BITMASK |
			TaskModelImpl.TASKNAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_G_TT = new FinderPath(TaskModelImpl.ENTITY_CACHE_ENABLED,
			TaskModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_G_TT",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the tasks where companyId = &#63; and groupId = &#63; and taskType = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param taskType the task type
	 * @return the matching tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Task> findByC_G_TT(long companyId, long groupId, int taskType)
		throws SystemException {
		return findByC_G_TT(companyId, groupId, taskType, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tasks where companyId = &#63; and groupId = &#63; and taskType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.TaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param taskType the task type
	 * @param start the lower bound of the range of tasks
	 * @param end the upper bound of the range of tasks (not inclusive)
	 * @return the range of matching tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Task> findByC_G_TT(long companyId, long groupId, int taskType,
		int start, int end) throws SystemException {
		return findByC_G_TT(companyId, groupId, taskType, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks where companyId = &#63; and groupId = &#63; and taskType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.TaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param taskType the task type
	 * @param start the lower bound of the range of tasks
	 * @param end the upper bound of the range of tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Task> findByC_G_TT(long companyId, long groupId, int taskType,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_G_TT;
			finderArgs = new Object[] { companyId, groupId, taskType };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_G_TT;
			finderArgs = new Object[] {
					companyId, groupId, taskType,
					
					start, end, orderByComparator
				};
		}

		List<Task> list = (List<Task>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Task task : list) {
				if ((companyId != task.getCompanyId()) ||
						(groupId != task.getGroupId()) ||
						(taskType != task.getTaskType())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_TASK_WHERE);

			query.append(_FINDER_COLUMN_C_G_TT_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_G_TT_GROUPID_2);

			query.append(_FINDER_COLUMN_C_G_TT_TASKTYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TaskModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(groupId);

				qPos.add(taskType);

				if (!pagination) {
					list = (List<Task>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Task>(list);
				}
				else {
					list = (List<Task>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first task in the ordered set where companyId = &#63; and groupId = &#63; and taskType = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param taskType the task type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching task
	 * @throws com.liferay.timesheet.NoSuchTaskException if a matching task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Task findByC_G_TT_First(long companyId, long groupId, int taskType,
		OrderByComparator orderByComparator)
		throws NoSuchTaskException, SystemException {
		Task task = fetchByC_G_TT_First(companyId, groupId, taskType,
				orderByComparator);

		if (task != null) {
			return task;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", taskType=");
		msg.append(taskType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTaskException(msg.toString());
	}

	/**
	 * Returns the first task in the ordered set where companyId = &#63; and groupId = &#63; and taskType = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param taskType the task type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching task, or <code>null</code> if a matching task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Task fetchByC_G_TT_First(long companyId, long groupId, int taskType,
		OrderByComparator orderByComparator) throws SystemException {
		List<Task> list = findByC_G_TT(companyId, groupId, taskType, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last task in the ordered set where companyId = &#63; and groupId = &#63; and taskType = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param taskType the task type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching task
	 * @throws com.liferay.timesheet.NoSuchTaskException if a matching task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Task findByC_G_TT_Last(long companyId, long groupId, int taskType,
		OrderByComparator orderByComparator)
		throws NoSuchTaskException, SystemException {
		Task task = fetchByC_G_TT_Last(companyId, groupId, taskType,
				orderByComparator);

		if (task != null) {
			return task;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", taskType=");
		msg.append(taskType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTaskException(msg.toString());
	}

	/**
	 * Returns the last task in the ordered set where companyId = &#63; and groupId = &#63; and taskType = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param taskType the task type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching task, or <code>null</code> if a matching task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Task fetchByC_G_TT_Last(long companyId, long groupId, int taskType,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByC_G_TT(companyId, groupId, taskType);

		if (count == 0) {
			return null;
		}

		List<Task> list = findByC_G_TT(companyId, groupId, taskType, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the tasks before and after the current task in the ordered set where companyId = &#63; and groupId = &#63; and taskType = &#63;.
	 *
	 * @param taskId the primary key of the current task
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param taskType the task type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next task
	 * @throws com.liferay.timesheet.NoSuchTaskException if a task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Task[] findByC_G_TT_PrevAndNext(long taskId, long companyId,
		long groupId, int taskType, OrderByComparator orderByComparator)
		throws NoSuchTaskException, SystemException {
		Task task = findByPrimaryKey(taskId);

		Session session = null;

		try {
			session = openSession();

			Task[] array = new TaskImpl[3];

			array[0] = getByC_G_TT_PrevAndNext(session, task, companyId,
					groupId, taskType, orderByComparator, true);

			array[1] = task;

			array[2] = getByC_G_TT_PrevAndNext(session, task, companyId,
					groupId, taskType, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Task getByC_G_TT_PrevAndNext(Session session, Task task,
		long companyId, long groupId, int taskType,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TASK_WHERE);

		query.append(_FINDER_COLUMN_C_G_TT_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_G_TT_GROUPID_2);

		query.append(_FINDER_COLUMN_C_G_TT_TASKTYPE_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(TaskModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(groupId);

		qPos.add(taskType);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(task);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Task> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the tasks where companyId = &#63; and groupId = &#63; and taskType = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param taskType the task type
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByC_G_TT(long companyId, long groupId, int taskType)
		throws SystemException {
		for (Task task : findByC_G_TT(companyId, groupId, taskType,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(task);
		}
	}

	/**
	 * Returns the number of tasks where companyId = &#63; and groupId = &#63; and taskType = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param taskType the task type
	 * @return the number of matching tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByC_G_TT(long companyId, long groupId, int taskType)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_G_TT;

		Object[] finderArgs = new Object[] { companyId, groupId, taskType };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_TASK_WHERE);

			query.append(_FINDER_COLUMN_C_G_TT_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_G_TT_GROUPID_2);

			query.append(_FINDER_COLUMN_C_G_TT_TASKTYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(groupId);

				qPos.add(taskType);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_C_G_TT_COMPANYID_2 = "task.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_G_TT_GROUPID_2 = "task.groupId = ? AND ";
	private static final String _FINDER_COLUMN_C_G_TT_TASKTYPE_2 = "task.taskType = ?";

	public TaskPersistenceImpl() {
		setModelClass(Task.class);
	}

	/**
	 * Caches the task in the entity cache if it is enabled.
	 *
	 * @param task the task
	 */
	@Override
	public void cacheResult(Task task) {
		EntityCacheUtil.putResult(TaskModelImpl.ENTITY_CACHE_ENABLED,
			TaskImpl.class, task.getPrimaryKey(), task);

		task.resetOriginalValues();
	}

	/**
	 * Caches the tasks in the entity cache if it is enabled.
	 *
	 * @param tasks the tasks
	 */
	@Override
	public void cacheResult(List<Task> tasks) {
		for (Task task : tasks) {
			if (EntityCacheUtil.getResult(TaskModelImpl.ENTITY_CACHE_ENABLED,
						TaskImpl.class, task.getPrimaryKey()) == null) {
				cacheResult(task);
			}
			else {
				task.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all tasks.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(TaskImpl.class.getName());
		}

		EntityCacheUtil.clearCache(TaskImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the task.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Task task) {
		EntityCacheUtil.removeResult(TaskModelImpl.ENTITY_CACHE_ENABLED,
			TaskImpl.class, task.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Task> tasks) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Task task : tasks) {
			EntityCacheUtil.removeResult(TaskModelImpl.ENTITY_CACHE_ENABLED,
				TaskImpl.class, task.getPrimaryKey());
		}
	}

	/**
	 * Creates a new task with the primary key. Does not add the task to the database.
	 *
	 * @param taskId the primary key for the new task
	 * @return the new task
	 */
	@Override
	public Task create(long taskId) {
		Task task = new TaskImpl();

		task.setNew(true);
		task.setPrimaryKey(taskId);

		return task;
	}

	/**
	 * Removes the task with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param taskId the primary key of the task
	 * @return the task that was removed
	 * @throws com.liferay.timesheet.NoSuchTaskException if a task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Task remove(long taskId) throws NoSuchTaskException, SystemException {
		return remove((Serializable)taskId);
	}

	/**
	 * Removes the task with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the task
	 * @return the task that was removed
	 * @throws com.liferay.timesheet.NoSuchTaskException if a task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Task remove(Serializable primaryKey)
		throws NoSuchTaskException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Task task = (Task)session.get(TaskImpl.class, primaryKey);

			if (task == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTaskException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(task);
		}
		catch (NoSuchTaskException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Task removeImpl(Task task) throws SystemException {
		task = toUnwrappedModel(task);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(task)) {
				task = (Task)session.get(TaskImpl.class, task.getPrimaryKeyObj());
			}

			if (task != null) {
				session.delete(task);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (task != null) {
			clearCache(task);
		}

		return task;
	}

	@Override
	public Task updateImpl(com.liferay.timesheet.model.Task task)
		throws SystemException {
		task = toUnwrappedModel(task);

		boolean isNew = task.isNew();

		TaskModelImpl taskModelImpl = (TaskModelImpl)task;

		Session session = null;

		try {
			session = openSession();

			if (task.isNew()) {
				session.save(task);

				task.setNew(false);
			}
			else {
				session.merge(task);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !TaskModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((taskModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { taskModelImpl.getOriginalGroupId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { taskModelImpl.getGroupId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((taskModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROJECTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						taskModelImpl.getOriginalProjectId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROJECTID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROJECTID,
					args);

				args = new Object[] { taskModelImpl.getProjectId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROJECTID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROJECTID,
					args);
			}

			if ((taskModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TASKTYPE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { taskModelImpl.getOriginalTaskType() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TASKTYPE, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TASKTYPE,
					args);

				args = new Object[] { taskModelImpl.getTaskType() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TASKTYPE, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TASKTYPE,
					args);
			}

			if ((taskModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_G_TT.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						taskModelImpl.getOriginalCompanyId(),
						taskModelImpl.getOriginalGroupId(),
						taskModelImpl.getOriginalTaskType()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_G_TT, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_G_TT,
					args);

				args = new Object[] {
						taskModelImpl.getCompanyId(), taskModelImpl.getGroupId(),
						taskModelImpl.getTaskType()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_G_TT, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_G_TT,
					args);
			}
		}

		EntityCacheUtil.putResult(TaskModelImpl.ENTITY_CACHE_ENABLED,
			TaskImpl.class, task.getPrimaryKey(), task);

		return task;
	}

	protected Task toUnwrappedModel(Task task) {
		if (task instanceof TaskImpl) {
			return task;
		}

		TaskImpl taskImpl = new TaskImpl();

		taskImpl.setNew(task.isNew());
		taskImpl.setPrimaryKey(task.getPrimaryKey());

		taskImpl.setTaskId(task.getTaskId());
		taskImpl.setGroupId(task.getGroupId());
		taskImpl.setCompanyId(task.getCompanyId());
		taskImpl.setUserId(task.getUserId());
		taskImpl.setUserName(task.getUserName());
		taskImpl.setCreateDate(task.getCreateDate());
		taskImpl.setModifiedDate(task.getModifiedDate());
		taskImpl.setDescription(task.getDescription());
		taskImpl.setProjectId(task.getProjectId());
		taskImpl.setTaskName(task.getTaskName());
		taskImpl.setTaskType(task.getTaskType());

		return taskImpl;
	}

	/**
	 * Returns the task with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the task
	 * @return the task
	 * @throws com.liferay.timesheet.NoSuchTaskException if a task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Task findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTaskException, SystemException {
		Task task = fetchByPrimaryKey(primaryKey);

		if (task == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTaskException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return task;
	}

	/**
	 * Returns the task with the primary key or throws a {@link com.liferay.timesheet.NoSuchTaskException} if it could not be found.
	 *
	 * @param taskId the primary key of the task
	 * @return the task
	 * @throws com.liferay.timesheet.NoSuchTaskException if a task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Task findByPrimaryKey(long taskId)
		throws NoSuchTaskException, SystemException {
		return findByPrimaryKey((Serializable)taskId);
	}

	/**
	 * Returns the task with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the task
	 * @return the task, or <code>null</code> if a task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Task fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		Task task = (Task)EntityCacheUtil.getResult(TaskModelImpl.ENTITY_CACHE_ENABLED,
				TaskImpl.class, primaryKey);

		if (task == _nullTask) {
			return null;
		}

		if (task == null) {
			Session session = null;

			try {
				session = openSession();

				task = (Task)session.get(TaskImpl.class, primaryKey);

				if (task != null) {
					cacheResult(task);
				}
				else {
					EntityCacheUtil.putResult(TaskModelImpl.ENTITY_CACHE_ENABLED,
						TaskImpl.class, primaryKey, _nullTask);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(TaskModelImpl.ENTITY_CACHE_ENABLED,
					TaskImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return task;
	}

	/**
	 * Returns the task with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param taskId the primary key of the task
	 * @return the task, or <code>null</code> if a task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Task fetchByPrimaryKey(long taskId) throws SystemException {
		return fetchByPrimaryKey((Serializable)taskId);
	}

	/**
	 * Returns all the tasks.
	 *
	 * @return the tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Task> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tasks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.TaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of tasks
	 * @param end the upper bound of the range of tasks (not inclusive)
	 * @return the range of tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Task> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.TaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of tasks
	 * @param end the upper bound of the range of tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Task> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<Task> list = (List<Task>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_TASK);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TASK;

				if (pagination) {
					sql = sql.concat(TaskModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Task>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Task>(list);
				}
				else {
					list = (List<Task>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the tasks from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (Task task : findAll()) {
			remove(task);
		}
	}

	/**
	 * Returns the number of tasks.
	 *
	 * @return the number of tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TASK);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the task persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.timesheet.model.Task")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Task>> listenersList = new ArrayList<ModelListener<Task>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Task>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(TaskImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_TASK = "SELECT task FROM Task task";
	private static final String _SQL_SELECT_TASK_WHERE = "SELECT task FROM Task task WHERE ";
	private static final String _SQL_COUNT_TASK = "SELECT COUNT(task) FROM Task task";
	private static final String _SQL_COUNT_TASK_WHERE = "SELECT COUNT(task) FROM Task task WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "task.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Task exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Task exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(TaskPersistenceImpl.class);
	private static Task _nullTask = new TaskImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Task> toCacheModel() {
				return _nullTaskCacheModel;
			}
		};

	private static CacheModel<Task> _nullTaskCacheModel = new CacheModel<Task>() {
			@Override
			public Task toEntityModel() {
				return _nullTask;
			}
		};
}