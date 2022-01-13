<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>로그인 페이지</h1>
<form action="/MVCform/Controller" method="post">

<input type="hidden" name="action" value="dologin">  <!-- 로그인 실패시 실패했던 email, password를 그대로 남기기 위해 -->
<input type="text" name="email" placeholder="email" value="<%=request.getAttribute("email") %>"><br>
<input type="text" name="password" placeholder="password" value="<%=request.getAttribute("password") %>"><br>
<input type="submit" value="전송">
</form>
<h2><%= request.getAttribute("valmessage") %></h2>
</body>
</html>