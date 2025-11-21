create database student;
use  student;

create table  student (
                          id int primary key auto_increment,
                          name char(45) not null,
                          score double
);

insert into  student  (name, score) values ('Lợi', 2.0);
insert into student (name, score) values ('Linh', 2.5);

select id, name, score from student;

select id, name, score from student where  id = ?;

insert into student (name, score) values (?, ?);