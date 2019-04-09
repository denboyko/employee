use dbTest;

create table if not exists department
(
  depID   int auto_increment
    primary key,
  depName varchar(255) not null,
  constraint department_depID_uindex
  unique (depID)
);

create table if not exists employee
(
  empID   int auto_increment
    primary key,
  empName varchar(255)           not null,
  active  tinyint(1) default '0' not null,
  depID   int                    null,
  constraint table_name_empID_uindex
  unique (empID),
  constraint employee_department_depID_fk
  foreign key (depID) references dbtest.department (depid)
);