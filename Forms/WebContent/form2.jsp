<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- action의 주소로 form안의 입력창에 입력한 데이터와 함께 요청 -->	
<form action="/Forms/formhandler.jsp" method="get">
<input type="text" name="user" placeholder="유저"><br>
<input type="password" name="password" placeholder="패스워드"><br>
<input type="submit" value="전송">

</form>
</body>
</html>