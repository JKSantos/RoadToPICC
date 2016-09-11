package com.gss.actions.CustomerAccount;

import java.sql.SQLException;

import com.gss.dao.CustomerRegistration;
import com.gss.model.CustomerAccount;
import com.gss.model.Verification;

public class RegisterCustomer {
	
	private String strName;
	private String strContact;
	private String strEmail;
	private String strUsername;
	private String strPassword;
	
	private Verification verification;

	
	public String execute() throws SQLException{
		
		CustomerAccount account = new CustomerAccount(1, this.strName, this.strContact, this.strEmail, this.strUsername, this.strPassword);
		
		this.verification = CustomerRegistration.createCustomer(account);
		
		if(this.verification.getIntCustomerID() == 0)
			return "failed";
		else
			return "success";
	}

	public Verification getVerification() {
		return verification;
	}

	public void setStrName(String strName) {
		this.strName = strName;
	}

	public void setStrContact(String strContact) {
		this.strContact = strContact;
	}

	public void setStrEmail(String strEmail) {
		this.strEmail = strEmail;
	}

	public void setStrUsername(String strUsername) {
		this.strUsername = strUsername;
	}

	public void setStrPassword(String strPassword) {
		this.strPassword = strPassword;
	}
}
