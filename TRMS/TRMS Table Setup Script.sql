-- Create database

drop table if exists reimbursement_form;
drop table if exists employee;
drop table if exists department;
drop table if exists event_type;
drop type if exists event_type_enum;



create table department
(
	dep_id serial not null,
	dep_name varchar(50),
	dep_head_id int,
);

create table employee
(
	employee_id serial not null,
	first_name varchar(255),
	last_name varchar(255),
	user_name varchar(255) unique,
	pass_word varchar(255),
	email varchar(255),
	dep_id int,
	reports_to int,
	is_ben_co bool,
	is_dep_head bool,
	is_supervisor bool,
	constraint PK_employee_id primary key (employee_id),
	constraint FK_dep_id foreign key (dep_id) references department (dep_id) on delete cascade on update cascade
);

create type event_type_enum as enum 
(
'univercity_course',
'seminar',
'certification_preparation_class', 
'certification', 
'other'
);

create table reimbursement_form 
(
	form_id serial not null, 
	employee_id int,
	event_type event_type_enum,
	event_date date,
	event_time time,
	event_location varchar(255),
	event_description varchar(1000),
	event_attach bytea,
	event_cost decimal,
	justification varchar(1000),
	grading_format varchar(255),
	grade varchar(1000),
	pre_approval_attach bytea,
	hours_missed decimal,
	reimbursment_amount decimal,
	status varchar(255),
	supervisor_id int;
	supervisor_approval bool,
	dep_head_approval bool,
	ben_co_approval bool,
	rejected bool default false,
	rej_reason varchar(1000),
	constraint PK_form_id primary key (form_id),
	constraint FK_employee_id foreign key (employee_id) references employee (employee_id) on delete cascade on update cascade,
	constraint FK_supervisor_id foreign key (supervisor_id) references employee (employee_id) on delete cascade on update cascade,
);

insert into department (dep_name)
values ('Production'), ('Research and Development'), ('Benefits Coordinator');

--insert a ben-co dep-head, then ben-supervisor, then supervisor, then employee.
insert into employee (first_name, last_name, user_name, pass_word, email, dep_id, reports_to, is_ben_co, is_dep_head, is_supervisor) 
values ('Zach', 'Thomson', 'thomsonz', 'hunter2', 'thomsonz@company.com', (select dep_id from department where dep_name = 'Benefits Coordinator'), null, true, false, false); 

insert into employee (first_name, last_name, user_name, pass_word, email, dep_id, reports_to, is_ben_co, is_dep_head, is_supervisor) 
values ('John', 'Smith', 'smithj', 'hunter2', 'smithJ@company.com', (select dep_id from department where dep_name = 'Production'), null, false, true, true);

insert into employee (first_name, last_name, user_name, pass_word, email, dep_id, reports_to, is_ben_co, is_dep_head, is_supervisor) 
values ('Matt', 'Stone', 'stonem', 'hunter2', 'stonem@company.com', (select dep_id from department where dep_name = 'Production'), (select employee_id from employee where user_name = 'smithj'), false, false, true);

insert into employee (first_name, last_name, user_name, pass_word, email, dep_id, reports_to, is_ben_co, is_dep_head, is_supervisor) 
values ('Kendra', 'Ferris', 'ferrisk', 'hunter2', 'ferrisk@company.com', (select dep_id from department where dep_name = 'Production'), (select employee_id from employee where user_name = 'stonem'), false, false, false);

insert into reimbursement_form 
(employee_id, event_type, event_date, event_time, event_location, event_description, 
event_attach, event_cost, justification, grading_format, grade, pre_approval_attach,
hours_missed, reimbursment_amount, status, supervisor_id, supervisor_approval, 
dep_head_approval, ben_co_approval, rejected, rej_reason)
values 
(2, 'seminar', '2020-12-01', '13:00:00', '123 something st. Corvallis, Oregon, 97330', 'A seminar to teach about a new production tool.',
null, 100, 'This seminar will help me understand advanced product production using this great new tool!', 'report', null, null, 
'8', 60, 'pending', true, 
true, false, false, 2, null),
(4, 'seminar', '2020-12-01', '13:00:00', '123 something st. Corvallis, Oregon, 97330', 'A seminar to teach about a new production tool.',
null, 100, 'This seminar will help me understand advanced product production using this great new tool!', 'report', null, null, 
'8', 60, 'pending', 3, false, 
false, false, false, null);
