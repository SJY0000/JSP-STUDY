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

import dao.BoardDao;
import model.Board;

@WebServlet("/Boards")
public class Boardcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/traditionmarket")
	private DataSource ds;
	private BoardDao boardDao;
	
	@Override
	public void init() throws ServletException {
		boardDao = new BoardDao(ds);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action") != null ? request.getParameter("action") : "home";

		switch (action) {
		case "notice":
			list(request, response);
			break;
//		case "board": 
//			list(request, response);
//			break;
//		case "update": // 실제 수정하기
//			update(request, response);
//			break;
//		case "delete": // 삭제
//			delete(request, response);
//			break;
		default: // 전체 연락처를 화면에 테이블로 표시
			home(request, response);
			break;
		}
	}

	private void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("TraditionMarket/index.jsp");
		rd.forward(request, response);
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			List<Board> board = boardDao.findAll(); 
			request.setAttribute("boards", board);
			RequestDispatcher rd = request.getRequestDispatcher("TraditionMarket/board.jsp");
			rd.forward(request, response); 
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
