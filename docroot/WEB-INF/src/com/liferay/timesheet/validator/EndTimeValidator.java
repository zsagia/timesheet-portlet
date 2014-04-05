package com.liferay.timesheet.validator;

import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;
import com.liferay.timesheet.CurrentTaskSessionIsAlreadyEndedException;
import com.liferay.timesheet.EndTimeException;
import com.liferay.timesheet.FutureStartTimeException;
import com.liferay.timesheet.NoCurrentTaskSessionException;
import com.liferay.timesheet.StartEndTimeException;
import com.liferay.timesheet.WorkDurationException;
import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.service.TaskSessionLocalServiceUtil;
import com.liferay.timesheet.util.MessageUtil;
import com.liferay.timesheet.util.PortletPropsValues;
import com.liferay.timesheet.util.TimeCalculatorUtil;
import com.liferay.timesheet.util.TimesheetUtil;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("EndTimeValidator")
public class EndTimeValidator implements Validator {

	private static final Logger logger =
		LoggerFactory.getLogger(EndTimeValidator.class);

	@Override
	public void validate(
			FacesContext context, UIComponent component, Object value)
		throws ValidatorException {

		if (value != null) {
			long userId = TimesheetUtil.getCurrentUserId();

			FacesMessage facesMessage = null;

			try {
				Date endTime = (Date)value;
				Date now = new Date();
				Date today = TimesheetUtil.getTodayWithoutTime();

				DateTimeValidatorUtil.validateFutureStartTime(endTime, now);
				DateTimeValidatorUtil.validateLatestEndTime(endTime);

				TaskSession lastTaskSession =
					TaskSessionLocalServiceUtil.getLastTaskSessionsByD_U(
						today, userId);

				if (lastTaskSession != null) {
					DateTimeValidatorUtil.validateEndTime(
						lastTaskSession, endTime);

					List<TaskSession> taskSessionList =
						TaskSessionLocalServiceUtil.getTaskSessionsByD_U(
							today, userId);

					DateTimeValidatorUtil.validateWorkDuration(
						TimeCalculatorUtil.summerizeDayTime(
							taskSessionList, endTime));
				}

			} catch (EndTimeException ete) {
				facesMessage = MessageUtil.getFacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"end_session_validation_error",
					"another_task_is_already_recorded"
						+ "_in_the_given_period");
			} catch (FutureStartTimeException fste) {
				facesMessage = MessageUtil.getFacesMessage(
						FacesMessage.SEVERITY_ERROR,
						"start_session_validation_error",
						"task_is_not_startable_in_the_future");
			} catch (NoCurrentTaskSessionException nctse) {
				facesMessage = MessageUtil.getFacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"end_session_validation_error",
					"there_is_not_current_task_session");
			} catch (CurrentTaskSessionIsAlreadyEndedException ctsiaee) {
				facesMessage = MessageUtil.getFacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"end_session_validation_error",
					"current_task_is_already_ended");
			} catch (StartEndTimeException sete) {
				facesMessage = MessageUtil.getFacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"start_session_validation_error",
					"The given time exceeds the restriction defined for "
						+ "finishing work: "
						+ PortletPropsValues.RESTRICTIONS_ENDTIME_LATEST);
			} catch(WorkDurationException wde) {
				facesMessage = MessageUtil.getFacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"end_session_validation_error",
					"work_time_duration_exception");
			} catch (Exception e) {
				logger.error(e);
			}

			if (facesMessage != null) {
				throw new ValidatorException(facesMessage);
			}
		}
	}

}