package todoApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import todoApp.model.Todo;
import todoApp.utils.JDBCUtils;

public class TodoDaoImpl implements TodoDao {
// DB연결하고 각 기능에 맞게 작업, DB todos테이블에 CRUD 작업
	@Override
	public void insertTodo(Todo todo) {
		String INSERT_USER_SQL = "insert into todos(title, username, description, target_date,is_done) "
				+ "value (?,?,?,?,?)";

		try {
			Connection conn = JDBCUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(INSERT_USER_SQL);
			pstmt.setString(1, todo.getTitle());
			pstmt.setString(2, todo.getUsername());
			pstmt.setString(3, todo.getDescription());
			pstmt.setDate(4, JDBCUtils.getSQLDate(todo.getTargetDate())); // ToDo에 입력된 날짜를 SQL문에 입력될 날짜타입으로 변경
			pstmt.setBoolean(5, todo.isStatus()); // pstmt 완성

			pstmt.executeUpdate(); // pstmt 실행, 결과가 없는 Update, Delete, Drop, Insert 등은 executeUpdate() 사용
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Todo selectTodo(long todoId) {
		String SELECT_TODO_BY_ID = "SELECT id, title, username, description, target_date, is_done from todos where id = ?";
		Todo todo = null;
		try {
			Connection conn = JDBCUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(SELECT_TODO_BY_ID);
			
			pstmt.setLong(1, todoId);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {// 결과가 있을 경우에 값을 저장한다.
				long id = rs.getLong("id");
				String title = rs.getString("title");
				String username = rs.getString("username");
				String description = rs.getString("description");
				LocalDate targetDate = rs.getDate("target_date").toLocalDate();
				Boolean status = rs.getBoolean("is_done");
				todo = new Todo(id, title, username, description, targetDate, status);
			}
		} catch (SQLException e) {
			System.out.println("SQL ToDo 검색 ERROR!");
		}
		return todo;
	}

	@Override
	public List<Todo> selectAllTodos() {
		String SELECT_TODO_BY_ID = "SELECT * from todos";
		List<Todo> todos = new ArrayList<Todo>(); // 빈 ArrayList 생성
		try {
			Connection conn = JDBCUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(SELECT_TODO_BY_ID);
			
			ResultSet rs = pstmt.executeQuery();
			
			// 결과가 여러줄 일 때 while문을 사용하여 처리
			while (rs.next()) {// 결과가 있을 경우에 값을 저장한다.
				long id = rs.getLong("id");
				String title = rs.getString("title");
				String username = rs.getString("username");
				String description = rs.getString("description");
				LocalDate targetDate = rs.getDate("target_date").toLocalDate();
				Boolean status = rs.getBoolean("is_done");
				todos.add(new Todo(id, title, username, description, targetDate, status));
			}
			
		} catch (SQLException e) {
			System.out.println("SQL ToDo 검색 ERROR!");
			return null;
		}
		System.out.println("SERCH 완료");
		return todos;
	}

	@Override
	public boolean deleteTodo(long todoId) {
		String DELETE_SQL = "delete from todos where id = ?";
		boolean status = false;	
		try {
			Connection conn = JDBCUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(DELETE_SQL);
			pstmt.setLong(1, todoId);

			status = pstmt.executeUpdate() > 0; // 한 줄 이상 삭제가 되면 true
			
		} catch (SQLException e) {
			System.out.println("SQL DELETE TOD ERROR");
			return false;
		}
		System.out.println("DELETE 완료");
		return status;
	}

	@Override
	public boolean updateTodo(Todo todo) {
		String UPDATE_SQL = "update todos set description = ?, title = ?, username = ?, target_date = ?, is_done = ? where id = ? ";
		boolean status = false;	
		try {
			Connection conn = JDBCUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(UPDATE_SQL);
			pstmt.setString(1, todo.getDescription());
			pstmt.setString(2, todo.getTitle());
			pstmt.setString(3, todo.getUsername());
			pstmt.setDate(4, JDBCUtils.getSQLDate(todo.getTargetDate()));		
			pstmt.setBoolean(5, todo.isStatus());
			pstmt.setLong(6, todo.getId());

			status = pstmt.executeUpdate() > 0; // 한 줄 이상 Update가 되면 true			
			
		} catch (SQLException e) {
			System.out.println("SQL UPDATE TODO ERROR");
			return false;
		}
		System.out.println("UPDATE 완료");
		return status;
	}

}
