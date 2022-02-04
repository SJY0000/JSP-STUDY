CREATE DATABASE IF NOT EXISTS bbs DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
use demo;
DROP TABLE IF EXISTS bduser;
CREATE TABLE bduser 
( 
	userID 		 VARCHAR(20),
    userPassword VARCHAR(20),
    userName 	 VARCHAR(20),
    userGender 	 VARCHAR(20),
    userEmail 	 VARCHAR(50),
    PRIMARY KEY (userID)
);

insert into bduser values('admin', '1234', '관리자', '남자', 'admin@naver.com');

