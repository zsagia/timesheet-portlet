package com.liferay.timesheet.bean;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class TaskSessionMultipleBean extends TaskSessionBaseBean {

	private static final long serialVersionUID = 6518160458023526615L;

	@ManagedProperty(value="#{param.selectedTaskId}")
	private long selectedTaskId;

	private Map<Long, Date> startTimes;

	public TaskSessionMultipleBean() {
		super();

		this.startTimes = new HashMap<Long, Date>();
	}

	public long getSelectedTaskId() {
		return selectedTaskId;
	}

	public Date getStartTime() {
		return startTimes.get(getSelectedTaskId());
	}

	public Map<Long, Date> getStartTimes() {
		return startTimes;
	}

	public void setSelectedTaskId(long selectedTaskId) {
		this.selectedTaskId = selectedTaskId;
	}

}