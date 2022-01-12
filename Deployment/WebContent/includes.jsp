<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--  ctrl + shift + / 주석 -->
	<!-- 정적 include : 미리 compile 해둠, 자주 바꾸지 않을 때 -->
	<%@ include file = "copyright.txt" %>
	<br>
	<!-- 동적 include : 볼 때마다 새로고침, 자주 수정할 때-->
	<jsp:include page="update.txt"/>
	
	<!-- 자바 변수등을 넣으려면 정적 include를 사용 -->
	<%@ include file = "variable.jsp" %>
	<%= name %>
	
	<!-- 실행시(runtime)에 두 개의 html 중에 id가 있을 경우 login.html 없을 경우 fail.html -->
	<% String id = request.getParameter("id"); %>
	<!-- 동적 include 사용 시 html 한글이 안깨짐 -->
	<% if(id == null) { %>
		<jsp:include page = "fail.html" />	
	<% } else { %>
		<jsp:include page = "login.html" />
	<% } %>
	
</body>
</html>