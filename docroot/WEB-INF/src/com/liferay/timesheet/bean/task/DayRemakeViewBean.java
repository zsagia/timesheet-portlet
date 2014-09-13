package com.liferay.timesheet.bean.task;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.timesheet.bean.validation.ValidationBean;
import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.service.TaskSessionLocalServiceUtil;
import com.liferay.timesheet.util.DateTimeUtil;
import com.liferay.timesheet.util.PortletPropsValues;
import com.liferay.timesheet.util.TimeSheetUtil;

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.extensions.model.timeline.TimelineEvent;
import org.primefaces.extensions.model.timeline.TimelineModel;
@ManagedBean
@ViewScoped
public class DayRemakeViewBean implements Serializable {

	public static final String ACTION_EDIT = "edit";

	public static final String ACTION_NEW = "new";

	public static final String ACTION_SELECTED = "selected";

	public DayRemakeViewBean() {
		initialize();
	}

	public String getAction() {
		return action;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public Date getDayEnd() {
		return dayEnd;
	}

	public TimelineModel getDayRemakeModel() {
		return dayRemakeModel;
	}

	public Date getDayStart() {
		return dayStart;
	}

	public Date getMax() {
		return max;
	}

	public Date getMin() {
		return min;
	}

	public TimelineEvent getSelectedEvent() {
		return selectedEvent;
	}

	public TimeZone getTimeZone() {
		return timeZone;
	}

	public long getZoomMax() {
		return zoomMax;
	}

	public long getZoomMin() {
		return zoomMin;
	}

	public boolean isTimeChangeable() {
		return timeChangeable;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public void setDayEnd(Date dayEnd) {
		this.dayEnd = dayEnd;
	}

	public void setDayRemakeModel(TimelineModel dayRemakeModel) {
		this.dayRemakeModel = dayRemakeModel;
	}

	public void setDayStart(Date dayStart) {
		this.dayStart = dayStart;
	}

	public void setMax(Date max) {
		this.max = max;
	}

	public void setMin(Date min) {
		this.min = min;
	}

	public void setSelectedEvent(TimelineEvent selectedEvent) {
		this.selectedEvent = selectedEvent;
	}

	public void setTimeChangeable(boolean timeChangeable) {
		this.timeChangeable = timeChangeable;
	}

	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}

	public void setZoomMax(long zoomMax) {
		this.zoomMax = zoomMax;
	}

	public void setZoomMin(long zoomMin) {
		this.zoomMin = zoomMin;
	}

	protected void initialize() {
		ExternalContext context =
			FacesContext.getCurrentInstance().getExternalContext();

		Map<String, Object> sessionMap = context.getSessionMap();

		ValidationBean validationBean = (ValidationBean)sessionMap.get(
			"validationBean");

		try {
			if (validationBean.isValidPreviousWorkingDay()) {
				currentDate = DateTimeUtil.getTodayWithoutTime();
			}
			else {
				currentDate = DateTimeUtil.getPreviousWorkingDay(
					DateTimeUtil.getTodayWithoutTime());
			}

			currentUser = TimeSheetUtil.getCurrentUser();
			dayStart = DateTimeUtil.getDateFromMilitaryTime(
				currentUser, currentDate,
				PortletPropsValues.RESTRICTIONS_STARTTIME_EARLIEST);

			dayEnd = DateTimeUtil.getDateFromMilitaryTime(
				currentUser, currentDate,
				PortletPropsValues.RESTRICTIONS_ENDTIME_LATEST);

			min = dayStart;
			max = dayEnd;

			timeZone = currentUser.getTimeZone();
			timeChangeable = false;
			zoomMax = 1000 * 60 * 60 * 24;
			zoomMin = 1000 * 60;

			createDayRemakeModel();
		} catch (Exception e) {
		}
	}

	private void createDayRemakeModel()
		throws PortalException, SystemException {

		dayRemakeModel = new TimelineModel();

		List<TaskSession> taskSessions =
			TaskSessionLocalServiceUtil.getTaskSessionsByC_U_I(
				TimeSheetUtil.getCompanyId(), currentUser.getUserId(),
				currentDate, DateTimeUtil.getIncrementedDay(currentDate));

		for (TaskSession taskSession : taskSessions) {
			TimelineEvent event = new TimelineEvent(
				taskSession, taskSession.getStartTime(),
				taskSession.getEndTime(), true, null, "readonly");

			dayRemakeModel.add(event);
		}
	}

	private static final long serialVersionUID = -9197850325731216027L; private TimelineModel dayRemakeModel = null;

	private String action = ACTION_NEW;
	private Date currentDate = null;
	private User currentUser = null;
	private Date dayEnd = null;
	private Date dayStart = null;
	private Date max = null;
	private Date min = null;
	private TimelineEvent selectedEvent = null;
	private boolean timeChangeable;
	private TimeZone timeZone = null;
	private long zoomMax;
	private long zoomMin;

}