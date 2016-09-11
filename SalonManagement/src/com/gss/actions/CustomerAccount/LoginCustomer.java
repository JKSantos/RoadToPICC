package com.gss.actions.CustomerAccount;

import com.gss.dao.CustomerRegistration;

public class LoginCustomer {
	
	private String username;
	private String password;
	
	private String result;

	public String execute() {
		this.result = CustomerRegistration.logInCustomer(username, password);
		
		return result;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getResult() {
		return result;
	}
}
