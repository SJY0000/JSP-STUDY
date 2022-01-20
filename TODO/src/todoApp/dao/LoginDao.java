package todoApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import todoApp.model.LoginBean;
import todoApp.utils.JDBCUtils;

// DB연결하여 login 확인하는 메소드 만들 Class
public class LoginDao {
	// 계정이 있으면 true, 없으면 false
	public boolean validate(LoginBean loginbean) {
		boolean status = false;
		String sql = "SELECT * from users where userName = ? and password = ?";
		
		try {
			Connection conn = JDBCUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, loginbean.getUsername());
			pstmt.setString(2, loginbean.getPassword());
			
			ResultSet rs = pstmt.executeQuery();
			
			status = rs.next(); // 한 줄이라도 있으면 true, 없으면 false
		} catch (SQLException e) {
			System.out.println("SQL login ERROR!");
		}
		return status;
	}
}
