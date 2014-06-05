package com.hung.le.site;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;
import javax.servlet.http.HttpSessionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionIdListener{
	
	private static final Logger log = LogManager.getLogger();

	@Override
	public void sessionIdChanged(HttpSessionEvent event, String oldSessionId) {
		log.debug("Session ID " + oldSessionId + " changed to " + event.getSession().getId());
        SessionRegistry.udpateSessionId(event.getSession(), oldSessionId);
	}

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		log.debug("Session " + event.getSession().getId() + " created.");
        SessionRegistry.addSession(event.getSession());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		log.debug("Session " + event.getSession().getId() + " destroyed.");
        SessionRegistry.removeSession(event.getSession());
	}
	
	

}
