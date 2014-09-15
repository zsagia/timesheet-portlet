package com.liferay.timesheet.bean.model;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.timesheet.model.Day;
import com.liferay.timesheet.model.DayConstants;
import com.liferay.timesheet.service.DayLocalServiceUtil;
import com.liferay.timesheet.util.TimeSheetUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
@ManagedBean
@RequestScoped
public class DayModelBean implements Serializable {

	public Day createDay() throws PortalException, SystemException {
		ServiceContext serviceContext = TimeSheetUtil.createServiceContext();

		return DayLocalServiceUtil.addDay(
			TimeSheetUtil.getCurrentUserId(), date, type, serviceContext);
	}

	public Day deleteDay(long dayId) throws Exception {
		return DayLocalServiceUtil.deleteDay(dayId);
	}

	public Date getDate() {
		return date;
	}

	public List<Day> getDays() throws Exception {
		List<Day> days = new ArrayList<Day>();

		long companyId = TimeSheetUtil.getCompanyId();

		days.addAll(DayLocalServiceUtil.getDays(
			companyId, DayConstants.TYPE_NATIONAL_HOLIDAY));

		days.addAll(DayLocalServiceUtil.getDays(
			companyId, DayConstants.TYPE_EXCEPTIONAL_HOLIDAY));

		days.addAll(DayLocalServiceUtil.getDays(
			companyId, DayConstants.TYPE_EXCEPTIONAL_WORKDAY));

		Collections.sort(days);

		return days;
	}

	public int getType() {
		return type;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setType(int type) {
		this.type = type;
	}

	private static final long serialVersionUID = 2602676609530935036L;

	private Date date;
	private int type;

}