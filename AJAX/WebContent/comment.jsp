<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String Email = request.getParameter("email");
	String comment = request.getParameter("comment");
	
	if (comment.trim().equals("") || Email.trim().equals("")) { // Email에 @, . 이 있을 경우
			out.print("<p>댓글 내용과 Email을 확인해주세요.");
	} else {
		try {
			String url = "jdbc:mysql://localhost:3306/demo?useSSL=false";
			Connection conn = DriverManager.getConnection(url, "root", "1234");
			PreparedStatement pstmt = conn.prepareStatement("insert into comment (comment, email) values (?, ?)");
			
			pstmt.setString(1, comment);
			pstmt.setString(2, Email);
			
			int i = pstmt.executeUpdate(); // 1일 떄 정상
			
			pstmt = conn.prepareStatement("SELECT * FROM comment ORDER BY id DESC");
			
			ResultSet rs = pstmt.executeQuery();
			
			out.print("<hr>");
			out.print("<h2>Comments : </h2>");
			while (rs.next()) {
				out.print("<div class='box'>");
				out.print("<p>" + rs.getString("comment") + "</p>");
				out.print("<p><strong>글쓴이 : " + rs.getString("email") + "</strong></p>");
				out.print("</div>");
			}
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
%> 