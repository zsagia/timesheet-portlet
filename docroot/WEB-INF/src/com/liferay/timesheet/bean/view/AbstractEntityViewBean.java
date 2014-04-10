package com.liferay.timesheet.bean.view;

public abstract class AbstractEntityViewBean extends AbstractViewBean {

	public abstract void doEditAction(Object bean);

	public abstract void doNewAction(Object bean);

	public abstract void onNodeSelect(Object bean);

	public abstract void onNodeUnSelect(Object bean);

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	private String action = ACTION_NEW;

	public static final String ACTION_NEW = "new";
	public static final String ACTION_EDIT = "edit";
	public static final String ACTION_SELECTED = "selected";

	private static final long serialVersionUID = 1L;

}