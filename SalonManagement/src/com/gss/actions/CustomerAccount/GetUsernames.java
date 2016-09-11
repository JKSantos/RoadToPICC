package com.gss.actions.CustomerAccount;

import java.util.List;

import com.gss.dao.CustomerRegistration;

public class GetUsernames {
	
	private List<String> unsernames;
	
	public String execute(){
		this.unsernames = CustomerRegistration.getAllUsername();
		
		return "success";
	}

	public List<String> getUnsernames() {
		return unsernames;
	}
}
