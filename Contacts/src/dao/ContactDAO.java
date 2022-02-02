package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import model.Contact;

public class ContactDAO { // DB에 있는 contacts 테이블을 CRUD 하는 클래스
	private DataSource dataSource; // jdbc/demo Connection pool 연결객체
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public ContactDAO(DataSource dataSource) {
		this.dataSource = dataSource; // 객체 생성시 Connection pool에 DataSource를 입력
	}
	
	// 모든 연락처를 list로 return
	public List<Contact> findAll() {
		List<Contact> list = new ArrayList<Contact>();
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("select * from contacts");
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Contact contact = new Contact();
				contact.setId(rs.getInt("id"));
				contact.setName(rs.getString("name"));
				contact.setEmail(rs.getString("email"));
				contact.setPhone(rs.getString("phone"));
				
				list.add(contact);
			}
		} catch (SQLException e) {
			System.out.println("SQL에러");
		} finally {
			closeAll();
		}
		
		return list;
	}

	private void closeAll() {
		try {
			// 나중에 생성한 순서부터 닫음 rs=> pstmt => conn(pool로 되돌아감)
			// close()하는 이유는 많은 접속이 동시에 이루어질 경우 메모리 사용 비율이 높아져 서버가 퍼짐
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch (Exception e2) {
			System.out.println("DB연결 닫을 때 ERROR발생");
		}
		
	}
	
	public Contact find (int id) {
		Contact contact = null;
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("select * from contacts where id=?");
			
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
			String name = rs.getString("name");
			String email = rs.getString("email");
			String phone = rs.getString("phone");
			
			contact = new Contact(name, email, phone);
			}
		} catch (SQLException e) {
			System.out.println("SQL ERROR");	
		}
		return contact;
	}
	
	public boolean save(Contact contact) {
		boolean rowAffected = false;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("insert into contacts(name, email, phone) values(?,?,?)");
			
			pstmt.setString(1, contact.getName());
			pstmt.setString(2, contact.getEmail());
			pstmt.setString(3, contact.getPhone());
			
			rowAffected = pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("DB 저장 실패");
		}
		return rowAffected;
	}
	
	public boolean update(Contact contact) {
		boolean rowAffected = false;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("update contacts set name=?, email=?, phone=? where id=?");
			
			pstmt.setString(1, contact.getName());
			pstmt.setString(2, contact.getEmail());
			pstmt.setString(3, contact.getPhone());
			pstmt.setInt(4, contact.getId());
			
			rowAffected = pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("DB 수정 실패");
		}
		return rowAffected;
	}
	
	public boolean delete(int id) {
		boolean rowAffected = false;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("delete from contacts where id=?");
			
			pstmt.setInt(1, id);
			
			rowAffected = pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("DB 삭제 실패");
		}
		return rowAffected;
	}
}
