package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext(); // 이 어플리케이션의 Context객체 return
		
		Integer hits = (Integer)context.getAttribute("hits");
		
		// 처음 hits의 값이 없을 경우 hits의 값을 0으로 초기화
		if (hits == null) hits = 0;
		// hits의 값이 null 이 아니면 1 증가
		else hits++;
		
		context.setAttribute("hits", hits);
		
		PrintWriter out = response.getWriter();
		out.println("Hits : " + hits);
	}
}
