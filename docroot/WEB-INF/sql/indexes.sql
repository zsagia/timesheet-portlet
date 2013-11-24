create index IX_3F299F0A on timesheet_Task (companyId, userId);
create index IX_ED5CDD36 on timesheet_Task (taskName, userId);
create index IX_39208692 on timesheet_Task (userId);

create index IX_19221F9B on timesheet_TaskSession (userId, startTime);