package com.gss.actions.CustomerAccount;

import com.gss.dao.CustomerRegistration;
import com.gss.model.CustomerAccount;

public class LoginCustomer {
	
	private String username;
	private String password;
	private CustomerAccount customer;
	
	private String result;

	public String execute() {
		this.customer = CustomerRegistration.logInCustomer(username, password);
		
		try{
			customer.getIntCustID();
			this.result = "success";
			return result;
		}catch(NullPointerException e){
			this.result = "invalid";
			return result;
		}
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

	public CustomerAccount getCustomer() {
		return customer;
	}
}
