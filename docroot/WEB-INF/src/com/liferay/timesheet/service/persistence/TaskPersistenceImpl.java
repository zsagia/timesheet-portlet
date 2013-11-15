package com.liferay.timesheet.service.persistence;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
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
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
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
 * @author Adorjan
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
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(TaskModelImpl.ENTITY_CACHE_ENABLED,
            TaskModelImpl.FINDER_CACHE_ENABLED, TaskImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
        new FinderPath(TaskModelImpl.ENTITY_CACHE_ENABLED,
            TaskModelImpl.FINDER_CACHE_ENABLED, TaskImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
            new String[] { Long.class.getName() },
            TaskModelImpl.USERID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(TaskModelImpl.ENTITY_CACHE_ENABLED,
            TaskModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TaskModelImpl.ENTITY_CACHE_ENABLED,
            TaskModelImpl.FINDER_CACHE_ENABLED, TaskImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TaskModelImpl.ENTITY_CACHE_ENABLED,
            TaskModelImpl.FINDER_CACHE_ENABLED, TaskImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TaskModelImpl.ENTITY_CACHE_ENABLED,
            TaskModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_TASK = "SELECT task FROM Task task";
    private static final String _SQL_SELECT_TASK_WHERE = "SELECT task FROM Task task WHERE ";
    private static final String _SQL_COUNT_TASK = "SELECT COUNT(task) FROM Task task";
    private static final String _SQL_COUNT_TASK_WHERE = "SELECT COUNT(task) FROM Task task WHERE ";
    private static final String _FINDER_COLUMN_USERID_USERID_2 = "task.userId = ?";
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
            public Task toEntityModel() {
                return _nullTask;
            }
        };

    @BeanReference(type = TaskPersistence.class)
    protected TaskPersistence taskPersistence;
    @BeanReference(type = ResourcePersistence.class)
    protected ResourcePersistence resourcePersistence;
    @BeanReference(type = UserPersistence.class)
    protected UserPersistence userPersistence;

    /**
     * Caches the task in the entity cache if it is enabled.
     *
     * @param task the task
     */
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
    public void cacheResult(List<Task> tasks) {
        for (Task task : tasks) {
            if (EntityCacheUtil.getResult(TaskModelImpl.ENTITY_CACHE_ENABLED,
                        TaskImpl.class, task.getPrimaryKey()) == null) {
                cacheResult(task);
            } else {
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
    public Task remove(long taskId) throws NoSuchTaskException, SystemException {
        return remove(Long.valueOf(taskId));
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

            Task task = (Task) session.get(TaskImpl.class, primaryKey);

            if (task == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchTaskException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(task);
        } catch (NoSuchTaskException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected Task removeImpl(Task task) throws SystemException {
        task = toUnwrappedModel(task);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, task);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(task);

        return task;
    }

    @Override
    public Task updateImpl(com.liferay.timesheet.model.Task task, boolean merge)
        throws SystemException {
        task = toUnwrappedModel(task);

        boolean isNew = task.isNew();

        TaskModelImpl taskModelImpl = (TaskModelImpl) task;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, task, merge);

            task.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !TaskModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((taskModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(taskModelImpl.getOriginalUserId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
                    args);

                args = new Object[] { Long.valueOf(taskModelImpl.getUserId()) };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
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
        taskImpl.setCompanyId(task.getCompanyId());
        taskImpl.setUserId(task.getUserId());
        taskImpl.setStartDate(task.getStartDate());
        taskImpl.setEndDate(task.getEndDate());
        taskImpl.setTaskName(task.getTaskName());

        return taskImpl;
    }

    /**
     * Returns the task with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the task
     * @return the task
     * @throws com.liferay.portal.NoSuchModelException if a task with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Task findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the task with the primary key or throws a {@link com.liferay.timesheet.NoSuchTaskException} if it could not be found.
     *
     * @param taskId the primary key of the task
     * @return the task
     * @throws com.liferay.timesheet.NoSuchTaskException if a task with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public Task findByPrimaryKey(long taskId)
        throws NoSuchTaskException, SystemException {
        Task task = fetchByPrimaryKey(taskId);

        if (task == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + taskId);
            }

            throw new NoSuchTaskException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                taskId);
        }

        return task;
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
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the task with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param taskId the primary key of the task
     * @return the task, or <code>null</code> if a task with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public Task fetchByPrimaryKey(long taskId) throws SystemException {
        Task task = (Task) EntityCacheUtil.getResult(TaskModelImpl.ENTITY_CACHE_ENABLED,
                TaskImpl.class, taskId);

        if (task == _nullTask) {
            return null;
        }

        if (task == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                task = (Task) session.get(TaskImpl.class, Long.valueOf(taskId));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (task != null) {
                    cacheResult(task);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(TaskModelImpl.ENTITY_CACHE_ENABLED,
                        TaskImpl.class, taskId, _nullTask);
                }

                closeSession(session);
            }
        }

        return task;
    }

    /**
     * Returns all the tasks where userId = &#63;.
     *
     * @param userId the user ID
     * @return the matching tasks
     * @throws SystemException if a system exception occurred
     */
    public List<Task> findByUserId(long userId) throws SystemException {
        return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the tasks where userId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param userId the user ID
     * @param start the lower bound of the range of tasks
     * @param end the upper bound of the range of tasks (not inclusive)
     * @return the range of matching tasks
     * @throws SystemException if a system exception occurred
     */
    public List<Task> findByUserId(long userId, int start, int end)
        throws SystemException {
        return findByUserId(userId, start, end, null);
    }

    /**
     * Returns an ordered range of all the tasks where userId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param userId the user ID
     * @param start the lower bound of the range of tasks
     * @param end the upper bound of the range of tasks (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching tasks
     * @throws SystemException if a system exception occurred
     */
    public List<Task> findByUserId(long userId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID;
            finderArgs = new Object[] { userId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID;
            finderArgs = new Object[] { userId, start, end, orderByComparator };
        }

        List<Task> list = (List<Task>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (Task task : list) {
                if ((userId != task.getUserId())) {
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
            } else {
                query = new StringBundler(2);
            }

            query.append(_SQL_SELECT_TASK_WHERE);

            query.append(_FINDER_COLUMN_USERID_USERID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(userId);

                list = (List<Task>) QueryUtil.list(q, getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    FinderCacheUtil.removeResult(finderPath, finderArgs);
                } else {
                    cacheResult(list);

                    FinderCacheUtil.putResult(finderPath, finderArgs, list);
                }

                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Returns the first task in the ordered set where userId = &#63;.
     *
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching task
     * @throws com.liferay.timesheet.NoSuchTaskException if a matching task could not be found
     * @throws SystemException if a system exception occurred
     */
    public Task findByUserId_First(long userId,
        OrderByComparator orderByComparator)
        throws NoSuchTaskException, SystemException {
        Task task = fetchByUserId_First(userId, orderByComparator);

        if (task != null) {
            return task;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("userId=");
        msg.append(userId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchTaskException(msg.toString());
    }

    /**
     * Returns the first task in the ordered set where userId = &#63;.
     *
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching task, or <code>null</code> if a matching task could not be found
     * @throws SystemException if a system exception occurred
     */
    public Task fetchByUserId_First(long userId,
        OrderByComparator orderByComparator) throws SystemException {
        List<Task> list = findByUserId(userId, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last task in the ordered set where userId = &#63;.
     *
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching task
     * @throws com.liferay.timesheet.NoSuchTaskException if a matching task could not be found
     * @throws SystemException if a system exception occurred
     */
    public Task findByUserId_Last(long userId,
        OrderByComparator orderByComparator)
        throws NoSuchTaskException, SystemException {
        Task task = fetchByUserId_Last(userId, orderByComparator);

        if (task != null) {
            return task;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("userId=");
        msg.append(userId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchTaskException(msg.toString());
    }

    /**
     * Returns the last task in the ordered set where userId = &#63;.
     *
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching task, or <code>null</code> if a matching task could not be found
     * @throws SystemException if a system exception occurred
     */
    public Task fetchByUserId_Last(long userId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByUserId(userId);

        List<Task> list = findByUserId(userId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the tasks before and after the current task in the ordered set where userId = &#63;.
     *
     * @param taskId the primary key of the current task
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next task
     * @throws com.liferay.timesheet.NoSuchTaskException if a task with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public Task[] findByUserId_PrevAndNext(long taskId, long userId,
        OrderByComparator orderByComparator)
        throws NoSuchTaskException, SystemException {
        Task task = findByPrimaryKey(taskId);

        Session session = null;

        try {
            session = openSession();

            Task[] array = new TaskImpl[3];

            array[0] = getByUserId_PrevAndNext(session, task, userId,
                    orderByComparator, true);

            array[1] = task;

            array[2] = getByUserId_PrevAndNext(session, task, userId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Task getByUserId_PrevAndNext(Session session, Task task,
        long userId, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_TASK_WHERE);

        query.append(_FINDER_COLUMN_USERID_USERID_2);

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
                    } else {
                        query.append(WHERE_LESSER_THAN_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN);
                    } else {
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
                    } else {
                        query.append(ORDER_BY_DESC_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC);
                    } else {
                        query.append(ORDER_BY_DESC);
                    }
                }
            }
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(userId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(task);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<Task> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the tasks.
     *
     * @return the tasks
     * @throws SystemException if a system exception occurred
     */
    public List<Task> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the tasks.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of tasks
     * @param end the upper bound of the range of tasks (not inclusive)
     * @return the range of tasks
     * @throws SystemException if a system exception occurred
     */
    public List<Task> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the tasks.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of tasks
     * @param end the upper bound of the range of tasks (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of tasks
     * @throws SystemException if a system exception occurred
     */
    public List<Task> findAll(int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = new Object[] { start, end, orderByComparator };

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
            finderArgs = FINDER_ARGS_EMPTY;
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
            finderArgs = new Object[] { start, end, orderByComparator };
        }

        List<Task> list = (List<Task>) FinderCacheUtil.getResult(finderPath,
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
            } else {
                sql = _SQL_SELECT_TASK;
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<Task>) QueryUtil.list(q, getDialect(), start,
                            end, false);

                    Collections.sort(list);
                } else {
                    list = (List<Task>) QueryUtil.list(q, getDialect(), start,
                            end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    FinderCacheUtil.removeResult(finderPath, finderArgs);
                } else {
                    cacheResult(list);

                    FinderCacheUtil.putResult(finderPath, finderArgs, list);
                }

                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Removes all the tasks where userId = &#63; from the database.
     *
     * @param userId the user ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByUserId(long userId) throws SystemException {
        for (Task task : findByUserId(userId)) {
            remove(task);
        }
    }

    /**
     * Removes all the tasks from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (Task task : findAll()) {
            remove(task);
        }
    }

    /**
     * Returns the number of tasks where userId = &#63;.
     *
     * @param userId the user ID
     * @return the number of matching tasks
     * @throws SystemException if a system exception occurred
     */
    public int countByUserId(long userId) throws SystemException {
        Object[] finderArgs = new Object[] { userId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_TASK_WHERE);

            query.append(_FINDER_COLUMN_USERID_USERID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(userId);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of tasks.
     *
     * @return the number of tasks
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_TASK);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
                    FINDER_ARGS_EMPTY, count);

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
                    Class<?> clazz = getClass();

                    listenersList.add((ModelListener<Task>) InstanceFactory.newInstance(
                            clazz.getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(TaskImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
