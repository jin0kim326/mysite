package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardVo> findAll() {
		return sqlSession.selectList("board.findAll");
	}
	
	public BoardVo findBoardOne(Long no) {
		return sqlSession.selectOne("board.findOne", no);
	}

	public int write(BoardVo boardVo) {
		return sqlSession.insert("board.wirte", boardVo);
	}
	
	
	
//	public boolean replyUpdate(BoardVo vo) {
//		boolean result = false;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			conn = getConnection();
//			
//			//SQL 준비
//			String sql = "UPDATE board "
//					+ "  	 SET order_no = order_no + 1"
//					+ "	   WHERE group_no = ?"
//					+ "      AND order_no > ?";
//					
//			pstmt = conn.prepareStatement(sql);
//			
//			//바인딩 (binding)
//			System.out.println(vo);
//			
//			pstmt.setLong(1, vo.getGroupNo());
//			pstmt.setLong(2, vo.getOrderNo());
//			
//			
//			//5. SQL 실행
//			int count = pstmt.executeUpdate();
//			
//			result = count == 1;
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		} finally {
//			try {
//				if (pstmt != null) {
//					pstmt.close();
//				}
//				if ( conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			
//		}
//		return result;
//	}
//	
//	public boolean replyInsert(BoardVo vo) {
//		boolean result = false;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			conn = getConnection();
//			
//			//SQL 준비
//			String sql = "INSERT "
//					+ "  	INTO board"
//					+ "	  VALUES (null,"
//					+ "		      ?,"					
//					+ "           ?,"
//					+ "           0,"
//					+ "           now(),"
//					+ "           ?,"
//					+ "           ?,"
//					+ "           ?,"
//					+ "           ?)";
//			pstmt = conn.prepareStatement(sql);
//			
//			//바인딩 (binding)
//			pstmt.setString(1, vo.getTitle());
//			pstmt.setString(2, vo.getContents());
//			pstmt.setLong(3, vo.getGroupNo());
//			pstmt.setLong(4, vo.getOrderNo());
//			pstmt.setLong(5, vo.getDepth());
//			pstmt.setLong(6, vo.getUserNo());
//			
//			
//			//5. SQL 실행
//			int count = pstmt.executeUpdate();
//			
//			result = count == 1;
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		} finally {
//			try {
//				if (pstmt != null) {
//					pstmt.close();
//				}
//				if ( conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			
//		}
//		return result;
//	}
//	
//	public boolean newInsert(BoardVo vo) {
//		boolean result = false;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			conn = getConnection();
//			
//			//SQL 준비
//			String sql = "INSERT "
//					+ "  	INTO board"
//					+ "	  VALUES (null,"
//					+ "		      ?,"					
//					+ "           ?,"
//					+ "           0,"
//					+ "           now(),"
//					+ "          (select coalesce(max(a.group_no)+1,1) from board a),"
//					+ "           1,"
//					+ "           1,"
//					+ "           ?)";
//			// SELECT COALESCE(Column명1, Column명1이 NULL인 경우 대체할 값)
//			// 첫번째 새글의경우 1, 첫번쨰가 아닌경우는 group_no+1
//
//			pstmt = conn.prepareStatement(sql);
//			
//			//바인딩 (binding)
//			pstmt.setString(1, vo.getTitle());
//			pstmt.setString(2, vo.getContents());
//			pstmt.setLong(3, vo.getUserNo());
//			
//			
//			//5. SQL 실행
//			int count = pstmt.executeUpdate();
//			
//			result = count == 1;
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		} finally {
//			try {
//				if (pstmt != null) {
//					pstmt.close();
//				}
//				if ( conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			
//		}
//		return result;
//	}

//	
//	public List<BoardVo> findBoardList(PagingVo vo) {
//		List<BoardVo> result = new ArrayList<>();
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = getConnection();
//			
//			//SQL 준비
//			String sql = "SELECT b.no,"
//					+ "		 	 b.title,"
//					+ "		 	 b.contents,"
//					+ "          b.hit,"
//					+ "          date_format(b.reg_date,'%Y-%m-%d %h:%i:%s'),"
//					+ "          b.group_no,"
//					+ "          b.order_no,"
//					+ "          b.depth,"
//					+ "          u.name"
//					+ "     FROM board b,"
//					+ "          user u"
//					+ "    WHERE b.user_no = u.no"
//					+ " ORDER BY group_no desc,"
//					+ "			 order_no asc"
//					+ "	   LIMIT ? , ?";
//					
//			pstmt = conn.prepareStatement(sql);
//			
//			pstmt.setLong(1, vo.)
//			// SQL 실행
//			rs = pstmt.executeQuery();
//			while(rs.next()) {
//				Long no = rs.getLong(1);
//				String title = rs.getString(2);
//				String contents = rs.getString(3);
//				Long hit = rs.getLong(4);
//				Date regDate  = rs.getDate(5);
//				Long groupNo = rs.getLong(6);
//				Long orderNo = rs.getLong(7);
//				Long depth = rs.getLong(8);
//				String userName = rs.getString(9);
//									
//				BoardVo vo = new BoardVo();
//				vo.setNo(no);
//				vo.setTitle(title);
//				vo.setContents(contents);
//				vo.setHit(hit);
//				vo.setRegDate(regDate);
//				vo.setGroupNo(groupNo);
//				vo.setOrderNo(orderNo);
//				vo.setDepth(depth);
//				vo.setUserName(userName);
//				
//				result.add(vo);
//			}
//			
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		} finally {
//			try {
//				if (rs != null) {
//					rs.close();
//				}
//				if (pstmt != null) {
//					pstmt.close();
//				}
//				if ( conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			
//		}
//		return result;
//}
//	public BoardVo findByBoardNo(Long boardNo) {
//		BoardVo vo = null;
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = getConnection();
//			
//			//SQL 준비
//			String sql = "SELECT b.no,"
//					+ "		 	 b.title,"
//					+ "		 	 b.contents,"
//					+ "          b.hit,"
//					+ "          date_format(b.reg_date,'%Y-%m-%d %h:%i:%s'),"
//					+ "          b.group_no,"
//					+ "          b.order_no,"
//					+ "          b.depth,"
//					+ "          u.name,"
//					+ "		 	 u.no"
//					+ "     FROM board b,"
//					+ "          user u"
//					+ "    WHERE b.user_no = u.no"
//					+ "      AND b.no = ? "
//					+ " ORDER BY group_no desc,"
//					+ "			 order_no asc";
//			pstmt = conn.prepareStatement(sql);
//			
//			pstmt.setLong(1, boardNo);
//			
//			// SQL 실행
//			rs = pstmt.executeQuery();
//			while(rs.next()) {
//				Long no = rs.getLong(1);
//				String title = rs.getString(2);
//				String contents = rs.getString(3);
//				Long hit = rs.getLong(4);
//				Date regDate  = rs.getDate(5);
//				Long groupNo = rs.getLong(6);
//				Long orderNo = rs.getLong(7);
//				Long depth = rs.getLong(8);
//				String userName = rs.getString(9);
//									
//				vo = new BoardVo();
//				
//				vo.setNo(no);
//				vo.setTitle(title);
//				vo.setContents(contents);
//				vo.setHit(hit);
//				vo.setRegDate(regDate);
//				vo.setGroupNo(groupNo);
//				vo.setOrderNo(orderNo);
//				vo.setDepth(depth);
//				vo.setUserName(userName);
//			}
//			
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		} finally {
//			try {
//				if (rs != null) {
//					rs.close();
//				}
//				if (pstmt != null) {
//					pstmt.close();
//				}
//				if ( conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			
//		}
//		return vo;
//	}
//	
	
	
}
