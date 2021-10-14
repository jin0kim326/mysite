package com.douzone.mysite.mvc.board;

import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;
import com.douzone.web.util.MvcUtil;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if("addform".equals(actionName)) {
			action = new addFormAction();
		} else if ("write".equals(actionName)) {
			action = new addAction();
		} else if ("view".equals(actionName)) {
			action = new viewAction();
		} else {
			action = new ListAction();
		}
		
		
		return action;
	}

}

