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
		
		BoardVo vo = new BoardVo();	
		
	
		vo.setTitle(title);
		vo.setContents(contents);
		vo.setUserNo(Long.parseLong(userNo));
		
		
//		new BoardDao().newInsert(vo);
		MvcUtil.redirect(request.getContextPath() + "/board", request, response);
	}
}
