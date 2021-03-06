package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class addAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String boardNo = request.getParameter("boardNo");
		
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		String userNo = request.getParameter("userNo");
		String groupNo = request.getParameter("groupNo");
		String orderNo = request.getParameter("orderNo");
		String depth = request.getParameter("depth");
		
		String board = request.getParameter("board");
		
		BoardVo vo = new BoardVo();	

		vo.setTitle(title);
		vo.setContents(contents);
		vo.setUserNo(Long.parseLong(userNo));
		
		if ( "".equals(boardNo) || boardNo == null) {
			// 새글작성의 경우
			System.out.println("새글인경우 ::" + vo);
			new BoardDao().newInsert(vo);
			
		} else {
			// 답글작성의 경우 ( update + insert )
			vo.setGroupNo(Long.parseLong(groupNo));
			vo.setOrderNo(Long.parseLong(orderNo));
			new BoardDao().replyUpdate(vo); 
			
			Long setOrderNo = Long.parseLong(orderNo)+1L;
			Long setDepth = Long.parseLong(orderNo)+1L; 
			vo.setGroupNo(Long.parseLong(groupNo));
			vo.setOrderNo(setOrderNo);
			vo.setDepth(setDepth);
			
			new BoardDao().replyInsert(vo);
		}
		
		MvcUtil.redirect(request.getContextPath() + "/board", request, response);
	}
}
