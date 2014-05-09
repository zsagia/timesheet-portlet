package com.liferay.timesheet.primefaces;

import com.liferay.timesheet.model.Project;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

public class ProjectTreeNode extends DefaultTreeNode {

	private static final long serialVersionUID = -3279028075857890516L;

	private Project project;

	public ProjectTreeNode(String data, TreeNode parent) {
		super(data, parent);
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
