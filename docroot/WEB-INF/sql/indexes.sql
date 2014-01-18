create index IX_F1CF7648 on timesheet_Project (parentProjectId);
create index IX_F508FA42 on timesheet_Project (uuid_);

create index IX_F3AE5F87 on timesheet_Task (companyId, creatorId);
create index IX_3F299F0A on timesheet_Task (companyId, userId);
create index IX_6A90776C on timesheet_Task (projectId);
create index IX_D0DE61DB on timesheet_Task (taskName, creatorId);
create index IX_ED5CDD36 on timesheet_Task (taskName, userId);
create index IX_39208692 on timesheet_Task (userId);

create index IX_AD36F903 on timesheet_TaskSession (startTime, endTime);
create index IX_2C1C7C24 on timesheet_TaskSession (userId);
create unique index IX_FC7413D4 on timesheet_TaskSession (userId, endTime);
create index IX_19221F9B on timesheet_TaskSession (userId, startTime);