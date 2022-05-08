package todoApp.rest.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import todoApp.dao.UserDao;
import todoApp.model.User;

// REST API 서버 요청방식(REST서버는 데이터만 주고 받는 서버) - 개별 CRUD 작업과 매칭해서 사용함
// 주소를 동일하게하고 메소드방식을 다르게 하는 것 - REST 서버
// 4개의 방식으로 나누는 이유는 각각 주소를 다르게 하면 유지보수 측면에서 힘듬
// - GET : 조회(Read, DB의 select문)
// - POST : 생성(Create, DB의 insert문)
// - PUT : 수정(Update, DB의 update문)
// - DELETE : 삭제(Delete, DB의 delete문)

// http://localhost:8090/todo/api/user/
// http://localhost:8090/ 기준 url, 뒷 부분은 uri
// get, delete는 parameter 받기 가능 - postman
// put, post는 x.www-form-urlencoded로 테스트 - postman
@WebServlet(urlPatterns = "/api/user/*", loadOnStartup = 1) // Tomcat이 시작하면 준비, 1은 우선순위 높음(1,2,3 ....)
public class UserRestServlet extends HttpServlet {

	private ServletContext application;
	private UserDao userDao = new UserDao();
	private Gson gson = new Gson();
	
	@Override
	public void init(ServletConfig config) throws ServletException { // web서버가 실행될 때
		System.out.println("init() 호출됨");
		// application : 애플리케이션 당 한개 유지되는 영역개체
		application = config.getServletContext();
		// 요청횟수 totalCount
		application.setAttribute("totalCount", 0);
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gson = gsonBuilder.setPrettyPrinting().create();
	}

	private void addCount() {
		int totalCount = (Integer) application.getAttribute("totalCount");
		totalCount++;
		
		application.setAttribute("totalCount", totalCount);
		
		System.out.println("\"/api/user/*\" 요청횟수 : " + totalCount);
		
	}
	@Override
	public void destroy() { // web서버가 종료되는 시점
		System.out.println("destroy() 호출됨");
		// 정리작업...
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet() 호출됨");
		addCount();
		
		// 레코드 한개 조회
		// http://localhost:8090/TODO/api/user?category=one&userName=hong
		
		// 레코드 전체(여러개) 조회
		// http://localhost:8090/TODO/api/user?category=list
		String category = request.getParameter("category");
		
		String strJson = "";
		if (category.equals("one")) {
			strJson = processGetOne(request);
		} else if (category.equals("list")) {
			strJson = processGetList(request);
		}
		sendResponse(strJson, response);
	} // doGet



	private void sendResponse(String strJson, HttpServletResponse response) throws IOException {
		response.setContentType("application/json; charset = UTF-8"); // 무조건 printWriter 전에 선언해줘야 데이터에 문제가 안 생김
		PrintWriter out = response.getWriter();
		
		out.println(strJson);
		
//		out.flush();
		out.close(); // close에 flush가 포함되어 있음
	}
	private String processGetOne(HttpServletRequest request)  {
		String userName = request.getParameter("userName");
		
		User user = userDao.getUserByUserName(userName);
		// XML 또는 JSON 문자열로 응답을 줌
		UserOneResult userOneResult = new UserOneResult();
		
		if (user != null) {
			userOneResult.setHasResult(true);
			userOneResult.setUser(user);
		} else {
			userOneResult.setHasResult(false);
		}
		// Gson을 이용해서 userOneResult 객체를 JSON 형식의 문자열로 변환
		String strJson = gson.toJson(userOneResult);
		System.out.println("strJson = " + strJson);
		
		return strJson;
	} // processGetOne
	private String processGetList(HttpServletRequest request) {
		List<User> userList = userDao.getAllUsers();
		
		UserListResult userListResult = new UserListResult();
		if (userList.size() > 0) {
			userListResult.setHasResult(true);
			userListResult.setUserList(userList);
			userListResult.setTotalCount(userList.size());
		} else { // userList.size() = 0
			userListResult.setHasResult(false);
			userListResult.setTotalCount(userList.size());
		}
		String strJson = gson.toJson(userListResult);
		System.out.println("strJson = " + strJson);
		
		return strJson;
	} // processGetList
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost() 호출됨");
		addCount();
		
		request.setCharacterEncoding("utf-8"); // 한글처리

		BufferedReader reader = request.getReader(); // 문자 입력 스트림 가져오기
		User user = gson.fromJson(reader, User.class); // JSON 문자열로부터 User 객체로 변환
		
		System.out.println(user.toString());
		userDao.registerUSer(user); // 신규회원 추가
		
		UserResult userResult = new UserResult();
		userResult.setSuccess(true);
		
		String strJson = gson.toJson(userResult);
		
		sendResponse(strJson, response);
	}



	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPut() 호출됨");
		addCount();
		
		request.setCharacterEncoding("utf-8"); // 한글처리
		
		System.out.println("doPost() 호출됨");
		addCount();
		
		request.setCharacterEncoding("utf-8"); // 한글처리

		String category = request.getParameter("category");
		System.out.println("category : " + category);
		
		if (category.equals("modify")) {
			updateUser(request, response);
		} else if (category.equals("password")) {
			updateUserPassword(request, response);
		}
	}
	
	private void updateUserPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		userDao.updatePasswordById(id, pwd); // 회원 아이디에 해당하는 비밀번호 수정
		
		UserResult userResult = new UserResult();
		userResult.setSuccess(true);
		
		String strJson = gson.toJson(userResult);
		
		sendResponse(strJson, response);
		
		
	} // updateUserPassword

	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		BufferedReader reader = request.getReader(); // 문자 입력 스트림 가져오기
		
		User user = gson.fromJson(reader, User.class); // JSON 문자열로부터 User 객체로 변환
		System.out.println(user.toString());
		
		userDao.update(user); // 회원정보 수정
		
		UserResult userResult = new UserResult();
		userResult.setSuccess(true);
		
		String strJson = gson.toJson(userResult);
		
		sendResponse(strJson, response);
	} // updateUser

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doDelete() 호출됨");
		addCount();
		
		request.setCharacterEncoding("utf-8"); // 한글처리
		
		String userName = request.getParameter("userName");
		
		userDao.delete(userName);
		
		UserResult userResult = new UserResult();
		userResult.setSuccess(true);
		
		String strJson = gson.toJson(userResult);
		
		sendResponse(strJson, response);
	}
} // class UserRestServlet
