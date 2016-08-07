package com.gss.actions.SMSSender;

import com.gss.utilities.NotifyCustomerViaSMS;

public class SendSMS {
	
	private String message;
	private String number;
	
	public String execute(){
		
		NotifyCustomerViaSMS test = new NotifyCustomerViaSMS();
		
		test.sendSMS(this.message, this.number);
		
		return "success";
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
