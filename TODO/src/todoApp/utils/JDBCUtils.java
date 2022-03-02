package todoApp.utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

// DB연결을 도와주는 Class
public class JDBCUtils {
	// demo DB, useSSL=false : SSL인증을 사용하지 않음
	private static String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
	private static String jdbcUsername = "root";
	private static String jdbcPassword = "1234";
	
	public static Connection getConnection() throws SQLException {
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); // 순서 0번
			conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword); // 순서 1번
		} catch (ClassNotFoundException e) {
			System.out.println("Driver class not found!");
		}
		
		return conn; //DB에 연결하여 Connection을 받아옴
	}
	// JAVA날짜를 SQL날짜로 변경
	public static Date getSQLDate(LocalDate date) {
		return java.sql.Date.valueOf(date);
	}
	// SQL날짜를 JAVA날짜로 변경
	public static LocalDate getUtilDate(Date sqlDate) {
		return sqlDate.toLocalDate();
	}
	
	public static void close(Connection conn, PreparedStatement pstmt) {
		close(conn, pstmt, null);
	}
	
	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
