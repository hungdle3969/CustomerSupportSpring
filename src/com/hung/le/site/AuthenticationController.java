package com.hung.le.site;

import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

/*
 * This filter replaces LoginServlet
 */
@Controller
public class AuthenticationController {

	private static final Logger log = LogManager.getLogger();
	private static final Map<String, String> userDatabase = new Hashtable<>();
	
	static {
		
		userDatabase.put("hungle", "hungpass");
		userDatabase.put("lanle", "lanpass");
		userDatabase.put("chasele", "chasepass");
		userDatabase.put("audreyle", "audreypass");
		userDatabase.put("danhle", "danhpass");
	}
	
	@RequestMapping("logout")
    public View logout(HttpSession session)
    {
        if(log.isDebugEnabled())
            log.debug("User {} logged out.", session.getAttribute("username"));
        session.invalidate();

        return new RedirectView("/login", true, false);
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView login(Map<String, Object> model, HttpSession session)
    {
    	log.debug("AUTHENTICATION CONTROLLER - LOGIN GET HAS BEEN INVOKED.");
        if(session.getAttribute("username") != null)
            return this.getTicketRedirect();

        model.put("loginFailed", false);
        model.put("loginForm", new UserForm());

        return new ModelAndView("login");
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelAndView login(Map<String, Object> model, HttpSession session,
                              HttpServletRequest request, UserForm userForm)
    {
    	log.debug("AUTHENTICATION CONTROLLER - LOGIN POST HAS BEEN INVOKED.");
    	
        if(session.getAttribute("username") != null)
            return this.getTicketRedirect();

        if(userForm.getUsername() == null || userForm.getPassword() == null ||
                !userDatabase.containsKey(userForm.getUsername()) ||
                !userForm.getPassword().equals(userDatabase.get(userForm.getUsername())))
        {
            log.warn("Login failed for user {}.", userForm.getUsername());
            userForm.setPassword(null);
            model.put("loginFailed", true);
            model.put("loginForm", userForm);
            return new ModelAndView("login");
        }

        log.debug("User {} successfully logged in.", userForm.getUsername());
        session.setAttribute("username", userForm.getUsername());
        request.changeSessionId();
        return this.getTicketRedirect();
    }

    private ModelAndView getTicketRedirect()
    {
        return new ModelAndView(new RedirectView("/ticket/list", true, false));
    }
	
	public static class UserForm{
		
		private String username;
		private String password;
		
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
		
	}
	
}
