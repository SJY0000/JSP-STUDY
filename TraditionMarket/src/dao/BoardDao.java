package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import model.Board;


public class BoardDao {
	
	
	private DataSource dataSource; 
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private LocalDate date;
	
	public BoardDao(DataSource dataSource) {
		this.dataSource = dataSource; 
	}
		
	public List<Board> findAll(){
		List<Board> list = new ArrayList<Board>();
		String type = "";
		int bno = 1;
		date = LocalDate.now();
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("select * from board b join user u on b.uno = u.uno");			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Board board = new Board();
				bno++;
				board.setBno(bno);
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("nick"));
				board.setReco(Integer.parseInt(rs.getString("reco")));
				board.setCheck(Integer.parseInt(rs.getString("check")));
				board.setDate(date);

				list.add(board);
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 에러");
		} finally { 
			closeAll();			
		}	
		return list;
	}

	private void closeAll() {
		
		try {
			if(rs != null)    rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null)  conn.close();
			
		} catch (Exception e) {
			System.out.println("DB연결 닫을때 에러발생");
		}
		
	}
//	
//	public Contact find(int id) {
//		Contact contact = null;
//		try {
//			conn = dataSource.getConnection();
//			pstmt = conn.prepareStatement("select * from contacts where id = ?");
//			pstmt.setInt(1, id);
//			rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//				contact = new Contact();
//				contact.setId(rs.getInt("id"));
//				contact.setName(rs.getString("name"));
//				contact.setEmail(rs.getString("email"));
//				contact.setPhone(rs.getString("phone"));
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			closeAll();
//		}
//
//		return contact;
//	}
//	
//	public boolean save(Contact contact) {
//		boolean rowAffected = false;
//
//		try {
//			conn = dataSource.getConnection();
//			pstmt = conn.prepareStatement("insert into contacts (name, email, phone) values (?, ?, ?)");
//			pstmt.setString(1, contact.getName());
//			pstmt.setString(2, contact.getEmail());
//			pstmt.setString(3, contact.getPhone());
//
//			rowAffected = pstmt.executeUpdate() > 0;
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			closeAll();
//		}
//
//		return rowAffected;
//	}
//	
//	public boolean update(Contact contact) {
//		boolean rowAffected = false;
//
//		try {
//			conn = dataSource.getConnection();
//			pstmt = conn.prepareStatement("update contacts set name = ?, email = ?, phone = ? where id = ?");
//			pstmt.setString(1, contact.getName());
//			pstmt.setString(2, contact.getEmail());
//			pstmt.setString(3, contact.getPhone());
//			pstmt.setInt(4, contact.getId());
//			
//			rowAffected = pstmt.executeUpdate() > 0;
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			closeAll();
//		}
//
//		return rowAffected;
//	}
//	
//	public boolean delete(int id) {
//		boolean rowAffected = false;
//
//		try {
//			conn = dataSource.getConnection();
//			pstmt = conn.prepareStatement("delete from contacts where id = ?");
//			pstmt.setInt(1, id);
//
//			rowAffected = pstmt.executeUpdate() > 0;
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			closeAll();
//		}
//
//		return rowAffected;
//	}
//	
//	
//	
	
}
