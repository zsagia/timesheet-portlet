package com.liferay.timesheet.service.impl;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.timesheet.model.Task;
import com.liferay.timesheet.service.base.TaskLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * The implementation of the task local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.timesheet.service.TaskLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Adorjan Nagy
 * @author Tibor Jandi
 * @author Istvan Sajtos
 * @see com.liferay.timesheet.service.base.TaskLocalServiceBaseImpl
 * @see com.liferay.timesheet.service.TaskLocalServiceUtil
 */
public class TaskLocalServiceImpl extends TaskLocalServiceBaseImpl {

	public Task addTask(
			long companyId, long userId, Date startDate, Date endDate,
			String taskName )
		throws SystemException {

		long taskId = counterLocalService.increment();

		Task task = taskPersistence.create(taskId);

		task.setCompanyId(companyId);
		task.setUserId(userId);
		task.setStartDate(startDate);
		task.setEndDate(endDate);
		task.setTaskName(taskName);

		taskPersistence.update(task, false);

		return task;
	}

	public List<Task> getTasks(long userId) throws SystemException {
		return taskPersistence.findByUserId(userId);
	}

}