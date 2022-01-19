# demo DATABASE 생성하고 utf8mb4(모든문자, 한글 및 이모지 포함)
create database IF not exists demo 
default character set utf8mb4 collate utf8mb4_unicode_ci;
use demo;

drop table if exists users;
create table users (
	id int not null auto_increment,
    firstName varchar(20) default null, # 기본값 = null
    lastName varchar(20) default null,
    userName varchar(20) default null,	# 유저이름
    password varchar(20) default null,	# 비밀번호
    primary key(id)
);

drop table if exists todos;
create table todos (
	id int not null auto_increment,			# 자동 1씩 증가
    description varchar(255) default null, # 할일 설명
    is_done bit(1) not null,				# 완료 여부
    target_date datetime(6) default null,	# 목표 날짜
    username varchar(20) default null,		# 유저 이름
    title varchar(255) default null,		# 할 일명
    primary key(id)
);