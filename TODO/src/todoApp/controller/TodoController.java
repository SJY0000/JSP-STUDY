package todoApp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String action = request.getServletPath();
		
		switch(action) {
		case "/todos/new" : showNewForm(request, response); break;
		case "/todos/insert" : insertTodo(request, response); break;
		case "/todos/delete" : deleteTodo(request, response); break;
		case "/todos/edit" : showEditForm(request, response); break;
		case "/todos/update" : updateTodo(request, response); break;
		case "/todos/list" : listTodo(request, response); break; // localhost8090:TODO/list
		default : // 요청 주소가 기본 또는 잘못되었을 경우 login페이지로 이동 
			RequestDispatcher dispatcher = request.getRequestDispatcher("login/login.jsp");
			dispatcher.forward(request, response); break;
		} 
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void insertTodo(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void deleteTodo(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void updateTodo(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
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
