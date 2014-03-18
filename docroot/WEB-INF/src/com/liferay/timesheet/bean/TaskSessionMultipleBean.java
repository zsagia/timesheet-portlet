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

	public void setStartTimes(Map<Long, Date> startTimes) {
		this.startTimes = startTimes;
	}

	protected void clear() {
		super.clear();

		setStartTimes(new HashMap<Long, Date>());
	}

	private static final long serialVersionUID = 6518160458023526615L;

	@ManagedProperty(value ="#{param.selectedTaskId}")
	private long selectedTaskId;

	private Map<Long, Date> startTimes;

}