package com.liferay.timesheet.bean.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
@ManagedBean
@SessionScoped
public class ViewControllerBean implements Serializable {

	public String showDayRemakeView() {
		return "/views/task/day-remake-view.xhtml";
	}

	public String showTaskView() {
		return "/views/task/view.xhtml";
	}

	private static final long serialVersionUID = 5559952133134792303L;

}