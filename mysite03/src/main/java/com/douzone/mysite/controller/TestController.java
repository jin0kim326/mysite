package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.Controller;

@Controller
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	public String list () {
		
		return "notice.list";
	}
	
	public String detail() {
		
		return "notice.detail";
	}
}

/*
 * context:component-scan base-package="com.douzone.mysite.noticeController"
 * 
 * 
 * 
 * */
