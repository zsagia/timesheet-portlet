package com.liferay.timesheet.bean.portlet;

import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.timesheet.bean.model.TaskModelBean;
import com.liferay.timesheet.bean.model.TaskSessionModelBean;
import com.liferay.timesheet.bean.view.DayRemakeViewBean;
import com.liferay.timesheet.model.Task;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class DayRemakeManagedBean implements Serializable {

	public void addTaskSessionAction() {
		Task selectedTask = taskSessionModelBean.getSelectedTask();
		Date startTime = taskSessionModelBean.getStartTime();
		Date endTime = taskSessionModelBean.getEndTime();
		String description = taskSessionModelBean.getDescription();

		try {
			taskSessionModelBean.createTaskSession(
				selectedTask, startTime, endTime, description);
		} catch (PortalException e) {
			e.printStackTrace();
		}
	}

	public TaskModelBean getTaskModelBean() {
		return taskModelBean;
	}

	public void setTaskModelBean(TaskModelBean taskModelBean) {
		this.taskModelBean = taskModelBean;
	}

	public TaskSessionModelBean getTaskSessionModelBean() {
		return taskSessionModelBean;
	}

	public void setTaskSessionModelBean(
		TaskSessionModelBean taskSessionModelBean) {

		this.taskSessionModelBean = taskSessionModelBean;
	}

	public DayRemakeViewBean getDayRemakeViewBean() {
		return dayRemakeViewBean;
	}

	public void setDayRemakeViewBean(DayRemakeViewBean dayRemakeViewBean) {
		this.dayRemakeViewBean = dayRemakeViewBean;
	}

	@ManagedProperty(name = "dayRemakeViewBean",
		value = "#{dayRemakeViewBean}")
	private DayRemakeViewBean dayRemakeViewBean;
	@ManagedProperty(name = "taskModelBean",
		value = "#{taskModelBean}")
	private TaskModelBean taskModelBean;
	@ManagedProperty(name = "taskSessionModelBean",
		value = "#{taskSessionModelBean}")
	private TaskSessionModelBean taskSessionModelBean;

	private static final long serialVersionUID = 5620158902340242023L;
	private static Logger logger = LoggerFactory.getLogger(
		DayRemakeManagedBean.class);

}