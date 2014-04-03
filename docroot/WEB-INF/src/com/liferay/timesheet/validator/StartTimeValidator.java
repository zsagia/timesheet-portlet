package com.liferay.timesheet.validator;

import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;
import com.liferay.timesheet.EarliestStartTimeException;
import com.liferay.timesheet.StartEndTimeException;
import com.liferay.timesheet.StartTimeException;
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

/**
* @author Istvan Sajtos
* @author Zsolt Szabo
*/

@FacesValidator("StartTimeValidator")
public class StartTimeValidator implements Validator {
	
	private static final Logger logger =
		LoggerFactory.getLogger(StartTimeValidator.class);

	@Override
	public void validate(
			FacesContext context, UIComponent component, Object value)
		throws ValidatorException {

		if (value != null) {
			long userId = TimesheetUtil.getCurrentUserId();

			FacesMessage facesMessage = null;

			try {
				Date todayWithoutTime = TimesheetUtil.getTodayWithoutTime();

				TaskSession lastTaskSession =
					TaskSessionLocalServiceUtil.getLastTaskSessionsByD_U(
						todayWithoutTime, userId);

				if (lastTaskSession != null) {
					List<TaskSession> taskSessionList =
						TaskSessionLocalServiceUtil.getTaskSessionsByD_U(
							todayWithoutTime, userId);

					DateTimeValidatorUtil.validateWorkDuration(
						TimeCalculatorUtil.summerizeDayTime(
							taskSessionList, (Date)value));

					DateTimeValidatorUtil.validateLatestEndTime((Date)value);
					DateTimeValidatorUtil.validateStartTime(
						lastTaskSession, (Date)value);
				}
				else {
					DateTimeValidatorUtil.validateWorkStart((Date)value);
				}
			} catch (StartTimeException ste) {
				facesMessage = MessageUtil.getFacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"start_session_validation_error",
					"another_task_is_already_recorded"
						+ "_in_the_given_period");
			} catch (EarliestStartTimeException este) {
				facesMessage = MessageUtil.getFacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"start_session_validation_error",
					"the_given_time_does_not_fit_the_time_frame_defined_for_"
						+ "work_start "
						+ PortletPropsValues.RESTRICTIONS_STARTTIME_EARLIEST);
			} catch (StartEndTimeException sete) {
				facesMessage = MessageUtil.getFacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"start_session_validation_error",
					"The given time exceeds the restriction defined for "
						+ "starting work: "
						+ PortletPropsValues.RESTRICTIONS_ENDTIME_LATEST);
			} catch (WorkDurationException wde) {
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