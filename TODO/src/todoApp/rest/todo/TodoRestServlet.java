package todoApp.rest.todo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import todoApp.dao.TodoDao;
import todoApp.dao.TodoDaoImpl;

import todoApp.model.Todo;
import todoApp.model.User;


//http://localhost:8090/todo/api/todo/
// *은 0개 이상의 문자열, loadOnStartup 값 같아도 상관없음, 객체 미리 만드는 속성
@WebServlet(urlPatterns = "/api/todo/*", loadOnStartup = 1)
public class TodoRestServlet extends HttpServlet {

	private TodoDao todoDao = new TodoDaoImpl();
	private Gson gson = new Gson();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("TodoRestServlet init() 호출됨");
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
		gson = gsonBuilder.setPrettyPrinting().create(); // json 줄바꿈 출력
	}
	
	private void sendResponse(String strJson, HttpServletResponse response) throws IOException {
		response.setContentType("application/json; charset = UTF-8"); // 무조건 printWriter 전에 선언해줘야 데이터에 문제가 안 생김
		PrintWriter out = response.getWriter();
		
		out.println(strJson);
		
//		out.flush();
		out.close(); // close에 flush가 포함되어 있음
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("TodoRestServlet doGet() 호출됨");
		
		// 레코드 한개 조회
		// http://localhost:8090/todo/api/todo?category=one&id=1
		
		// 레코드 특정 사용자의 데이터(여러개) 조회
		// http://localhost:8090/todo/api/todo?category=me&username=hong
		
		// 레코드 전체(여러개) 조회
		// http://localhost:8090/todo/api/todo?category=all
		String category = request.getParameter("category");
		String strJson = "";
		
		if (category.equals("one")) {
			strJson = processGetOne(request);
		} else if (category.equals("me")) {
			strJson = processGetMe(request);
		} else if (category.equals("all")) {
			strJson = processGetAll(request);
		}
		sendResponse(strJson, response);
	}

	private String processGetOne(HttpServletRequest request) {
		String strId = request.getParameter("id");
		long id = Long.parseLong(strId);
		
		
		Todo todo = todoDao.selectTodo(id);
		
		TodoOneResult todoOneResult = new TodoOneResult();
		
		if (todo != null) {
			todoOneResult.setTodo(todo);
			todoOneResult.setHasResult(true);			
		} else {
			todoOneResult.setHasResult(false);
		}
		String strJson = gson.toJson(todoOneResult);
		System.out.println("strJson = " + strJson);
		return strJson;
	}
	
	private String processGetMe(HttpServletRequest request) {
		String username = request.getParameter("userName");
		
		User user = todoDao.getUserAndTodos(username);
		
		TodoListResult todoListResult = new TodoListResult();
		
		if (user != null) {
			todoListResult.setHasResult(true);
			todoListResult.setTotalCount(user.getTodolist().size());
			todoListResult.setUser(user);
		} else { // user == null 
			todoListResult.setHasResult(false);
			todoListResult.setTotalCount(0);
		}

		
		String strJson = gson.toJson(todoListResult);
		System.out.println("strJson = " + strJson);
		return strJson;
	}
	
	private String processGetAll(HttpServletRequest request) {
		List<User> userList = todoDao.getAllUserAndTodoCount();
		
		TodoListUsersResult result = new TodoListUsersResult();
		
		if (userList.size() > 0) {
			result.setUserlist(userList);
			result.setHasResult(true);
			result.setTotalCount(userList.size());
		} else {
			result.setHasResult(false);
			result.setTotalCount(userList.size());
		}
		
		String strJson = gson.toJson(result);
		System.out.println("strJson = " + strJson);
		return strJson;
	} // doGet

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("TodoRestServlet doPost() 호출됨");
		
		request.setCharacterEncoding("utf-8"); // 한글처리

		BufferedReader reader = request.getReader(); // 문자 입력 스트림 가져오기
		Todo todo = gson.fromJson(reader, Todo.class); // JSON 문자열로부터 User 객체로 변환
		
		System.out.println(todo.toString());
		todoDao.insertTodo(todo); // 새 할일 추가
		
		TodoResult todoResult  = new TodoResult();
		todoResult.setSuccess(true);
		
		String strJson = gson.toJson(todoResult);
		
		sendResponse(strJson, response);
//		PostMan으로 POST요청시 이런 형식으로 보내야 함(LocalDate에 여러 값을 가지고 있어서 객체형식으로 표현됨)
//		{
//		    "title" : "할일 1004",
//		    "description" : "할일 1004번쨰......",
//		    "username" : "asd",
//		    "targetDate": {
//		                    "year": 2022,
//		                    "month": 3,
//		                    "day": 04
//		    },
//		    "status" : false
//		}
	} //doPost

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("TodoRestServlet doPut() 호출됨");
		
		request.setCharacterEncoding("utf-8"); // 한글처리

		BufferedReader reader = request.getReader(); // 문자 입력 스트림 가져오기
		Todo todo = gson.fromJson(reader, Todo.class); // JSON 문자열로부터 User 객체로 변환
		
		System.out.println(todo.toString());
		todoDao.updateTodo(todo); // 할일 수정
		
		TodoResult todoResult  = new TodoResult();
		todoResult.setSuccess(true);
		
		String strJson = gson.toJson(todoResult);
		
		sendResponse(strJson, response);
	} //doPut

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("TodoRestServlet doDelete() 호출됨");
		
		// 레코드 한개 삭제
		// http://localhost:8090/todo/api/todo?category=one&id=1
		
		// 레코드 특정 사용자의 데이터(여러개) 삭제
		// http://localhost:8090/todo/api/todo?category=mine&username=hong
		
		String category = request.getParameter("category");
		String strJson = "";
		
		if (category.equals("one")) {
			strJson = processDeleteOne(request);
		} else if (category.equals("mine")) {
			strJson = processDeleteMine(request);
		} 
		sendResponse(strJson, response);
	}// doDelete

	private String processDeleteOne(HttpServletRequest request) {
		String strid = request.getParameter("id");
		long id  = Long.parseLong(strid); 
		
		todoDao.deleteTodo(id); // 글 번호 id에 해당하는 글 하나 삭제
		
		TodoResult todoResult  = new TodoResult();
		todoResult.setSuccess(true);
		
		String strJson = gson.toJson(todoResult);

		return strJson;
	}

	private String processDeleteMine(HttpServletRequest request) {
		String username = request.getParameter("username");
		
		todoDao.deleteTodo(username); // 글 번호 id에 해당하는 글 하나 삭제
		
		TodoResult todoResult  = new TodoResult();
		todoResult.setSuccess(true);
		
		String strJson = gson.toJson(todoResult);

		return strJson;
	}

	@Override
	public void destroy() {
		System.out.println("TodoRestServlet destroy() 호출됨");
	}// destroy	
	
}
