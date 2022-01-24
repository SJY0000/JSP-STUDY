package todoApp.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import todoApp.dao.TodoDao;
import todoApp.dao.TodoDaoImpl;
import todoApp.model.Todo;


// SERVLET 주소가 "/"이면 다른 서블릿 주소(/register, /login)를 제외한 모든 요청이 여기에서 처리함
@WebServlet("/todos")
public class TodoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TodoDao todoDAO;
	
	public void init() {
		todoDAO = new TodoDaoImpl(); // 실제 객체는 todoDao를 구현한 ToDoDaoImpl로 생성
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청주소가 localhost:8090/TODO/new = > "/new"가 action의 값
		String action = request.getParameter("action");
		
		switch(action) {
		case "new" : showNewForm(request, response); break;
		case "insert" : insertTodo(request, response); break;
		case "delete" : deleteTodo(request, response); break;
		case "edit" : showEditForm(request, response); break;
		case "update" : updateTodo(request, response); break;
		case "list" : listTodo(request, response); break; // localhost8090:TODO/list
		default : // 요청 주소가 기본 또는 잘못되었을 경우 login페이지로 이동 
			RequestDispatcher dispatcher = request.getRequestDispatcher("login/login.jsp");
			dispatcher.forward(request, response); break;
		} 
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-form.jsp");
		dispatcher.forward(request, response);
		
	}

	private void insertTodo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 입력한 값들을 받아서 DB에 저장
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		String title = request.getParameter("title");
		String username = (String) session.getAttribute("username");
		String description = request.getParameter("description");
		LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate")); 
		boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
		
		Todo newTodo = new Todo(title, username, description, targetDate, isDone);
		
		todoDAO.insertTodo(newTodo);
		
		response.sendRedirect("todos?action=list"); // 새 할 일을 저장 후에 list페이지로 이동
	}

	private void deleteTodo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		
		todoDAO.deleteTodo(id);
		
		response.sendRedirect("todos?action=list"); // 새 할 일을 삭제 후에 list페이지로 이동
		
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 수정할 todo객체를 같이 보냄
		Long id = Long.parseLong(request.getParameter("id"));
		Todo theTodo = todoDAO.selectTodo(id);
		
		request.setAttribute("todo", theTodo);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-form.jsp");
		dispatcher.forward(request, response);
		
	}

	private void updateTodo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		
		String title = request.getParameter("title");
		Long id = Long.parseLong(request.getParameter("id"));
		String username = todoDAO.selectTodo(id).getUsername();
		String description = request.getParameter("description");
		LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate")); 
		boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
		
		Todo newTodo = new Todo(id, title, username, description, targetDate, isDone);
		
		todoDAO.updateTodo(newTodo);
		
		response.sendRedirect("todos?action=list"); // 새 할 일을 수정 후에 list페이지로 이동
		
	}

	private void listTodo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Todo> listTodo = todoDAO.selectAllTodos(); // DB에서 할일들을 가져와 list에 저장
		request.setAttribute("listTodo", listTodo);		// list를 request에 저장
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-list.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
