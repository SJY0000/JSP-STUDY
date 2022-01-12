<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>홈페이지</h1>
	
	<!-- Servlet, JSP 둘 다 사용한 MVC모델2 -->
	<!-- 상위 폴더 위치가 변경되도 정상적으로 실행될 수 있게 경로출력 -->
	<p><a href="<%=request.getContextPath() %>/Controller?action=login">로그인</a></p>
	<p><a href="<%=request.getContextPath() %>/Controller?action=about">어바웃</a></p>
</body>
</html>