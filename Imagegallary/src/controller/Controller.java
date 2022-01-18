package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/gallery")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Map<String, String> actionMap = new HashMap<String, String>();  
	
    public Controller() {
		actionMap.put("home", "/home.jsp");
		actionMap.put("image", "/image.jsp");
		actionMap.put("rate", "/image.jsp");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		// 만약 action의 값이 없거나 actionMap에 없으면 => home.jsp
		if (action == null || !actionMap.containsKey(action)) action = "home";
		
		request.getRequestDispatcher(actionMap.get(action)).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
