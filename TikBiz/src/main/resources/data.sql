insert into TMS_USER(id, FIRST_NAME, LAST_NAME, PASSWORD, USER_NAME, ROLE, MOBILENUMBER) values(1, 'hameedu', 'sultan', '123456', 'kaderh', 'SUPPORT', '9600074749');
insert into TMS_USER(id, FIRST_NAME, LAST_NAME, PASSWORD, USER_NAME, ROLE, MOBILENUMBER) values(2, 'vinoth', 'kumar', '123456', 'gurusam', 'SUPPORT', '9600074749');
insert into TMS_USER(id, FIRST_NAME, LAST_NAME, PASSWORD, USER_NAME, ROLE, MOBILENUMBER) values(3, 'ravi', 'kumar', '123456', 'maruthr', 'SUPPORT', '9600074749');
insert into TMS_USER(id, FIRST_NAME, LAST_NAME, PASSWORD, USER_NAME, ROLE, MOBILENUMBER) values(4, 'santosh', 'balakrishnan', '123456', 'balaksa', 'SUPPORT', '9600074749');
insert into TMS_USER(id, FIRST_NAME, LAST_NAME, PASSWORD, USER_NAME, ROLE, MOBILENUMBER) values(5, 'murali', 'manohar', '123456', 'marimum', 'SUPPORT', '9600074749');
insert into TMS_USER(id, FIRST_NAME, LAST_NAME, PASSWORD, USER_NAME, ROLE, MOBILENUMBER) values(6, 'kumar', 'karthik', '123456', 'karku', 'SUPPORT-LEAD', '9600074749');
insert into TMS_USER(id, FIRST_NAME, LAST_NAME, PASSWORD, USER_NAME, ROLE, MOBILENUMBER) values(7, 'shanmugam', 'karthikeyan', '123456', 'karsha', 'SUPPORT-MANAGER', '9600074749');
insert into TMS_USER(id, FIRST_NAME, LAST_NAME, PASSWORD, USER_NAME, ROLE, MOBILENUMBER) values(8, 'velan', 'vetri', '123456', 'vetve', 'USER', '9600074749');
insert into TMS_USER(id, FIRST_NAME, LAST_NAME, PASSWORD, USER_NAME, ROLE, MOBILENUMBER) values(9, 'daniel', 'jack', '123456', 'jacda', 'USER', '9600074749');


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

insert into SHIFT_ROASTER(id, DATE) values (8,'2017-08-07');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(8, 'NOON', 'kaderh,marimum');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(8, 'MORNING', 'gurusam');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(8, 'NIGHT', 'maruthr');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(8, 'OFF', 'balaksa');

insert into SHIFT_ROASTER(id, DATE) values (9,'2017-08-08');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(9, 'MORNING', 'balaksa');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(9, 'NOON', 'gurusam,marimum');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(9, 'NIGHT', 'maruthr');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(9, 'OFF', 'kaderh');

insert into SHIFT_ROASTER(id, DATE) values (10,'2017-08-09');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(10, 'MORNING', 'gurusam');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(10, 'NOON', 'balaksa');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(10, 'NIGHT', 'maruthr,marimum');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(10, 'OFF', 'kaderh');

insert into SHIFT_ROASTER(id, DATE) values (11,'2017-08-10');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(11, 'MORNING', 'gurusam');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(11, 'NOON', 'balaksa');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(11, 'OFF', 'maruthr,marimum');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(11, 'NIGHT', 'kaderh');

insert into SHIFT_ROASTER(id, DATE) values (12,'2017-08-11');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(12, 'MORNING', 'gurusam');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(12, 'NOON', 'balaksa');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(12, 'OFF', 'maruthr');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(12, 'NIGHT', 'kaderh,marimum');

insert into SHIFT_ROASTER(id, DATE) values (13,'2017-08-12');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(13, 'MORNING', 'gurusam');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(13, 'NOON', 'balaksa');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(13, 'OFF', 'maruthr');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(13, 'NIGHT', 'kaderh,marimum');

insert into SHIFT_ROASTER(id, DATE) values (14,'2017-08-13');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(14, 'MORNING', 'gurusam');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(14, 'NOON', 'balaksa');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(14, 'OFF', 'maruthr');
insert into ShiftRoaster_shifts(ShiftRoaster_id, shifts_KEY, shifts) values(14, 'NIGHT', 'kaderh,marimum');
