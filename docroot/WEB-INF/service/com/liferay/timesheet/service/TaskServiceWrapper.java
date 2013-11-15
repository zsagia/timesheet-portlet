package com.liferay.timesheet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link TaskService}.
 * </p>
 *
 * @author    Adorjan
 * @see       TaskService
 * @generated
 */
public class TaskServiceWrapper implements TaskService,
    ServiceWrapper<TaskService> {
    private TaskService _taskService;

    public TaskServiceWrapper(TaskService taskService) {
        _taskService = taskService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _taskService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _taskService.setBeanIdentifier(beanIdentifier);
    }

    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _taskService.invokeMethod(name, parameterTypes, arguments);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public TaskService getWrappedTaskService() {
        return _taskService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedTaskService(TaskService taskService) {
        _taskService = taskService;
    }

    public TaskService getWrappedService() {
        return _taskService;
    }

    public void setWrappedService(TaskService taskService) {
        _taskService = taskService;
    }
}
