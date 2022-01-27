<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String Email = request.getParameter("email");
	
	if (Email.contains("@") && Email.contains(".")) { // Email에 @, . 이 있을 경우
		try {
			String url = "jdbc:mysql://localhost:3306/demo?useSSL=false";
			String sql = "select * from members where email=?";
			Connection conn = DriverManager.getConnection(url, "root", "1234");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, Email);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				out.print("이미 가입된 Email이 있습니다.");
			} else {
				out.print("사용가능한 Email입니다.");
			}
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} else {
		out.print("잘못된 Email 형식입니다.");
	
	}
%> 