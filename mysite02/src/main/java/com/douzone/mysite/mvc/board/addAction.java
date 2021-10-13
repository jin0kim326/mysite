package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;

public class addAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
//		String hit
//		String groupNo 
//		String orderNo
//		String depth
//		String userNo
		
		
		BoardVo vo = new BoardVo();
		
		
		vo.setTitle(title);
		vo.setContents(contents);
	}

}
