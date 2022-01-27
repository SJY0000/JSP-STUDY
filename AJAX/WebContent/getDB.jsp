<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String strID = request.getParameter("id");
	
	if (strID == null || strID.trim().equals("")) {
		out.print("ID를 입력해주세요.");
	} else {
		int id = Integer.parseInt(strID);
		
		try {
			String url = "jdbc:mysql://localhost:3306/demo?useSSL=false";
			String sql = "select * from emp where id=?";
			Connection conn = DriverManager.getConnection(url, "root", "1234");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				out.print(rs.getInt("id") + " " + rs.getString("name"));
			} else {
				out.print("TABLE에 해당 ID가 없습니다.");
			}
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
%> 