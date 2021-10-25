package com.douzone.mysite.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nowPage = request.getParameter("nowPage");
		if (nowPage == null) {
			nowPage = "1";
		}
		
		
		
		List<BoardVo> list = new BoardDao().findAll();
				
		request.setAttribute("list", list);
		MvcUtil.forward("/board/list", request, response);
	}

}

/*
 * 1. index()
 * 2. view()
 *  3.delete()
 *  4. modify() - GET
 *  5. modify() - POST
 *  6. write() - GET 
 *  7. write() - POST
 *  8. reply
 * 
 * */
 