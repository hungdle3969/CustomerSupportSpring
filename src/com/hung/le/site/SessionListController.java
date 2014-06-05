package com.hung.le.site;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/*
 * This controller replaces the SessionListServlet. The code isn't really 
 * that different except that Spring MVC patterns are used instead of 
 * HttpServletRequest and HttpServletResponse tools
 */
@Controller
@RequestMapping("session")
public class SessionListController {

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(Map<String, Object> model){
		
		model.put("numberOfSessions", SessionRegistry.getNumberOfSession());
		model.put("sessionList", SessionRegistry.getAllSessions());
		model.put("timestamp", System.currentTimeMillis());

		return "session/list";
	}
}
