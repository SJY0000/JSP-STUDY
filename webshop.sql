# database webshop이 없으면 새로 생성 utf8mb4 한글 뿐만 아니라 이모지 포함한 문자가능
create database if not exists webshop 
default character set utf8mb4 collate utf8mb4_unicode_ci;

use webshop; # webshop을 선택

# Create table user가 있으면 삭제하고 새로 만듬
Drop table if exists user;
create table users (
	id int primary key auto_increment, # 유저아이디 
    email varchar(50),				   # 이메일
    password varchar(50)			   # 비번
);
