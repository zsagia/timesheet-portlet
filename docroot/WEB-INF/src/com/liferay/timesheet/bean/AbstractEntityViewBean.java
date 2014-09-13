package com.liferay.timesheet.bean;

public abstract class AbstractEntityViewBean extends AbstractViewBean
	implements ActionableView {

	public void doEditAction(Object bean) {}

	public void doListAction(Object bean) {}

	public void doNewAction(Object bean) {}

	public String getAction() {
		return action;
	}

	public boolean isEditAction() {
		return ACTION_EDIT.equals(getAction());
	}

	public boolean isListAction() {
		return ACTION_LIST.equals(getAction());
	}

	public boolean isNewAction() {
		return ACTION_NEW.equals(getAction());
	}

	public boolean isNewOrEditAction() {
		return (ACTION_NEW.equals(getAction()) ||
				ACTION_EDIT.equals(getAction()));
	}

	public boolean isNotEditAction() {
		return !ACTION_EDIT.equals(getAction());
	}

	public boolean isNotListAction() {
		return !ACTION_LIST.equals(getAction());
	}

	public boolean isNotNewAction() {
		return !ACTION_NEW.equals(getAction());
	}

	public boolean isNotNewAndEditAction() {
		return (!ACTION_NEW.equals(getAction()) &&
				!ACTION_EDIT.equals(getAction()));
	}

	public void onNodeSelect(Object bean) {}

	public void onNodeUnSelect(Object bean) {}

	public void setAction(String action) {
		this.action = action;
	}

	private static final long serialVersionUID = 1L;

	private String action = ACTION_NEW;

}