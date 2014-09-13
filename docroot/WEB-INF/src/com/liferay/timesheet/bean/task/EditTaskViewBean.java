package com.liferay.timesheet.bean.task;

import com.liferay.timesheet.model.Task;
import com.liferay.timesheet.primefaces.ProjectTreeNode;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
@ManagedBean
@ViewScoped
public class EditTaskViewBean implements Serializable {

	public Task getEditedTask() {
		return editedTask;
	}

	public ProjectTreeNode getSelectedProjectNode() {
		return selectedProjectNode;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setEditedTask(Task editedTask) {
		this.editedTask = editedTask;

		if (editedTask != null) {
			this.taskName = editedTask.getTaskName();
		}
	}

	public void setSelectedProjectNode(ProjectTreeNode selectedProjectNode) {
		this.selectedProjectNode = selectedProjectNode;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	private static final long serialVersionUID = -4363519020450177169L;

	private String taskName = null;
	private Task editedTask = null;
	private ProjectTreeNode selectedProjectNode = null;

}