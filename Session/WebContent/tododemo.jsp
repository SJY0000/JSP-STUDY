<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="tododemo.jsp">
새 할일 : <input type="text" name="theItem" />
<input type="submit" value="입력">
</form>
<%
	// 먼저 SESSION에 todo리스트가 있는지 확인
	List<String> items = (List<String>)session.getAttribute("todoList");

	// 만약에 todo리스트가 없다면 새로 만들기
	if (items == null) {
		items = new ArrayList<String>();
		session.setAttribute("todoList", items);
	}
	// 입력한 할 일을 받아서 리스트에 추가 그리고 그 리스트를 SESSION에 저장
	String theItem = request.getParameter("theItem");
	// 입력한 할일이 null 이나 공백이 아니면 입력
	if (theItem != null && !theItem.trim().equals("")) {
		// 리스트에 같은 데이터가 있으면 입력 안됨
		if (!items.contains(theItem.trim())) {
			items.add(theItem);
			session.setAttribute("todoList", items);	
		}
	}
%>
<hr>
<b>할일 리스트 : </b>
<ol>
<%
	for(String s : items) {
		out.println("<li>" + s + "</li>");
	}
%>
</ol>
</body>
</html>