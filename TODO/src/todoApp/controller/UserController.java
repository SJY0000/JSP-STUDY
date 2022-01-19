package todoApp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todoApp.dao.UserDao;
import todoApp.model.User;


@WebServlet("/register")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDao userDao;
	
	@Override
	public void init() throws ServletException {
		// Servlet이 만들어 질 때 한 번 실행
		userDao = new UserDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// User 입력 시 post로 입력 데이터를 
		request.setCharacterEncoding("UTF-8"); // 한글설정
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String userName = request.getParameter("usertName");
		String password = request.getParameter("password");
		
		User user = new User(firstName, lastName, userName, password);
	}

}
