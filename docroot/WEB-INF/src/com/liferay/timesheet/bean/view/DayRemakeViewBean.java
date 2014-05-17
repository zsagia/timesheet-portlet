package com.liferay.timesheet.bean.view;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.service.TaskSessionLocalServiceUtil;
import com.liferay.timesheet.util.DateTimeUtil;
import com.liferay.timesheet.util.PortletPropsValues;
import com.liferay.timesheet.util.TimeSheetUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.extensions.event.timeline.TimelineModificationEvent;
import org.primefaces.extensions.model.timeline.TimelineEvent;
import org.primefaces.extensions.model.timeline.TimelineModel;

@ManagedBean
@ViewScoped
public class DayRemakeViewBean implements Serializable {

	public DayRemakeViewBean() {
		initialize();
	}

	public void onChange(TimelineModificationEvent e) {
		event = e.getTimelineEvent();

		dayRemakeModel.update(event);
	}

	protected void initialize() {
		try {
			currentUser = TimeSheetUtil.getCurrentUser();
			currentDate = DateTimeUtil.getTodayWithoutTime();
			dayStart = DateTimeUtil.getDateFromMilitaryTime(
				currentUser, currentDate,
				PortletPropsValues.RESTRICTIONS_STARTTIME_EARLIEST);

			dayEnd = DateTimeUtil.getDateFromMilitaryTime(
				currentUser, currentDate,
				PortletPropsValues.RESTRICTIONS_ENDTIME_LATEST);

			min = dayStart;
			max = dayEnd;

			timeZone = currentUser.getTimeZone();
			zoomMax = 1000 * 60 * 60 * 24;
			zoomMin = 1000 * 60;

			createDayRemakeModel();
		} catch (Exception e) {
			
		}
	}

	private void createDayRemakeModel()
		throws SystemException, PortalException {

		dayRemakeModel = new TimelineModel();

		List<TaskSession> taskSessions =
			TaskSessionLocalServiceUtil.getTaskSessionsByC_U_I(
				TimeSheetUtil.getCompanyId(), currentUser.getUserId(),
				currentDate, DateTimeUtil.getIncrementedDay(currentDate));

		for (TaskSession taskSession: taskSessions) {
			TimelineEvent event = new TimelineEvent(
				taskSession, taskSession.getStartTime(),
				taskSession.getEndTime(), true);

			dayRemakeModel.add(event);
		}
	}

	public TimelineModel getDayRemakeModel() {
		return dayRemakeModel;
	}

	public void setDayRemakeModel(TimelineModel dayRemakeModel) {
		this.dayRemakeModel = dayRemakeModel;
	}

	public TimelineEvent getEvent() {
		return event;
	}

	public void setEvent(TimelineEvent event) {
		this.event = event;
	}

	public long getZoomMax() {
		return zoomMax;
	}

	public void setZoomMax(long zoomMax) {
		this.zoomMax = zoomMax;
	}

	public Date getDayStart() {
		return dayStart;
	}

	public void setDayStart(Date dayStart) {
		this.dayStart = dayStart;
	}

	public Date getDayEnd() {
		return dayEnd;
	}

	public void setDayEnd(Date dayEnd) {
		this.dayEnd = dayEnd;
	}

	public TimeZone getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}

	public boolean isTimeChangeable() {
		return timeChangeable;
	}

	public void setTimeChangeable(boolean timeChangeable) {
		this.timeChangeable = timeChangeable;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public Date getMin() {
		return min;
	}

	public void setMin(Date min) {
		this.min = min;
	}

	public Date getMax() {
		return max;
	}

	public void setMax(Date max) {
		this.max = max;
	}

	public long getZoomMin() {
		return zoomMin;
	}

	public void setZoomMin(long zoomMin) {
		this.zoomMin = zoomMin;
	}

	private TimelineModel dayRemakeModel = null;
	private TimelineEvent event = null;
	private long zoomMax;
	private long zoomMin;
	private Date dayStart = null;
	private Date dayEnd = null;
	private TimeZone timeZone = null; 
	private boolean timeChangeable;
	private User currentUser = null;
	private Date currentDate = null;
	private Date min = null;
	private Date max = null;

	private static final long serialVersionUID = -9197850325731216027L;
}