package com.liferay.timesheet.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "viewController")
@SessionScoped
public class ViewController implements Serializable {

	private static final long serialVersionUID = 5559952133134792303L;

	public String showView() {
		return "success";
	}

	public String showTaskSessionList() {
		return "success";
	}

}