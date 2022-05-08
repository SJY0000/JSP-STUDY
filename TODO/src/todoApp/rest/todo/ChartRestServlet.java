package todoApp.rest.todo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

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
import todoApp.dao.UserDao;
import todoApp.model.User;


@WebServlet(urlPatterns = "/api/chart/*", loadOnStartup = 1)
public class ChartRestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDao userDao = new UserDao();
	private TodoDao todoDao = new TodoDaoImpl();
	private Gson gson = new Gson();

	@Override
	public void init(ServletConfig config) throws ServletException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gson = gsonBuilder.setPrettyPrinting().create(); // json 줄바꿈 출력
		
	} // init
	
	private void sendResponse(String strJson, HttpServletResponse response) throws IOException {
		response.setContentType("application/json; charset = UTF-8"); // 무조건 printWriter 전에 선언해줘야 데이터에 문제가 안 생김
		PrintWriter out = response.getWriter();
		
		out.println(strJson);
		
//		out.flush();
		out.close(); // close에 flush가 포함되어 있음
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ChartRestServlet 호출됨");
		// 완료여부 별 카운트 데이터 가져오기
		// http://localhost:8090/todo/api/chart?category=statusPerCount
		
		// 사용자아이디 별 할일 갯수 데이터 가져오기
		// http://localhost:8090/todo/api/chart?category=getUsernamePerCount
		
		String category = request.getParameter("category");
		String strJson = "";
		
		if (category.equals("statusPerCount")) {
			strJson = processGetStatusPerCount(request);
		} else if (category.equals("getUsernamePerCount")) {
			strJson = processGetgetUsernamePerCount(request);
		}
		
		sendResponse(strJson, response);
	}

	private String processGetgetUsernamePerCount(HttpServletRequest request) {
		List<User> list = todoDao.getUsernamePerCount();
		
		String strJson = gson.toJson(list);
		System.out.println("strJson = " + strJson);
		
		return strJson;
	}

	private String processGetStatusPerCount(HttpServletRequest request) {
		List<Map<String, Object>> list = todoDao.getStatusPerCount();
		
		String strJson = gson.toJson(list);
		System.out.println("strJson = " + strJson);
		
		return strJson;
	}// processGetStatusPerCount

}
