package demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/DatasourceDemo")
public class DatasourceDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/webshop") 
	private DataSource ds; // DataSource ds로 DB연결
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8"); // 한글 설정(servlet으로 한글 출력하기위해 필요)
		
		PrintWriter out = response.getWriter();
		
		Connection conn = null;
		
		try {
			// 1. DB연결
			conn = ds.getConnection();
			
		} catch (SQLException e) {
			out.println("DB에 연결 실패");
			return;
		}	
		out.println("DB연결 성공");
		
		try {
			conn.close(); // 실제로는 닫는 것이 아니라 Conection pool로 보냄
		} catch (SQLException e) {
			out.println("DB연결 종료 실패");
		}
	}


}
