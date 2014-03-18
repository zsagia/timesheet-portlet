package com.liferay.timesheet.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
@ManagedBean(name = "viewController")
@SessionScoped
public class ViewController implements Serializable {

	public String showTaskSessionList() {
		return "/views/tasksession_list.xhtml";
	}

	public String showView() {
		return "/views/view.xhtml";
	}

	private static final long serialVersionUID = 5559952133134792303L;

}