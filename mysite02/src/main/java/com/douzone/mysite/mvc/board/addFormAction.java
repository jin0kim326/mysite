package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class addFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String boardNo = request.getParameter("boardNo");
		
		if (boardNo != null) {
		BoardVo board = new BoardDao().findByBoardNo(Long.parseLong(boardNo));
		request.setAttribute("board", board);
		System.out.println("bn:::"+ boardNo);
		System.out.println("fuxk::::"+board);
		}
		
		MvcUtil.forward("/board/writeform", request, response);
	}

}
