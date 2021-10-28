package com.douzone.mysite.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.security.AuthUser;
import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping( "/board" )
public class BoardController {
	@Autowired
	private BoardService boardService;

	@RequestMapping("")
	public String index(@RequestParam( value="kwd",required=true, defaultValue="") String keyword,
						@RequestParam( value="p", required=true, defaultValue="1") Integer page,
						Model model) {
		Map<String, Object> map = boardService.getBoardList(keyword, page);
		model.addAttribute("map", map);
		return "board/index";
	}
	
	/* 게시판 한개 보여주기 */
	@RequestMapping("/view/{no}")
	public String view(@PathVariable("no") Long no, Model model) {
		BoardVo boardVo = boardService.getBoardOne(no);
		System.out.println(boardVo);
		model.addAttribute("boardVo", boardVo);
		return "board/view";
	}
	
	/* 게시판 새글 쓰기(폼 포워딩) */
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write() {
		return "board/write";
	}
	
	/* 게시판 새글 쓰기 */
	@RequestMapping( value="/write", method=RequestMethod.POST )	
	public String write(
						@AuthUser UserVo authUser,	
						@ModelAttribute BoardVo boardVo
						 ) {
		boardVo.setUserNo( authUser.getNo() );
		boardService.write( boardVo );
		return	"redirect:/board";
	}
	
	/* 답글 달기 */
	@RequestMapping( value="/reply/{no}" )	
	public String reply(@AuthUser UserVo authUser, 
						@PathVariable( "no" ) Long no, Model model) {
		
		BoardVo boardVo = boardService.getBoardOne( no );
		System.out.println(boardVo);
		boardVo.setOrderNo( boardVo.getOrderNo() + 1 );
		boardVo.setDepth( boardVo.getDepth() + 1 );
		
		model.addAttribute( "boardVo", boardVo );
		
		return "board/reply";
	}
	
	@RequestMapping(value="/delete/{no}")
	public String delete (@AuthUser UserVo authUser,
						  @PathVariable("no") Long no ) {
		boardService.deleteOne(no, authUser.getNo());
	
		return "redirect:/board";
	}
	
	@RequestMapping( value="/modify/{no}" )	
	public String modify(
		@AuthUser UserVo authUser,
		@PathVariable( "no" ) Long no,
		Model model) {
		
		BoardVo boardVo = boardService.getBoardOne(no, authUser.getNo() );
		model.addAttribute( "boardVo", boardVo );
		return "board/modify";
	}
	
	@RequestMapping( value="/modify", method=RequestMethod.POST )	
	public String modify(
		@AuthUser UserVo authUser,
		@ModelAttribute BoardVo boardVo
		) {
		
		boardVo.setUserNo( authUser.getNo() );
		boardService.modifyBoard( boardVo );
		return "redirect:/board/view/" + boardVo.getNo();
	}

}
/*
 
 @RequestMapping( "" )
	public String index(
		@RequestParam( value="p", required=true, defaultValue="1") Integer page,
		@RequestParam( value="kwd", required=true, defaultValue="") String keyword,
		Model model) {
		
		Map<String, Object> map = boardService.getContentsList( page, keyword );
		model.addAttribute("map", map);
		//model.addAllAttributes(map);
		
		return "board/index";
	}
	
	@RequestMapping( "/view/{no}" )
	public String view( @PathVariable( "no" ) Long no, Model model ) {
		BoardVo boardVo = boardService.getContents( no );
		model.addAttribute( "boardVo", boardVo );
		return "board/view";
	}

	@RequestMapping( "/delete/{no}" )
	public String delete(
		HttpSession session,	
		@PathVariable( "no" ) Long boardNo,
		@RequestParam( value="p", required=true, defaultValue="1") Integer page,
		@RequestParam( value="kwd", required=true, defaultValue="") String keyword ) {
		
		// 접근제어(Access Control List)
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		//////////////////////////////////////////////////////////		
		
		boardService.deleteContents( boardNo, authUser.getNo() );
		return "redirect:/board?p=" + page + "&kwd=" + WebUtil.encodeURL( keyword, "UTF-8" );
	}
	
	@RequestMapping( value="/modify/{no}" )	
	public String modify(
		HttpSession session,
		@PathVariable( "no" ) Long no,
		Model model) {
		
		// 접근제어(Access Control List)
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		//////////////////////////////////////////////////////////		
		
		BoardVo boardVo = boardService.getContents(no, authUser.getNo() );
		model.addAttribute( "boardVo", boardVo );
		return "board/modify";
	}

	@RequestMapping( value="/modify", method=RequestMethod.POST )	
	public String modify(
		HttpSession session,
		@ModelAttribute BoardVo boardVo,
		@RequestParam( value="p", required=true, defaultValue="1") Integer page,
		@RequestParam( value="kwd", required=true, defaultValue="") String keyword ) {
		
		// 접근제어(Access Control List)
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		//////////////////////////////////////////////////////////		
		
		boardVo.setUserNo( authUser.getNo() );
		boardService.modifyContents( boardVo );
		return "redirect:/board/view/" + boardVo.getNo() + 
				"?p=" + page + 
				"&kwd=" + WebUtil.encodeURL( keyword, "UTF-8" );
	}

	@Auth
	@RequestMapping( value="/write", method=RequestMethod.GET )	
	public String write() {
		return "board/write";
	}

	@RequestMapping( value="/write", method=RequestMethod.POST )	
	public String write(
		HttpSession session,
		@ModelAttribute BoardVo boardVo,
		@RequestParam( value="p", required=true, defaultValue="1") Integer page,
		@RequestParam( value="kwd", required=true, defaultValue="") String keyword ) {
		
		// 접근제어(Access Control List)
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		//////////////////////////////////////////////////////////
		
		boardVo.setUserNo( authUser.getNo() );
		boardService.addContents( boardVo );
		
		return	"redirect:/board?p=" + page + "&kwd=" + WebUtil.encodeURL( keyword, "UTF-8" );
	}

	
  
 */
 