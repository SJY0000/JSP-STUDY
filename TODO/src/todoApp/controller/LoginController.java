package todoApp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import todoApp.dao.LoginDao;
import todoApp.model.LoginBean;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private LoginDao loginDao;
	
	@Override
	public void init() {
		loginDao = new LoginDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("login/login.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 입력 받을 떄 한글
		response.setContentType("text/html;charset=UTF-8"); // 출력할 떄 한글
		// ID, PASSWORD를 parameter로 입력받기
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		LoginBean loginBean = new LoginBean();
		
		loginBean.setUsername(username);
		loginBean.setPassword(password);
		
		if (loginDao.validate(loginBean)) { // 계정이 있음 => todolist.jsp로 forward
			HttpSession session = request.getSession();
			session.setAttribute("username", username); // 로그인한 username을 session에 저장
			RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-list.jsp");
			dispatcher.forward(request, response);
		} else { // 계정이 없음
			request.setAttribute("user", username); // ID는 다시 보내줌
			request.setAttribute("message", "잘못입력하셨습니다.");
			// 로그인 실패 내용을 forward로 다시 login 페이지에 보여주기
			RequestDispatcher dispatcher = request.getRequestDispatcher("login/login.jsp");
			dispatcher.forward(request, response);
		}	
	}
}
