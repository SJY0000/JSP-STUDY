package controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import userdao.UserDAO;
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private UserDAO userDAO;
	
	@Resource(name = "jdbc/demo")
	private DataSource ds;

	@Override
	public void init() throws ServletException {
		userDAO = new UserDAO(ds);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("email");
		userDAO.login(req.getParameter("email"), req.getParameter("password"));
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
}
