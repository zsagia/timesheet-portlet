package com.liferay.timesheet.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.timesheet.model.Task;

/**
 * The persistence interface for the task service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Adorjan
 * @see TaskPersistenceImpl
 * @see TaskUtil
 * @generated
 */
public interface TaskPersistence extends BasePersistence<Task> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link TaskUtil} to access the task persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the task in the entity cache if it is enabled.
    *
    * @param task the task
    */
    public void cacheResult(com.liferay.timesheet.model.Task task);

    /**
    * Caches the tasks in the entity cache if it is enabled.
    *
    * @param tasks the tasks
    */
    public void cacheResult(
        java.util.List<com.liferay.timesheet.model.Task> tasks);

    /**
    * Creates a new task with the primary key. Does not add the task to the database.
    *
    * @param taskId the primary key for the new task
    * @return the new task
    */
    public com.liferay.timesheet.model.Task create(long taskId);

    /**
    * Removes the task with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param taskId the primary key of the task
    * @return the task that was removed
    * @throws com.liferay.timesheet.NoSuchTaskException if a task with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.liferay.timesheet.model.Task remove(long taskId)
        throws com.liferay.portal.kernel.exception.SystemException,
            com.liferay.timesheet.NoSuchTaskException;

    public com.liferay.timesheet.model.Task updateImpl(
        com.liferay.timesheet.model.Task task, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the task with the primary key or throws a {@link com.liferay.timesheet.NoSuchTaskException} if it could not be found.
    *
    * @param taskId the primary key of the task
    * @return the task
    * @throws com.liferay.timesheet.NoSuchTaskException if a task with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.liferay.timesheet.model.Task findByPrimaryKey(long taskId)
        throws com.liferay.portal.kernel.exception.SystemException,
            com.liferay.timesheet.NoSuchTaskException;

    /**
    * Returns the task with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param taskId the primary key of the task
    * @return the task, or <code>null</code> if a task with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.liferay.timesheet.model.Task fetchByPrimaryKey(long taskId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the tasks where userId = &#63;.
    *
    * @param userId the user ID
    * @return the matching tasks
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.liferay.timesheet.model.Task> findByUserId(
        long userId) throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.liferay.timesheet.model.Task> findByUserId(
        long userId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.liferay.timesheet.model.Task> findByUserId(
        long userId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first task in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching task
    * @throws com.liferay.timesheet.NoSuchTaskException if a matching task could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.liferay.timesheet.model.Task findByUserId_First(long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            com.liferay.timesheet.NoSuchTaskException;

    /**
    * Returns the first task in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching task, or <code>null</code> if a matching task could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.liferay.timesheet.model.Task fetchByUserId_First(long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last task in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching task
    * @throws com.liferay.timesheet.NoSuchTaskException if a matching task could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.liferay.timesheet.model.Task findByUserId_Last(long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            com.liferay.timesheet.NoSuchTaskException;

    /**
    * Returns the last task in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching task, or <code>null</code> if a matching task could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.liferay.timesheet.model.Task fetchByUserId_Last(long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public com.liferay.timesheet.model.Task[] findByUserId_PrevAndNext(
        long taskId, long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            com.liferay.timesheet.NoSuchTaskException;

    /**
    * Returns all the tasks.
    *
    * @return the tasks
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.liferay.timesheet.model.Task> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.liferay.timesheet.model.Task> findAll(int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.liferay.timesheet.model.Task> findAll(int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the tasks where userId = &#63; from the database.
    *
    * @param userId the user ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByUserId(long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the tasks from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of tasks where userId = &#63;.
    *
    * @param userId the user ID
    * @return the number of matching tasks
    * @throws SystemException if a system exception occurred
    */
    public int countByUserId(long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of tasks.
    *
    * @return the number of tasks
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
