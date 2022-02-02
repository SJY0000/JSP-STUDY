package controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import dao.ContactDAO;
import model.Contact;

@WebServlet("/Contacts")
public class ContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ContactDAO contactDao;
	
	@Resource(name="jdbc/demo")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		super.init();
	contactDao = new ContactDAO(dataSource);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF8");


//		list.forEach(contact -> System.out.println(contact.toString()));
		String action = request.getParameter("action");
		
		switch (action) {
			case "post":
				save(request, response);
				break;
			case "edit":
				edit(request, response);
				break;
			case "update":
				update(request, response);
				break;
			case "del":
				delete(request, response);
				break;
			default:
				list(request, response);
				break;
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Contact> list = contactDao.findAll();
		request.setAttribute("contacts", list);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("contact/list.jsp");
		dispatcher.forward(request, response);
		
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		contactDao.delete(id);
		
		response.sendRedirect("Contacts?action=list");
	}

	private void update(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF8");
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		Contact contact = new Contact(name, email, phone);
		
		contactDao.save(contact);
		
		response.sendRedirect("Contacts?action=list");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
