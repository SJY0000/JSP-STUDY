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

import dao.ContactDao;
import model.Contact;
import utills.Json;


@WebServlet("/contact")
public class ContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ContactDao contactDao;

	@Resource(name = "jdbc/demo")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		contactDao = new ContactDao(dataSource);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		// 파라메터가 cmd값을 읽어서 액션으로 저장하는데 만약 값이 null이면 "list"로 대체
		String action = req.getParameter("cmd") != null ? req.getParameter("cmd") : "list";

		switch (action) {
		case "post": // 새로 입력 저장
			save(req, resp);
			break;
		case "edit": // 수정하기 창을 표시
			edit(req, resp);
			break;
		case "update": // 실제 수정하기
			update(req, resp);
			break;
		case "delete": // 삭제
			delete(req, resp);
			break;
		default: // 전체 연락처를 화면에 테이블로 표시
			list(req, resp);
			break;
		}
	}

	private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Contact> contacts = contactDao.findAll(); //DB모든 연락처 가져오기
		req.setAttribute("contacts", contacts);
		RequestDispatcher rd = req.getRequestDispatcher("contact/list.jsp");
		rd.forward(req, resp); //리퀘스트를 유지하면서 list.jsp페이지로 이동
	}

	private void delete(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id")); //문자열 id를 정수 변환
		
		boolean isDeleted = contactDao.delete(id);
		
		if(isDeleted) {
			System.out.println("삭제 완료!");	
			new Json(resp).sendMessage(true, "연락처 삭제됨");
		}
	}

	private void update(HttpServletRequest req, HttpServletResponse resp) {
		Contact contact = new Contact();
		
		contact.setId(Integer.parseInt(req.getParameter("id")));
		contact.setName(req.getParameter("name"));
		contact.setEmail(req.getParameter("email"));
		contact.setPhone(req.getParameter("phone"));
		
		boolean isUpdated = contactDao.update(contact); //참이면 저장완료
		
		if(isUpdated) {
			System.out.println("수정 완료!");	
			new Json(resp).sendMessage(true, "연락처 수정됨"); 
		}

	}

	private void edit(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id")); //문자열 id를 정수 변환
		
		Contact contact = contactDao.find(id); //id로 연락처 객체 찾기
		if(contact != null) {
			System.out.println("찾기 완료!");	
			new Json(resp).sendContact(contact); //연락처를 상태와 제이슨 변환해 출력
		}
	}

	private void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Contact contact = new Contact();
		
		contact.setName(req.getParameter("name"));
		contact.setEmail(req.getParameter("email"));
		contact.setPhone(req.getParameter("phone"));
		
		boolean isSaved = contactDao.save(contact); //참이면 저장완료
		
		if(isSaved) {
			System.out.println("입력 완료!");	
			new Json(resp).sendMessage(true, "연락처 입력됨"); 
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
