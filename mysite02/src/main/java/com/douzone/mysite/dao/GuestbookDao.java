package com.douzone.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.douzone.mysite.vo.GuestbookVo;

public class GuestbookDao {
	public boolean insert(GuestbookVo vo) {
		
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			
			//SQL 준비
			String sql = "INSERT"
					+ "  	INTO guestbook"
					+ "	  VALUES ( null ,"
					+ "		 	   ?,"
					+ "            ?,"
					+ "            ?,"
					+ "            now())";
			pstmt = conn.prepareStatement(sql);
			
			//바인딩 (binding)
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getMessage());
			
			
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
	public List<GuestbookVo> findAll() {
			List<GuestbookVo> result = new ArrayList<>();
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				conn = getConnection();
				
				//SQL 준비
				String sql = "SELECT no,"
						+ "		     name,"
						+ "		     date_format(reg_date,'%Y-%m-%d %h:%i:%s'),"
						+ "		     message"
						+ "	 	FROM guestbook"
						+ " ORDER BY reg_date desc";
				pstmt = conn.prepareStatement(sql);
				
				// SQL 실행
				rs = pstmt.executeQuery();
				while(rs.next()) {
					Long no = rs.getLong(1);
					String name = rs.getString(2);
					Date regDate  = rs.getDate(3);
					String message = rs.getString(4);
										
					GuestbookVo vo = new GuestbookVo();
					vo.setNo(no);
					vo.setName(name);
					vo.setRegDate(regDate);
					vo.setMessage(message);
					
					result.add(vo);
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
			return result;
	}
	
	public boolean delete(GuestbookVo vo) {
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			//3. SQL 준비
			String sql = 
				"  DELETE"
				+ "  FROM guestbook"
				+ " WHERE no=?"
				+ "   AND password = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			//4. 바인딩(binding)
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getPassword());
			
			//5. SQL 실행
			int count = pstmt.executeUpdate();
			
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// clean up
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
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
