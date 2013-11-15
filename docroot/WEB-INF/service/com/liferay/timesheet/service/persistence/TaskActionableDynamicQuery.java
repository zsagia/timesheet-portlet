package com.liferay.timesheet.service.persistence;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

import com.liferay.timesheet.model.Task;
import com.liferay.timesheet.service.TaskLocalServiceUtil;

/**
 * @author Adorjan
 * @generated
 */
public abstract class TaskActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public TaskActionableDynamicQuery() throws SystemException {
        setBaseLocalService(TaskLocalServiceUtil.getService());
        setClass(Task.class);

        setClassLoader(com.liferay.timesheet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("taskId");
    }
}
