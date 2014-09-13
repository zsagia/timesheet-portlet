package com.liferay.timesheet.bean;

import com.liferay.timesheet.model.Project;
import com.liferay.timesheet.model.Task;
import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.primefaces.ProjectTreeNode;

import java.io.Serializable;

import java.util.Date;

import org.primefaces.model.TreeNode;
public abstract class AbstractViewBean implements Serializable {

	public TaskSession getCurrentTaskSession() {
		return currentTaskSession;
	}

	public TreeNode getRoot() {
		return root;
	}

	public Project getSelectedProject() {
		return selectedProject;
	}

	public TreeNode getSelectedProjectNode() {
		return selectedProjectNode;
	}

	public Task getSelectedTask() {
		return selectedTask;
	}

	public Date getTodayWithoutTime() {
		return todayWithoutTime;
	}

	public void setCurrentTaskSession(TaskSession currentTaskSession) {
		this.currentTaskSession = currentTaskSession;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	public void setSelectedProject(Project selectedProject) {
		this.selectedProject = selectedProject;
	}

	public void setSelectedProjectNode(TreeNode selectedProjectNode) {
		this.selectedProjectNode = selectedProjectNode;

		if (selectedProjectNode != null) {
			selectedProject =
				((ProjectTreeNode)selectedProjectNode).getProject();
		}
	}

	public void setSelectedTask(Task selectedTask) {
		this.selectedTask = selectedTask;
	}

	public void setTodayWithoutTime(Date todayWithoutTime) {
		this.todayWithoutTime = todayWithoutTime;
	}

	private static final long serialVersionUID = 1L;

	private TaskSession currentTaskSession = null;
	private TreeNode root = null;
	private Project selectedProject = null;
	private TreeNode selectedProjectNode = null;
	private Task selectedTask = null;
	private Date todayWithoutTime = null;

}