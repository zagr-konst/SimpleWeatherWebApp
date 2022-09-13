create table city (cityid int8 not null, city_name varchar(255), country varchar(255), region varchar(255), primary key (cityid));
create table users (id  bigserial not null, email varchar(255), first_name varchar(255), last_name varchar(255), password varchar(255), city int8, primary key (id));
alter table if exists users add constraint FKcs9whtendqffwjvjel7jybj2m foreign key (city) references city;
create table city (cityid int8 not null, city_name varchar(255), country varchar(255), region varchar(255), primary key (cityid));
create table users (id  bigserial not null, email varchar(255), first_name varchar(255), last_name varchar(255), password varchar(255), city int8, primary key (id));
alter table if exists users add constraint FKcs9whtendqffwjvjel7jybj2m foreign key (city) references city;
