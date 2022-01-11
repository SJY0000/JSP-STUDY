<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	영문 대문자로 변환 : <%= new String("Hello World!").toUpperCase() %>
    <br><br>
    25 곱하기 4 : <%= 25 * 4 %>
    <br><br>
    1이 2보다 큰가 ? <%= 1 > 2 %>
    <br><br>
    <%-- Scriptlet 태그는 여러줄의 java코드를 실행 --%>
<%
    for(int i = 0; i <= 5; i++) {
        out.println("<br> JSP & Servlet : " + i);
    }
%>
</body>
</html>