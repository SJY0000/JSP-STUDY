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

<!-- java Bean에 값을 입력하기(set메소드) -->
<jsp:setProperty property="email" name="user1" value="afaf@naver.com"/>
<jsp:setProperty property="password" name="user1" value="abcd"/>

<jsp:forward page="getbeansrequest.jsp" />
</body>
</html>