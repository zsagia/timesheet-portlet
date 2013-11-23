create table timesheet_Task (
	taskId LONG not null primary key,
	userId LONG,
	taskName VARCHAR(75) null
);

create table timesheet_TaskSession (
	taskSessionId LONG not null primary key,
	endTime DATE null,
	startTime DATE null,
	taskId LONG,
	userId LONG
);