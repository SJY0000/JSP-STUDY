package demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Cookies")
public class Cookies extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		Cookie[] cookies = request.getCookies();
		
		if (cookies == null) {
			out.println("No Cookie"); 
		} else {
			for (Cookie c : cookies) {
				String name = c.getName();
				String value = c.getValue();
				
				out.println(name + " = " + value + "<br>");
			}
		}
		Cookie c1 = new Cookie("user1", "Kim"); // 새 쿠키 c1 생성
		Cookie c2 = new Cookie("user2", "SUNG"); // 새 쿠키 c2 생성
		
		c1.setMaxAge(300); // 300초
		c2.setMaxAge(300); // 300초
		
		response.addCookie(c1); // Cookies 저장
		response.addCookie(c2); // Cookies 저장
		
		out.println("Cookie saved <br>");
		out.println("</html>");
	}
}
