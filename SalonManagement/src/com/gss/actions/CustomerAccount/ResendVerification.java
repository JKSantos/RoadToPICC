package com.gss.actions.CustomerAccount;

import com.gss.dao.CustomerRegistration;

public class ResendVerification {
	
	private String code;
	private int intCustomerID;
	
	public String execute(){
		
		boolean result = CustomerRegistration.resendSms(code, intCustomerID);
		
		if(result == true)
			return "success";
		else
			return "failed";
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setIntCustomerID(int intCustomerID) {
		this.intCustomerID = intCustomerID;
	}
}
