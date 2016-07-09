package com.gss.utilities;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

public class SessionHelper implements SessionAware{
	
	private Map<String, Object> userSession;

	@Override
	public void setSession(Map<String, Object> session) {
		
		this.userSession = session;
		
	}

}
