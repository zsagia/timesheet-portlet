package com.liferay.timesheet.validator;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.service.TaskSessionLocalServiceUtil;
import com.liferay.timesheet.util.MessageUtil;
import com.liferay.timesheet.util.TimesheetUtil;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("TaskSessionTimeValidator")
public class TaskSessionTimeValidator implements Validator {

	@Override
	public void validate(
		FacesContext context, UIComponent component, Object value)
		throws ValidatorException {

		long userId = TimesheetUtil.getCurrentUserId();

		try {
			List<TaskSession> todayTaskSessions = 
				TaskSessionLocalServiceUtil.getTaskSessionsByD_U(
						new Date(), userId);
			for (TaskSession taskSession : todayTaskSessions) {
				Date startTime = taskSession.getStartTime();
				Date endTime = taskSession.getEndTime();
				if (startTime.before((Date)value) && 
					((endTime != null) && endTime.after((Date)value))) {
					
					FacesMessage message = MessageUtil.getMessage(
						"com.liferay.timesheet.bean.messages", 
						"another_task_is_already_recorded_in_the_given_period", 
						null);
					
					message.setSeverity(FacesMessage.SEVERITY_ERROR);
					throw new ValidatorException(message);
				}
			}
		} catch (SystemException e) {
			e.printStackTrace();
		}

	}
}