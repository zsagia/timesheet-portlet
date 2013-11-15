package com.liferay.timesheet.bean;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.timesheet.model.Task;
import com.liferay.timesheet.service.TaskLocalServiceUtil;

import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;

public class TaskBean {

	private Date end;
	private Date start;
	private String taskName;

	public String createTask() {
		FacesContext facesContext = FacesContext.getCurrentInstance();

		long userId =
			Long.valueOf(facesContext.getExternalContext().getRemoteUser());

		try {
			User user = UserLocalServiceUtil.getUser(userId);

			long companyId = user.getCompanyId();

			TaskLocalServiceUtil.addTask(
				companyId, user.getUserId(), getStart(), getEnd(),
				getTaskName());
		}
		catch (PortalException pe) {
			pe.printStackTrace();
		}
		catch (SystemException se) {
			se.printStackTrace();
		}

		return "success";
	}

	public Date getEnd() {
		return end;
	}

	public String getTaskName() {
		return taskName;
	}

	public Date getStart() {
		return start;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public List<Task> getTaskByUser() {
		FacesContext facesContext = FacesContext.getCurrentInstance();

		List<Task> taskToday = null;

		long userId =
			Long.valueOf(facesContext.getExternalContext().getRemoteUser());

		try {
			taskToday = TaskLocalServiceUtil.getTasks(userId);
		}
		catch (SystemException se) {
			se.printStackTrace();
		}

		return taskToday;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public void setStart(Date start) {
		this.start = start;
	}

}