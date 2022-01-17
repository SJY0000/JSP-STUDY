package controller;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Account {
	private Connection conn;
	
	public Account(Connection conn) {
		this.conn = conn;
	}
	// 로그인 (email, password) DB에서 같은 email, password 확인해서 false,true return
	public boolean login(String email, String password) throws SQLException {
		String sql = "SELECT COUNT(*) AS count FROM users where email=? and password=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,  email); // 첫번쨰 물음표
		pstmt.setString(2,  password);	// 두번쨰 물음표
		
		ResultSet rs = pstmt.executeQuery(); // SQL문 실행
		
		int count = 0;
		
		if(rs.next()) { // 결과가 있으면
			count = rs.getInt("count"); // count 열의 값을 return(int형)
		}
		rs.close();
		
		if(count == 0) return false; // 없으면 false 있으면 true
		return true;
	}
	public boolean exists(String email) throws SQLException {
		// 이미 등록된 email이 있을 경우 true 없으면 false
		String sql = "SELECT COUNT(*) AS count FROM users where email=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, email);
		
		ResultSet rs = pstmt.executeQuery();
		
		int count = 0;
		
		if (rs.next()) { // 결과가 있으면
			count = rs.getInt("count"); // count 열의 값을 리턴
		}
		if (count == 0) return false; // 없으면 false 있으면 true
		
		return true;
	}
	public void create(String email, String password) throws SQLException {
		// DB에 새 user의 email, password를 입력
		String sql = "insert into users(email, password) values(?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, email);
		pstmt.setString(2, password);
		
		pstmt.executeUpdate(); // 결과 rs가 없을 경우(입력, 수정, 삭제) 
		
		pstmt.close();
	}

}
