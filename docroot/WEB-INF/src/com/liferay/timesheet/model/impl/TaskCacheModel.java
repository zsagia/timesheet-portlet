package com.liferay.timesheet.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.timesheet.model.Task;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing Task in entity cache.
 *
 * @author Adorjan
 * @see Task
 * @generated
 */
public class TaskCacheModel implements CacheModel<Task>, Serializable {
    public long taskId;
    public long companyId;
    public long userId;
    public long startDate;
    public long endDate;
    public String taskName;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

        sb.append("{taskId=");
        sb.append(taskId);
        sb.append(", companyId=");
        sb.append(companyId);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", startDate=");
        sb.append(startDate);
        sb.append(", endDate=");
        sb.append(endDate);
        sb.append(", taskName=");
        sb.append(taskName);
        sb.append("}");

        return sb.toString();
    }

    public Task toEntityModel() {
        TaskImpl taskImpl = new TaskImpl();

        taskImpl.setTaskId(taskId);
        taskImpl.setCompanyId(companyId);
        taskImpl.setUserId(userId);

        if (startDate == Long.MIN_VALUE) {
            taskImpl.setStartDate(null);
        } else {
            taskImpl.setStartDate(new Date(startDate));
        }

        if (endDate == Long.MIN_VALUE) {
            taskImpl.setEndDate(null);
        } else {
            taskImpl.setEndDate(new Date(endDate));
        }

        if (taskName == null) {
            taskImpl.setTaskName(StringPool.BLANK);
        } else {
            taskImpl.setTaskName(taskName);
        }

        taskImpl.resetOriginalValues();

        return taskImpl;
    }
}
