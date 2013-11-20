package com.liferay.timesheet.service.impl;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.timesheet.NoSuchTaskException;
import com.liferay.timesheet.model.Task;
import com.liferay.timesheet.service.base.TaskLocalServiceBaseImpl;

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

	public Task addTask(String taskName, long userId) throws SystemException {
		long taskId = counterLocalService.increment();

		Task task = taskPersistence.create(taskId);

		task.setUserId(userId);
		task.setTaskName(taskName);

		taskPersistence.update(task, false);

		return task;
	}

	public Task getTaskByTN_U(String taskName, long userId) {
		Task task = null;

		try {
			task = taskPersistence.findByTN_U(taskName, userId);
		} catch (NoSuchTaskException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		}

		return task;
	}

	public List<Task> getTasksByUserId(long userId) throws SystemException {
		return taskPersistence.findByUserId(userId);
	}

}