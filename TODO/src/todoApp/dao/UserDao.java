package todoApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import todoApp.model.User;
import todoApp.utils.JDBCUtils;

// DAO는 DB에 연결해 DATA를 조작하는 Class
public class UserDao {
	// User 입력 => DB에 UserData를 입력
	public int registerUSer(User user) { // 결과가 성공이면 1, 실패면 0이하 (에러나면 - 출력) RETURN
		String INSERT_USER_SQL = "INSERT INTO users(firstName, lastName, userName, password) "
									+ "VALUES (?, ?, ?, ?)";
		
		int result = 0;
		
		try {
			Connection conn = JDBCUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(INSERT_USER_SQL);
			pstmt.setString(1, user.getFirstName());
			pstmt.setString(2, user.getLastName());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getPassword()); // pstmt 완성
			
			// 결과 실행의 갯수를 result에 저장 = 1 실패면 0
			result = pstmt.executeUpdate();	// pstmt 실행, 결과가 없는 Update, Delete, Drop, Insert 등은 executeUpdate() 사용
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
