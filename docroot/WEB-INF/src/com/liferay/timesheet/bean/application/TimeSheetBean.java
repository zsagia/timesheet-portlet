package com.liferay.timesheet.bean.application;

import com.liferay.timesheet.util.DateTimeUtil;

import java.io.Serializable;

import java.util.Date;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
@ManagedBean
@ApplicationScoped
public class TimeSheetBean implements Serializable {

	public Date getCurrentDate() throws Exception {
		return DateTimeUtil.getDayWithoutTime(new Date());
	}

	private static final long serialVersionUID = -1650313968246331240L;

}