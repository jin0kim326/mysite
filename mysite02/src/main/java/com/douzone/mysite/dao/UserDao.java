package com.douzone.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.douzone.mysite.vo.GuestbookVo;
import com.douzone.mysite.vo.UserVo;

public class UserDao {
	public UserVo findByEmailAndPassword(String email, String password) {
		UserVo vo = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			//SQL 준비
			String sql = "SELECT no,"
					+ "	  		 name"
					+ "  	FROM user"
					+ "    WHERE email = ? "
					+ "  	 AND password = ? ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			
			// SQL 실행
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				// 로그인 성공 
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				
				vo = new UserVo();
				vo.setNo(no);
				vo.setName(name);
			}
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if ( conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return vo;
}
	public boolean insert(UserVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			
			//SQL 준비
			String sql = "INSERT "
					+ "     INTO user"
					+ "   VALUES (null,"
					+ "			  ?,"
					+ "           ?,"
					+ "           ?,"
					+ "           ?,"
					+ "          now())";
			pstmt = conn.prepareStatement(sql);
			
			//바인딩 (binding)
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());
			
			
			//5. SQL 실행
			int count = pstmt.executeUpdate();
			
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if ( conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return result;
	}

	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		
		try {
			// 1. JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			// 2. 연결하기
			String url = "jdbc:mysql://127.0.0.1:3306/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패: "+e);
		}
		return conn;
	}
}
