package demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/Connect")
public class Connect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8"); // 한글 설정(servlet으로 한글 출력하기위해 필요)
	
		PrintWriter out = response.getWriter();
		
		Connection conn = null;
		
		try {
			// 0. 드라이버 로딩(생략가능)
			Class.forName("com.mysql.jdbc.Driver");
			// 1. DB연결
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop?useSSL=false", "root", "1234");
			
		} catch (SQLException e) {
			out.println("DB에 연결 실패");
			return;
		} catch (ClassNotFoundException e) {
			out.println("Driver Class를 찾을 수 없습니다.");
			return;
		}	
		out.println("DB연결 성공");
		
		try {
			conn.close();
		} catch (SQLException e) {
			out.println("DB연결 종료 실패");
		}
	}
}
