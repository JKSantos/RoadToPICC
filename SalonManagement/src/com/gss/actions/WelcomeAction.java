package com.gss.actions;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

public class WelcomeAction implements SessionAware{

	
	private Map<String, Object> userSession;
	
	
	public String execute(){
		
		if(userSession.containsKey("firstName") && userSession.containsKey("lastName") && userSession.containsKey("id")){
			return "success";
		}
		else
			return "input";
		
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		
		this.userSession = session;
		
	}
	
	

}
