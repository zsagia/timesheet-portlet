create table timesheet_Task (
	taskId LONG not null primary key,
	companyId LONG,
	userId LONG,
	startDate DATE null,
	endDate DATE null,
	taskName VARCHAR(75) null
);