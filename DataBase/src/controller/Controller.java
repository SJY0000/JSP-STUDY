package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.security.auth.login.AccountException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name = "jdbc/webshop") 
	private DataSource ds; // DataSource ds로 DB연결

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		
		if(action == null) {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} else if (action.equals("login")) {
			request.setAttribute("email", "");
			request.setAttribute("password", "");
			request.setAttribute("message", "");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8"); // 한글 설정(servlet으로 한글 출력하기위해 필요)
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		
		if(action == null) {
			out.println("알 수 없는 요청입니다.");
			return;
		}
		
		Connection conn = null;
		
		try {
			conn = ds.getConnection(); // DB연결
		} catch (SQLException e) {
			out.println("DB에 연결 실패");
			return;
		}	
		Account account = new Account(conn); // 
		
		if (action.equals("dologin")) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			request.setAttribute("email", email);
			request.setAttribute("password", "");
			
			try {
				if (account.login(email, password)) {
					request.getRequestDispatcher("/loginsuccess.jsp").forward(request, response);	
				} else {
					request.setAttribute("message", "email 또는 password가 틀립니다.");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("message", "DB 에러 발생");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		}
		
		try {
			conn.close(); // 실제로는 닫는 것이 아니라 Conection pool로 보냄
		} catch (SQLException e) {
			out.println("DB연결 종료 실패");
		}
	}

}
