package com.liferay.timesheet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Task}.
 * </p>
 *
 * @author    Adorjan
 * @see       Task
 * @generated
 */
public class TaskWrapper implements Task, ModelWrapper<Task> {
    private Task _task;

    public TaskWrapper(Task task) {
        _task = task;
    }

    public Class<?> getModelClass() {
        return Task.class;
    }

    public String getModelClassName() {
        return Task.class.getName();
    }

    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("taskId", getTaskId());
        attributes.put("companyId", getCompanyId());
        attributes.put("userId", getUserId());
        attributes.put("startDate", getStartDate());
        attributes.put("endDate", getEndDate());
        attributes.put("taskName", getTaskName());

        return attributes;
    }

    public void setModelAttributes(Map<String, Object> attributes) {
        Long taskId = (Long) attributes.get("taskId");

        if (taskId != null) {
            setTaskId(taskId);
        }

        Long companyId = (Long) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Date startDate = (Date) attributes.get("startDate");

        if (startDate != null) {
            setStartDate(startDate);
        }

        Date endDate = (Date) attributes.get("endDate");

        if (endDate != null) {
            setEndDate(endDate);
        }

        String taskName = (String) attributes.get("taskName");

        if (taskName != null) {
            setTaskName(taskName);
        }
    }

    /**
    * Returns the primary key of this task.
    *
    * @return the primary key of this task
    */
    public long getPrimaryKey() {
        return _task.getPrimaryKey();
    }

    /**
    * Sets the primary key of this task.
    *
    * @param primaryKey the primary key of this task
    */
    public void setPrimaryKey(long primaryKey) {
        _task.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the task ID of this task.
    *
    * @return the task ID of this task
    */
    public long getTaskId() {
        return _task.getTaskId();
    }

    /**
    * Sets the task ID of this task.
    *
    * @param taskId the task ID of this task
    */
    public void setTaskId(long taskId) {
        _task.setTaskId(taskId);
    }

    /**
    * Returns the company ID of this task.
    *
    * @return the company ID of this task
    */
    public long getCompanyId() {
        return _task.getCompanyId();
    }

    /**
    * Sets the company ID of this task.
    *
    * @param companyId the company ID of this task
    */
    public void setCompanyId(long companyId) {
        _task.setCompanyId(companyId);
    }

    /**
    * Returns the user ID of this task.
    *
    * @return the user ID of this task
    */
    public long getUserId() {
        return _task.getUserId();
    }

    /**
    * Sets the user ID of this task.
    *
    * @param userId the user ID of this task
    */
    public void setUserId(long userId) {
        _task.setUserId(userId);
    }

    /**
    * Returns the user uuid of this task.
    *
    * @return the user uuid of this task
    * @throws SystemException if a system exception occurred
    */
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _task.getUserUuid();
    }

    /**
    * Sets the user uuid of this task.
    *
    * @param userUuid the user uuid of this task
    */
    public void setUserUuid(java.lang.String userUuid) {
        _task.setUserUuid(userUuid);
    }

    /**
    * Returns the start date of this task.
    *
    * @return the start date of this task
    */
    public java.util.Date getStartDate() {
        return _task.getStartDate();
    }

    /**
    * Sets the start date of this task.
    *
    * @param startDate the start date of this task
    */
    public void setStartDate(java.util.Date startDate) {
        _task.setStartDate(startDate);
    }

    /**
    * Returns the end date of this task.
    *
    * @return the end date of this task
    */
    public java.util.Date getEndDate() {
        return _task.getEndDate();
    }

    /**
    * Sets the end date of this task.
    *
    * @param endDate the end date of this task
    */
    public void setEndDate(java.util.Date endDate) {
        _task.setEndDate(endDate);
    }

    /**
    * Returns the task name of this task.
    *
    * @return the task name of this task
    */
    public java.lang.String getTaskName() {
        return _task.getTaskName();
    }

    /**
    * Sets the task name of this task.
    *
    * @param taskName the task name of this task
    */
    public void setTaskName(java.lang.String taskName) {
        _task.setTaskName(taskName);
    }

    public boolean isNew() {
        return _task.isNew();
    }

    public void setNew(boolean n) {
        _task.setNew(n);
    }

    public boolean isCachedModel() {
        return _task.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _task.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _task.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _task.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _task.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _task.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _task.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new TaskWrapper((Task) _task.clone());
    }

    public int compareTo(Task task) {
        return _task.compareTo(task);
    }

    @Override
    public int hashCode() {
        return _task.hashCode();
    }

    public com.liferay.portal.model.CacheModel<Task> toCacheModel() {
        return _task.toCacheModel();
    }

    public Task toEscapedModel() {
        return new TaskWrapper(_task.toEscapedModel());
    }

    public Task toUnescapedModel() {
        return new TaskWrapper(_task.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _task.toString();
    }

    public java.lang.String toXmlString() {
        return _task.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _task.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof TaskWrapper)) {
            return false;
        }

        TaskWrapper taskWrapper = (TaskWrapper) obj;

        if (Validator.equals(_task, taskWrapper._task)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public Task getWrappedTask() {
        return _task;
    }

    public Task getWrappedModel() {
        return _task;
    }

    public void resetOriginalValues() {
        _task.resetOriginalValues();
    }
}
