package todoApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import todoApp.model.Todo;
import todoApp.model.User;
import todoApp.utils.JDBCUtils;

public class TodoDaoImpl implements TodoDao {
// DB연결하고 각 기능에 맞게 작업, DB todos테이블에 CRUD 작업

	@Override
	public List<User> getUsernamePerCount() {
		List<User> userList = new ArrayList<User>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "";
		sql += " SELECT u.id as user_id, u.userName, u.firstName, u.lastName, ";
		sql += " COUNT(t.username) as todo_count ";
		sql += " FROM users u LEFT OUTER JOIN todos t on u.userName = t.username ";
		sql += " GROUP BY u.userName ";
		sql += " HAVING COUNT(t.username) > 0";
		sql += " ORDER BY userName ASC, target_date DESC; ";


		try {
			conn = JDBCUtils.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				User user = new User();
				user.setUserName(rs.getString("userName"));
				user.setTodoCount(rs.getInt("todo_count"));
				
				userList.add(user);
			} // while
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(conn, pstmt, rs);
		}

		return userList;
	}

	
	@Override
	public List<Map<String, Object>> getStatusPerCount() {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "";
		sql += " select is_done, ";
		sql += " case is_done when 0 Then '진행중' ";
		sql += " when 1 then '완료됨' ";
		sql += " End AS status, ";
		sql += " COUNT(*) as count ";
		sql += " from todos ";
		sql += " GROUP BY is_done ";
		sql += " order by status desc; ";
		
		try {
			conn = JDBCUtils.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				
				map.put("status", rs.getString("status"));
				map.put("count", rs.getInt("count"));
				
				list.add(map);
			} // while
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(conn, pstmt, rs);
		}

		
		return list;
	}// getStatusPerCount

 	
	// 외부 조인해서 가져오기 메소드
	public User getUserAndTodos(String userName) {
		User user = null;
		List<Todo> todoList = new ArrayList<Todo>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "";
		sql += " SELECT u.id as user_id, u.password, u.userName, u.firstName, u.lastName, ";
		sql += " t.id as todo_id, t.title, t.description, t.target_date, t.is_done ";
		sql += " FROM users u LEFT OUTER JOIN todos t on u.userName = t.username ";
		sql += " WHERE u.userName = ? ";
		sql += " ORDER BY target_date DESC ";
		
		try {
			conn = JDBCUtils.getConnection();
			
			// 이렇게 설정하면 rs에서 커서를 마음대로 움직일 수 있음
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, userName);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// Sql 값이 null 이면 0으로 출력됨(기본키, NotNull 속성)
				if (rs.getLong("todo_id") == 0) {
					continue; // 중괄호 끝으로 감(사이의 실행문 실행안함)
				}
			
				Todo todo = new Todo();
				
				todo.setId(rs.getLong("todo_id"));
				todo.setTitle(rs.getString("title"));
				todo.setDescription(rs.getString("description"));
				todo.setTargetDate((rs.getDate("target_date") != null) ? rs.getDate("target_date").toLocalDate() : null);
				todo.setStatus(rs.getBoolean("is_done"));
				
				todoList.add(todo); // 리스트에 추가
			} // while
			
			// 1:N일 경우 1은 반복적인 데이터 이니 한 번만 등록
			// 마지막 데이터 행으로 커서위치로 이동
			if (rs.last()) {
				user = new User();
				user.setUserName(rs.getString("userName"));
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setPassword(rs.getString("password"));
				
				user.setTodolist(todoList);
			};
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(conn, pstmt, rs);
		}
		
		return user;
	}// getUserAndTodos
	
	
	public List<User> getAllUserAndTodoCount() {
		List<User> userList = new ArrayList<User>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "";
		sql += " SELECT u.id as user_id, u.userName, u.firstName, u.lastName, ";
		sql += " COUNT(t.username) as todo_count ";
		sql += " FROM users u LEFT OUTER JOIN todos t on u.userName = t.username ";
		sql += " GROUP BY u.userName ";
		sql += " ORDER BY target_date DESC ";
		
		try {
			conn = JDBCUtils.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				User user = new User();
				user.setUserName(rs.getString("userName"));
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setTodoCount(rs.getInt("todo_count"));
				
				userList.add(user);			
			} // while

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(conn, pstmt, rs);
		}
		
		return userList;
	}// getUserAndTodos
	
	@Override
	public void insertTodo(Todo todo) {
		String INSERT_USER_SQL = "insert into todos(title, username, description, target_date,is_done) "
				+ "value (?,?,?,?,?)";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JDBCUtils.getConnection();
			pstmt = conn.prepareStatement(INSERT_USER_SQL);
			pstmt.setString(1, todo.getTitle());
			pstmt.setString(2, todo.getUsername());
			pstmt.setString(3, todo.getDescription());
			pstmt.setDate(4, JDBCUtils.getSQLDate(todo.getTargetDate())); // ToDo에 입력된 날짜를 SQL문에 입력될 날짜타입으로 변경
			pstmt.setBoolean(5, todo.isStatus()); // pstmt 완성

			pstmt.executeUpdate(); // pstmt 실행, 결과가 없는 Update, Delete, Drop, Insert 등은 executeUpdate() 사용
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(conn, pstmt);
		}
	}

	@Override
	public Todo selectTodo(long todoId) {
		String SELECT_TODO_BY_ID = "SELECT id, title, username, description, target_date, is_done from todos where id = ?";
		Todo todo = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtils.getConnection();
			pstmt = conn.prepareStatement(SELECT_TODO_BY_ID);
			
			pstmt.setLong(1, todoId);
			
			rs = pstmt.executeQuery();
			
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
		} finally {
			JDBCUtils.close(conn, pstmt, rs);
		}
		return todo;
	}
	
	@Override
	public List<Todo> selectTodoByUsername(String username) {
		String SELECT_TODO_BY_ID = "SELECT * from todos where username = ?";
		List<Todo> todos = new ArrayList<Todo>(); // 빈 ArrayList 생성
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtils.getConnection();
			pstmt = conn.prepareStatement(SELECT_TODO_BY_ID);
			pstmt.setString(1, username);
			
			rs = pstmt.executeQuery();
			
			// 결과가 여러줄 일 때 while문을 사용하여 처리
			while (rs.next()) {// 결과가 있을 경우에 값을 저장한다.
				Todo todo = new Todo();
				todo.setId(rs.getLong("id"));
				todo.setTitle(rs.getString("title"));
				todo.setDescription(rs.getString("description"));
				todo.setUsername(username);
				todo.setTargetDate(rs.getDate("target_date").toLocalDate());
				todo.setStatus(rs.getBoolean("is_done"));

				todos.add(todo);
			}
			
		} catch (SQLException e) {
			System.out.println("SQL ToDo 검색 ERROR!");
			return null;
		} finally {
			JDBCUtils.close(conn, pstmt, rs);
		}
		System.out.println("SERCH 완료");
		return todos;
	} // selectTodoByUsername

	@Override
	public List<Todo> selectAllTodos() {
		String SELECT_TODO_BY_ID = "SELECT * from todos";
		List<Todo> todos = new ArrayList<Todo>(); // 빈 ArrayList 생성
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtils.getConnection();
			pstmt = conn.prepareStatement(SELECT_TODO_BY_ID);
			
			rs = pstmt.executeQuery();
			
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
		} finally {
			JDBCUtils.close(conn, pstmt, rs);
		}
		System.out.println("SERCH 완료");
		return todos;
	}

	@Override
	public boolean deleteTodo(long todoId) {
		String DELETE_SQL = "delete from todos where id = ?";
		boolean status = false;	
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = JDBCUtils.getConnection();
			pstmt = conn.prepareStatement(DELETE_SQL);
			pstmt.setLong(1, todoId);

			status = pstmt.executeUpdate() > 0; // 한 줄 이상 삭제가 되면 true
			
		} catch (SQLException e) {
			System.out.println("SQL DELETE TOD ERROR");
			return false;
		} finally {
			JDBCUtils.close(conn, pstmt);
		}
		System.out.println("DELETE 완료");
		return status;
	}

	@Override
	public boolean deleteTodo(String username) {
		String DELETE_SQL = "delete from todos where username = ?";
		boolean status = false;	
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = JDBCUtils.getConnection();
			pstmt = conn.prepareStatement(DELETE_SQL);
			pstmt.setString(1, username);

			status = pstmt.executeUpdate() > 0; // 한 줄 이상 삭제가 되면 true
			
		} catch (SQLException e) {
			System.out.println("SQL DELETE TOD ERROR");
			return false;
		} finally {
			JDBCUtils.close(conn, pstmt);
		}
		System.out.println("DELETE 완료");
		return status;
	}

	@Override
	public boolean updateTodo(Todo todo) {
		String UPDATE_SQL = "update todos set description = ?, title = ?, username = ?, target_date = ?, is_done = ? where id = ? ";
		boolean status = false;	
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = JDBCUtils.getConnection();
			pstmt = conn.prepareStatement(UPDATE_SQL);
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
		} finally {
			JDBCUtils.close(conn, pstmt);
		}
		System.out.println("UPDATE 완료");
		return status;
	}



}
