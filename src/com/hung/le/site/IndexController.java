package com.hung.le.site;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

/*
 * This controller replaces the index.jsp
 */

@Controller
public class IndexController {

	private static final Logger log = LogManager.getLogger();
	
	@RequestMapping("/")
	public View index(){
		log.info("THE INDEX CONTROLLER HAS BEEN INVOKED.");
		return new RedirectView("/ticket/list", true, false);
	}
}
