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

import beans.User;

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
		} else if (action.equals("createaccount")) {
			request.setAttribute("email", "");
			request.setAttribute("message", "");
			request.getRequestDispatcher("/createaccount.jsp").forward(request, response);
		} else {
			out.println("없는 action입니다.");
			return;
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
		else if(action.equals("createaccount")) { // 가입하기 페이지에서 작성 후 가입하기버튼 클릭 시
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String repeatpassword = request.getParameter("repeatpassword");
			request.setAttribute("email", email); // email 주소를 request에 저장 
			
			// password를 두 번 적을 때 서로 같지 않을 경우
			if (!password.equals(repeatpassword)) {
				request.setAttribute("message", "password가 같지 않습니다.");
				request.getRequestDispatcher("/createaccount.jsp").forward(request, response);
			} else {
				User user = new User(email, password);
				
				// 유효성 검사 불합격 했을 경우
				if (!user.validate()) {
					request.setAttribute("message", user.getMessage());
					request.getRequestDispatcher("/createaccount.jsp").forward(request, response);
				}
				else {// 합격 했을 경우 email 중복 확인 후 새 계정 만들기
					try {
						if (account.exists(email)) { // 중복이 있을 경우
							request.setAttribute("message", "이미 존재하는 email입니다.");
							request.getRequestDispatcher("/createaccount.jsp").forward(request, response);
						} else { // 새 계정을 만들기
							account.create(email, password);
							request.getRequestDispatcher("/createsuccess.jsp").forward(request, response);
						}
					} catch (SQLException e) { // SQL에러 발생 시
						request.setAttribute("message", "SQL에러 발생");
						request.getRequestDispatcher("/error.jsp").forward(request, response);
					}
				}
			}
		try {
			conn.close(); // 실제로는 닫는 것이 아니라 Conection pool로 보냄
		} catch (SQLException e) {
			out.println("DB연결 종료 실패");
		}
	}
		
	}
}
