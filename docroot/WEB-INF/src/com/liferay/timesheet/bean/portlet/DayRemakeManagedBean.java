package com.liferay.timesheet.bean.portlet;

import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.timesheet.TSEarliestStartTimeException;
import com.liferay.timesheet.TSEndTimeException;
import com.liferay.timesheet.TSNoEndTimeException;
import com.liferay.timesheet.TSNoStartTimeException;
import com.liferay.timesheet.TSStartEndTimeException;
import com.liferay.timesheet.TSStartTimeException;
import com.liferay.timesheet.TSWorkDurationException;
import com.liferay.timesheet.bean.model.TaskModelBean;
import com.liferay.timesheet.bean.model.TaskSessionModelBean;
import com.liferay.timesheet.bean.view.DayRemakeViewBean;
import com.liferay.timesheet.model.Task;
import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.service.TaskSessionLocalServiceUtil;
import com.liferay.timesheet.util.DateTimeUtil;
import com.liferay.timesheet.util.MessageUtil;
import com.liferay.timesheet.util.PortletPropsValues;
import com.liferay.timesheet.util.TimeSheetUtil;
import com.liferay.timesheet.validator.TaskSessionValidatorUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.primefaces.extensions.component.timeline.TimelineUpdater;
import org.primefaces.extensions.event.timeline.TimelineAddEvent;
import org.primefaces.extensions.event.timeline.TimelineModificationEvent;
import org.primefaces.extensions.event.timeline.TimelineSelectEvent;
import org.primefaces.extensions.model.timeline.TimelineEvent;
import org.primefaces.extensions.model.timeline.TimelineModel;

@ManagedBean
@ViewScoped
public class DayRemakeManagedBean implements Serializable {

	public void addTaskSessionAction() {
		Task selectedTask = taskSessionModelBean.getSelectedTask();
		Date startTime = taskSessionModelBean.getStartTime();
		Date endTime = taskSessionModelBean.getEndTime();
		String description = taskSessionModelBean.getDescription();

		try {
			TaskSession taskSession = taskSessionModelBean.createTaskSession(
				selectedTask, startTime, endTime, description);

			TimelineModel dayRemakeModel =
				dayRemakeViewBean.getDayRemakeModel();

			TimelineEvent event = new TimelineEvent(
				taskSession, taskSession.getStartTime(),
				taskSession.getEndTime(), true);

			dayRemakeModel.add(event);

			TimelineUpdater timelineUpdater =
				TimelineUpdater.getCurrentInstance(
				":timesheetLayout:j_idt33:j_idt34:timelineForm:dayRemake");

				dayRemakeModel.update(event, timelineUpdater);

		} catch (PortalException e) {
			logger.error(e);
		}
	}

	public void deleteTaskSessionAction() {
		TimelineEvent selectedEvent = dayRemakeViewBean.getSelectedEvent();

		if (selectedEvent != null) {
			TaskSession selectedTaskSession = (TaskSession)selectedEvent.getData();

			try {
				taskSessionModelBean.deleteTaskSession(
					selectedTaskSession.getTaskSessionId());

				TimelineUpdater timelineUpdater =
					TimelineUpdater.getCurrentInstance(
						":timesheetLayout:j_idt33:j_idt34:timelineForm:dayRemake");

				TimelineModel dayRemakeModel =
					dayRemakeViewBean.getDayRemakeModel();

				dayRemakeModel.delete(selectedEvent, timelineUpdater);
			} catch (PortalException e) {
				logger.error(e);
			}
		}
	}

