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

	@Resource(name = "jdbc/demo")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		contactDao = new ContactDAO(dataSource);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF8");

		// parameter가 action값을 읽어서 액션으로 저장하는데 만약 값이 null이면 "list"로 대체
		String action = request.getParameter("action") != null ? request.getParameter("action") : "list";

		switch (action) {
		case "post": // 새로 입력 저장
			save(request, response);
			break;
		case "edit": // 수정하기 창을 표시
			edit(request, response);
			break;
		case "update": // 실제 수정하기
			update(request, response);
			break;
		case "del": // 삭제
			delete(request, response);
			break;
		default: // 전체 연락처를 화면에 table로 표시
			list(request, response);
			break;
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Contact> list = contactDao.findAll(); // DB 모든 연락처 가져오기
		request.setAttribute("contacts", list);

		RequestDispatcher rd = request.getRequestDispatcher("contact/list.jsp");
		rd.forward(request, response);

	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
	}

	private void update(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	private void edit(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	private void save(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Contact contact = new Contact();

		contact.setName(request.getParameter("name"));
		contact.setEmail(request.getParameter("email"));
		contact.setPhone(request.getParameter("phone"));

		boolean isSaved = contactDao.save(contact);
		
		if(isSaved) System.out.println("입력완료!");

		list(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
