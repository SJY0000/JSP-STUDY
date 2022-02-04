<%@page import="javax.annotation.Resource"%>
<%@page import="userdao.UserDAO"%>
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

		UserDAO userDAO = new UserDAO();
		int result = userDAO.login(user.getUserID(), user.getUserPassword());
		if (result == 1 ) {
			out.println("<script>");
			out.println("location.href = 'main.jsp'");
			out.println("</script>");
		} else if (result == 0) {
			out.println("<script>");
			out.println("alert('비밀번호가 틀립니다.')");
			out.println("history.back()");
			out.println("</script>");
		} else if (result == -1) {
			out.println("<script>");
			out.println("alert('존재하지 않는 아이디입니다.')");
			out.println("history.back()");
			out.println("</script>");
		} else if (result == -2) {
			out.println("<script>");
			out.println("alert('데이터베이스 오류가 발생했습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
	%>
</body>
</html>