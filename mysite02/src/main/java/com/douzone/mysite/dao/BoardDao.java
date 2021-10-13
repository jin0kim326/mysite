package com.douzone.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.GuestbookVo;

public class BoardDao {
public boolean insert(BoardVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			
			//SQL 준비
			String sql = "INSERT "
					+ "  	INTO book"
					+ "	  VALUES (null,"
					+ "		      ?,"					
					+ "           ?,"
					+ "           ?,"
					+ "          now(),"
					+ "           ?,"
					+ "           ?,"
					+ "           ?,"
					+ "           ?)";
			pstmt = conn.prepareStatement(sql);
			
			//바인딩 (binding)
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getHit());
			pstmt.setLong(4, vo.getGroupNo());
			pstmt.setLong(5, vo.getOrderNo());
			pstmt.setLong(6, vo.getDepth());
			pstmt.setLong(7, vo.getUserNo());
			
			
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
	public List<BoardVo> findAll() {
			List<BoardVo> result = new ArrayList<>();
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				conn = getConnection();
				
				//SQL 준비
				String sql = "SELECT b.no,"
						+ "		 	 b.title,"
						+ "		 	 b.contents,"
						+ "          b.hit,"
						+ "          date_format(b.reg_date,'%Y-%m-%d %h:%i:%s'),"
						+ "          b.group_no,"
						+ "          b.order_no,"
						+ "          b.depth,"
						+ "          u.name"
						+ "     FROM board b,"
						+ "          user u"
						+ "    WHERE b.user_no = u.no"
						+ " ORDER BY group_no desc,"
						+ "			 order_no asc";
				pstmt = conn.prepareStatement(sql);
				
				// SQL 실행
				rs = pstmt.executeQuery();
				while(rs.next()) {
					Long no = rs.getLong(1);
					String title = rs.getString(2);
					String contents = rs.getString(3);
					Long hit = rs.getLong(4);
					Date regDate  = rs.getDate(5);
					Long groupNo = rs.getLong(6);
					Long orderNo = rs.getLong(7);
					Long depth = rs.getLong(8);
					String userName = rs.getString(9);
										
					BoardVo vo = new BoardVo();
					vo.setNo(no);
					vo.setTitle(title);
					vo.setContents(contents);
					vo.setHit(hit);
					vo.setRegDate(regDate);
					vo.setGroupNo(groupNo);
					vo.setOrderNo(orderNo);
					vo.setDepth(depth);
					vo.setUserName(userName);
					
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
