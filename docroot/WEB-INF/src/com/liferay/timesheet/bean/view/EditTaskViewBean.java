package com.liferay.timesheet.bean.view;

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

	public void setEditedTask(Task editedTask) {
		this.editedTask = editedTask;

		if (editedTask != null) {
			this.taskName = editedTask.getTaskName();
		}
	}

	public ProjectTreeNode getSelectedProjectNode() {
		return selectedProjectNode;
	}

	public void setSelectedProjectNode(ProjectTreeNode selectedProjectNode) {
		this.selectedProjectNode = selectedProjectNode;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	private ProjectTreeNode selectedProjectNode = null;
	private Task editedTask = null;
	private String taskName = null;

	private static final long serialVersionUID = -4363519020450177169L;
}