	public void updateTaskSessionAction() {
		TaskSession selectedTaskSession =
			(TaskSession)dayRemakeViewBean.getSelectedEvent().getData();

		Task selectedTask = taskSessionModelBean.getSelectedTask();

		selectedTaskSession.setStartTime(taskSessionModelBean.getStartTime());
		selectedTaskSession.setEndTime( taskSessionModelBean.getEndTime());
		selectedTaskSession.setDescription(
			taskSessionModelBean.getDescription());

		selectedTaskSession.setTaskId(selectedTask.getTaskId());

		try {
			TaskSession taskSession =
				TaskSessionLocalServiceUtil.updateTaskSession(
					selectedTaskSession);

			TimelineModel dayRemakeModel =
				dayRemakeViewBean.getDayRemakeModel();

			TimelineEvent selectedEvent = dayRemakeViewBean.getSelectedEvent();

			selectedEvent.setData(taskSession);
			selectedEvent.setStartDate(taskSession.getStartTime());
			selectedEvent.setEndDate(taskSession.getEndTime());

			TimelineUpdater timelineUpdater =
				TimelineUpdater.getCurrentInstance(
					":timesheetLayout:j_idt33:j_idt34:timelineForm:dayRemake");

			dayRemakeModel.update(selectedEvent, timelineUpdater);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public void onChange(TimelineModificationEvent event) {
		TimelineEvent selectedEvent = event.getTimelineEvent();
		dayRemakeViewBean.setSelectedEvent(selectedEvent);

		TimelineModel dayRemakeModel = dayRemakeViewBean.getDayRemakeModel();

		dayRemakeModel.update(selectedEvent);
	}

	public void onAdd(TimelineAddEvent event) {
		dayRemakeViewBean.setSelectedEvent(null);
		dayRemakeViewBean.setAction("new");
		taskSessionModelBean.clear();
	}

	public void onDelete(TimelineModificationEvent event) {
		dayRemakeViewBean.setSelectedEvent(event.getTimelineEvent());
	}

	public void onEdit(TimelineModificationEvent event) {
		TimelineEvent selectedEvent = event.getTimelineEvent();
		dayRemakeViewBean.setSelectedEvent(selectedEvent);
		dayRemakeViewBean.setAction("edit");

		TaskSession selectedTaskSession = (TaskSession)selectedEvent.getData();

		try {
			taskSessionModelBean.setSelectedTask(selectedTaskSession.getTask());
			taskSessionModelBean.setStartTime(
				selectedTaskSession.getStartTime());

			taskSessionModelBean.setEndTime(selectedTaskSession.getEndTime());
			taskSessionModelBean.setDescription(
				selectedTaskSession.getDescription());
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public void onSelect(TimelineSelectEvent event) {
		dayRemakeViewBean.setSelectedEvent(event.getTimelineEvent());
	}

	public void doStartEndTimeValidate(
			FacesContext context, UIComponent component, Object value)
		throws ValidatorException {

		Date endTime = (Date)value;
		Date startTime = (Date)startTimeInput.getValue();
		Date currentDate = dayRemakeViewBean.getCurrentDate();

		FacesMessage facesMessage = null;

		try {
			List<TaskSession> taskSessions =
				TaskSessionLocalServiceUtil.getTaskSessionsByC_U_I(
					TimeSheetUtil.getCompanyId(),
					TimeSheetUtil.getCurrentUserId(),
					currentDate, DateTimeUtil.getIncrementedDay(currentDate));

			String action = dayRemakeViewBean.getAction();

			if (action.equals("new")) {
				TaskSessionValidatorUtil.validateAddTaskSession(
					taskSessions, startTime, endTime, currentDate);
			} else if (action.equals("edit")) {
				TaskSession selectedTaskSession =
					(TaskSession)dayRemakeViewBean.getSelectedEvent().getData();

				TaskSessionValidatorUtil.validateUpdateTaskSession(
					taskSessions, selectedTaskSession, startTime, endTime,
					currentDate);
			}
		} catch (Exception e) {
			if(e instanceof TSStartTimeException) {
				facesMessage = MessageUtil.getFacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"start _session _validation _error",
					"another _task _is _already _recorded _in _the _given _period");
			}
			else if(e instanceof TSNoStartTimeException) {
				facesMessage = MessageUtil.getFacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"start _session _validation _error",
					"no _start _time");
			}
			else if(e instanceof TSEndTimeException) {
				facesMessage = MessageUtil.getFacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"end _session _validation _error",
					"another _task _is _already _recorded _in _the _given _period");
			}
			else if(e instanceof TSNoEndTimeException) {
				facesMessage = MessageUtil.getFacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"end _session _validation _error",
					"no _end _time");
			}
			else if (e instanceof TSEarliestStartTimeException) {
				facesMessage = MessageUtil.getFacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"start _session _validation _error",
					"the _given _time _does _not _fit _the _time _frame _defined_for_"
						+ "work_start "
						+ PortletPropsValues.RESTRICTIONS_STARTTIME_EARLIEST);
			}
			else if (e instanceof TSStartEndTimeException) {
				facesMessage = MessageUtil.getFacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"task _session _validation _error",
					"start _and _end _time _exception");
				}
			else if (e instanceof TSWorkDurationException) {
				facesMessage = MessageUtil.getFacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"end _session _validation _error",
					"work _time _duration _exception");
			}
			else {
				facesMessage = MessageUtil.getFacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"validation _error",
					"exception");
			}
		}

		if (facesMessage != null) {
			throw new ValidatorException(facesMessage);
		}
	}

	public TaskModelBean getTaskModelBean() {
		return taskModelBean;
	}

	public void setTaskModelBean(TaskModelBean taskModelBean) {
		this.taskModelBean = taskModelBean;
	}

	public TaskSessionModelBean getTaskSessionModelBean() {
		return taskSessionModelBean;
	}

	public void setTaskSessionModelBean(
		TaskSessionModelBean taskSessionModelBean) {

		this.taskSessionModelBean = taskSessionModelBean;
	}

	public DayRemakeViewBean getDayRemakeViewBean() {
		return dayRemakeViewBean;
	}

	public UIInput getStartTimeInput() {
		return startTimeInput;
	}

	public void setStartTimeInput(UIInput startTimeInput) {
		this.startTimeInput = startTimeInput;
	}

	public UIInput getEndTimeInput() {
		return endTimeInput;
	}

	public void setEndTimeInput(UIInput endTimeInput) {
		this.endTimeInput = endTimeInput;
	}

	private UIInput startTimeInput = null;
	private UIInput endTimeInput = null;

	public void setDayRemakeViewBean(DayRemakeViewBean dayRemakeViewBean) {
		this.dayRemakeViewBean = dayRemakeViewBean;
	}

	@ManagedProperty(name = "dayRemakeViewBean",
		value = "#{dayRemakeViewBean}")
	private DayRemakeViewBean dayRemakeViewBean;
	@ManagedProperty(name = "taskModelBean",
		value = "#{taskModelBean}")
	private TaskModelBean taskModelBean;
	@ManagedProperty(name = "taskSessionModelBean",
		value = "#{taskSessionModelBean}")
	private TaskSessionModelBean taskSessionModelBean;

	private static final long serialVersionUID = 5620158902340242023L;
	private static Logger logger = LoggerFactory.getLogger(
		DayRemakeManagedBean.class);

}