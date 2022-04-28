create table emp(
  empId    number(4,0),
  ename    varchar2(10),
  sal      number(7,2)
  constraint pk_emp primary key (empId).
  constraint fk_deptId foreign key (deptId) references dept (deptId)

);

create table dept(
  deptId number(2,0),
  dname  varchar2(14),
  loc    varchar2(13),
  constraint pk_dept primary key (deptId)
);

create table project(
  projectId    number(4,0),
  projName    varchar2(10),
  deptno   number(2,0),
  constraint pk_emp primary key (projectId),
  constraint fk_deptId foreign key (deptId) references dept (deptId)
  constraint fk_empId foreign key (empId) references dept (empId)
);
 

 