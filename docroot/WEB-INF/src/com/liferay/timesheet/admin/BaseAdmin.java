package com.liferay.timesheet.admin;

public interface BaseAdmin {

	public static final String ACTION_NEW = "new";
	public static final String ACTION_EDIT = "edit";
	public static final String ACTION_SELECTED = "selected";

	public void doEditAction();

	public void doNewAction();

	public void onNodeSelect();

	public void onNodeUnSelect();
	
}