package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;


@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	index 페이지에서 넘어온 데이터를 get으로 받는 이유는 주소창에 ?action=login 붙여서 적는 get방식으로 넘어왔기 떄문
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		// email, password, valmessage null 값이 나오지 않게 초기화
		request.setAttribute("email", "");
		request.setAttribute("password", "");
		request.setAttribute("valmessage", "");
		if(action == null) {
			// action 값이 없을 경우 index페이지로 보냄
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} else if(action.equals("login")) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if (action == null) {
			// action 값이 없을 경우 index페이지로 보냄
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} else if (action.equals("dologin")) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			request.setAttribute("email", email);
			request.setAttribute("password", password);
		
			
			User user = new User(email, password);
			
			// 유효성 검사 통과 시 로그인성공페이지로 이동 
			if (user.validate()) {
				request.getRequestDispatcher("/loginsuccess.jsp").forward(request, response);
			} else { // 유효성 검사에서 false(mesaage를 request객체에 parameter로 추가)
				request.setAttribute("valmessage", user.getMessage());
				// 다시 login 페이지로 되돌아감 (valmessage를 추가)
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		}
	}
}
