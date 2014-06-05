package com.hung.le.site.chat;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * This controller replaces ChatServlet
 */
@Controller
@RequestMapping("chat")
public class ChatController {
	
	private static final Logger log = LogManager.getLogger();
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(Map<String, Object> model){
		
		log.info("LIST CHAT METHOD HAS BEEN INVOKED.");
		log.debug("NUMBER OF PENDING SESSIONS {}", ChatEndpoint.pendingSessions.size());
		model.put("sessions", ChatEndpoint.pendingSessions);
		return "chat/list";
	}
	
	@RequestMapping(value = "new", method = RequestMethod.POST)
	public String newChat(Map<String, Object> model, HttpServletResponse response){
		
		log.info("NEWCHAT METHOD HAS BEEN INVOKED.");
		this.setNoCacheHeaders(response);
		model.put("chatSessionId", 0);
		return "chat/chat"; 
	}
	
	@RequestMapping(value = "join/{chatSessionId}", method = RequestMethod.POST)
	public String joinChat(Map<String, Object> model, 
						HttpServletResponse response, 
						@PathVariable("chatSessionId") long chatSessionId){
		
		this.setNoCacheHeaders(response);
		model.put("chatSessionId", chatSessionId);
		
		return "chat/chat";
	}
	
	private void setNoCacheHeaders(HttpServletResponse response){
		
		response.setHeader("Expires", "Thu, 1 Jan 1970 12:00:00 GMT");
        response.setHeader("Cache-Control","max-age=0, must-revalidate, no-cache");
        response.setHeader("Pragma", "no-cache");
	}
}
