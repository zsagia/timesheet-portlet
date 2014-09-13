package com.liferay.timesheet.bean;

public interface ActionableView {

	void doEditAction(Object bean);

	void doListAction(Object bean);

	void doNewAction(Object bean);

	void onNodeSelect(Object bean);

	void onNodeUnSelect(Object bean);

	String getAction();

	void setAction(String action);

	static final String ACTION_EDIT = "edit";

	static final String ACTION_NEW = "new";

	static final String ACTION_LIST = "list";

	static final String ACTION_SELECTED = "selected";

}