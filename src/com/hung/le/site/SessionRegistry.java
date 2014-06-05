package com.hung.le.site;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public final class SessionRegistry {

	private static final Map<String, HttpSession> SESSIONS = new Hashtable<>();
	
	private SessionRegistry(){}
	
	public static void addSession(HttpSession session){
		SESSIONS.put(session.getId(), session);
	}
	
	public static void udpateSessionId(HttpSession session, String oldSessionId){
		
		synchronized(SESSIONS){
			SESSIONS.remove(oldSessionId);
			addSession(session);
		}
	}
	
	public static void removeSession(HttpSession session){
		
		SESSIONS.remove(session.getId());
	}
	
	public static List<HttpSession> getAllSessions(){
		
		return new ArrayList<>(SESSIONS.values());
	}
	
	public static int getNumberOfSession(){
		
		return SESSIONS.size();
	}
}
