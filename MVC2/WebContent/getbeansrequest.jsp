<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- jsp에 Java Bean 객체 만들기 -->
<!-- id는 객체의 이름, class는 beans객체, scope는 범위 -->
<jsp:useBean id="user1" class="beans.User" scope="request"/>

<!-- java Bean에 값을 가져오기 -->
이메일 : <%= user1.getEmail() %>
패스워드 : <%= user1.getPassword() %>
</body>
</html>