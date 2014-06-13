package com.liferay.timesheet.bean.application;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.liferay.timesheet.util.DateTimeUtil;

@ManagedBean
@ApplicationScoped
public class TimeSheetBean implements Serializable {

	private static final long serialVersionUID = -1650313968246331240L;

	public Date getCurrentDate() throws Exception {
		return DateTimeUtil.getDayWithoutTime(new Date());
	}

}