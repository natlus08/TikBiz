insert into TMS_USER(id, FIRST_NAME, LAST_NAME, PASSWORD, USER_NAME, ROLE) values(1, 'hameedu', 'sultan', '123456', 'kaderh', 'SUPPORT');
insert into TMS_USER(id, FIRST_NAME, LAST_NAME, PASSWORD, USER_NAME, ROLE) values(2, 'vinoth', 'kumar', '123456', 'gurusam', 'SUPPORT');
insert into TMS_USER(id, FIRST_NAME, LAST_NAME, PASSWORD, USER_NAME, ROLE) values(3, 'ravi', 'kumar', '123456', 'maruthr', 'SUPPORT');
insert into TMS_USER(id, FIRST_NAME, LAST_NAME, PASSWORD, USER_NAME, ROLE) values(4, 'santosh', 'balakrishnan', '123456', 'balaksa', 'SUPPORT');
insert into TMS_USER(id, FIRST_NAME, LAST_NAME, PASSWORD, USER_NAME, ROLE) values(5, 'murali', 'manohar', '123456', 'marimum', 'SUPPORT');


insert into TMS_TICKET(id, CREATED_BY, DESCRIPTION, MODIFIED_BY, PRIORITY, STATUS, CREATED_DATE) values(1, 'kaderh', 'INC1', '', 'P1', 'NEW', '2017-07-20 06:14:00.742000000');
insert into TMS_TICKET(id, CREATED_BY, DESCRIPTION, MODIFIED_BY, PRIORITY, STATUS, CREATED_DATE) values(2, 'gurusam', 'INC2', '', 'P4', 'CLOSED', '2017-07-02 18:14:00.742000000');
insert into TMS_TICKET(id, CREATED_BY, DESCRIPTION, MODIFIED_BY, PRIORITY, STATUS, CREATED_DATE) values(3, 'maruthr', 'INC3', '', 'P1', 'NEW', '2017-07-18 06:14:00.742000000');
insert into TMS_TICKET(id, CREATED_BY, DESCRIPTION, MODIFIED_BY, PRIORITY, STATUS, CREATED_DATE) values(4, 'maruthr', 'INC4', '', 'P1', 'INPROGRESS', '2017-07-25 09:14:00.742000000');
insert into TMS_TICKET(id, CREATED_BY, DESCRIPTION, MODIFIED_BY, PRIORITY, STATUS, CREATED_DATE) values(5, 'kaderh', 'INC5', '', 'P3', 'NEW', '2017-07-02 06:14:00.742000000');
insert into TMS_TICKET(id, CREATED_BY, DESCRIPTION, MODIFIED_BY, PRIORITY, STATUS, CREATED_DATE) values(6, 'kaderh', 'INC6', '', 'P2', 'INPROGRESS', '2017-07-08 23:14:00.742000000');
insert into TMS_TICKET(id, CREATED_BY, DESCRIPTION, MODIFIED_BY, PRIORITY, STATUS, CREATED_DATE) values(7, 'balaksa', 'INC7', '', 'P2', 'INPROGRESS', '2017-07-02 06:14:00.742000000');
insert into TMS_TICKET(id, CREATED_BY, DESCRIPTION, MODIFIED_BY, PRIORITY, STATUS, CREATED_DATE) values(8, 'maruthr', 'INC8', '', 'P4', 'NEW', '2017-07-27 00:14:00.742000000');

insert into SHIFT_ROASTER(id, DATE) values (1,'2017-07-31');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(1, 'MORNING', 'kaderh,marimum');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(1, 'NOON', 'gurusam');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(1, 'NIGHT', 'maruthr');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(1, 'OFF', 'balaksa');

insert into SHIFT_ROASTER(id, DATE) values (2,'2017-08-01');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(2, 'MORNING', 'balaksa');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(2, 'NOON', 'gurusam,marimum');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(2, 'NIGHT', 'maruthr');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(2, 'OFF', 'kaderh');

insert into SHIFT_ROASTER(id, DATE) values (3,'2017-08-02');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(3, 'MORNING', 'gurusam');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(3, 'NOON', 'balaksa');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(3, 'NIGHT', 'maruthr,marimum');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(3, 'OFF', 'kaderh');

insert into SHIFT_ROASTER(id, DATE) values (4,'2017-08-03');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(4, 'MORNING', 'gurusam');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(4, 'NOON', 'balaksa');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(4, 'OFF', 'maruthr,marimum');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(4, 'NIGHT', 'kaderh');

insert into SHIFT_ROASTER(id, DATE) values (5,'2017-08-04');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(5, 'MORNING', 'gurusam');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(5, 'NOON', 'balaksa');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(5, 'OFF', 'maruthr');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(5, 'NIGHT', 'kaderh,marimum');

insert into SHIFT_ROASTER(id, DATE) values (6,'2017-08-05');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(6, 'MORNING', 'gurusam');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(6, 'NOON', 'balaksa');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(6, 'OFF', 'maruthr');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(6, 'NIGHT', 'kaderh,marimum');

insert into SHIFT_ROASTER(id, DATE) values (7,'2017-08-06');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(7, 'MORNING', 'gurusam');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(7, 'NOON', 'balaksa');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(7, 'OFF', 'maruthr');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(7, 'NIGHT', 'kaderh,marimum');
