<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String user = request.getParameter("user");
	String id = request.getParameter("id");
	// jsp 에는 out 객체가 만들어져있기때문에 선언하지않고 사용가능
	out.println("유저 파라미터 : " + user);
	out.println("아이디 파라미터 : " + id);
	%>
</body>
</html>