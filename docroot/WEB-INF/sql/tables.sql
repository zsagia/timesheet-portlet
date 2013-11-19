create table timesheet_Task (
	taskId LONG not null primary key,
	userId LONG,
	taskName VARCHAR(75) null
);

create table timesheet_TaskSession (
	taskSessionId LONG not null primary key,
	taskId LONG,
	startTime DATE null,
	endTime DATE null
);