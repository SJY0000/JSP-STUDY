<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p> <c:out value="${user1.name}"/>
<p> <c:out value="${user2.name}"/>
<p> <c:out value="${user3.name}"/>
<br><br>
<!-- map list -->
<p> <c:out value="${maplist.fruit1 }"/>
<p> <c:out value="${maplist.fruit2 }"/>
<p> <c:out value="${maplist['fruit1'] }"/>
<p> <c:out value="${maplist['fruit2'] }"/>
<br><br>
<!-- list -->
<c:forEach var="item" items="${list}" >
	${item.id} : ${item.name} <br>
</c:forEach>
<br><br>
<!-- 테이블(표) 안에 입력 -->
<table border = "1">
<tr>
	<th>Id</th><th>이름</th>
</tr>
<c:forEach var="item" items="${list}">
<tr>
	<td>${item.id}</td> <td>${item.name}</td>
</tr>
</c:forEach>
</table>
</body>
</html>