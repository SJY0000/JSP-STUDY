package todoApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import todoApp.model.User;
import todoApp.utils.JDBCUtils;

// DAO는 DB에 연결해 DATA를 조작하는 Class
public class UserDao {
	// User 입력 => DB에 UserData를 입력
	public int registerUSer(User user) { // 결과가 성공이면 1, 실패면 0이하 (에러나면 - 출력) RETURN
		String INSERT_USER_SQL = "INSERT INTO users(firstName, lastName, userName, password) "
									+ "VALUES (?, ?, ?, ?)";
		
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = JDBCUtils.getConnection();
			pstmt = conn.prepareStatement(INSERT_USER_SQL);
			pstmt.setString(1, user.getFirstName());
			pstmt.setString(2, user.getLastName());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getPassword()); // pstmt 완성
			
			// 결과 실행의 갯수를 result에 저장 = 1 실패면 0
			result = pstmt.executeUpdate();	// pstmt 실행, 결과가 없는 Update, Delete, Drop, Insert 등은 executeUpdate() 사용
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(conn, pstmt);
		}
		return result;
	}
	
	public User getUserByUserName(String userName) {
		User user = null;
		
		String sql = "SELECT * FROM users WHERE userName = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtils.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userName);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				user = new User();
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setUserName(rs.getString("userName"));
				user.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(conn, pstmt, rs);
		}
		
		return user;
	}
	
	public List<User> getAllUsers() {
		List<User> userList = new ArrayList<User>();
		
		String sql = "SELECT * FROM users ORDER BY userName ASC";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtils.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				User user = new User();
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setUserName(rs.getString("userName"));
				user.setPassword(rs.getString("password"));
				
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(conn, pstmt);
		}
		return userList;		
	} // getAllUsers
	
	public void update(User user) {
		String sql = "";
		sql += " UPDATE users ";
		sql += " SET firstName = ?, lastName = ? ";
		sql += " WHERE userName = ? ";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getFirstName());
			pstmt.setString(2, user.getLastName());
			pstmt.setString(3, user.getUserName());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(conn, pstmt);
		}
		
	}
}
