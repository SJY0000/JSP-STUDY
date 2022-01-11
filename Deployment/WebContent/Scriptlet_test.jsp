<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- Scriptlet 태그는 여러줄의 java코드를 실행 --%>
<%
    for(int i = 0; i <= 5; i++) {
        out.println("<br> JSP & Servlet : " + i);
    }
%>
</body>
</html>