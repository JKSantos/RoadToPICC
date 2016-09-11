package com.gss.actions.CustomerAccount;

import java.sql.SQLException;

import com.gss.dao.CustomerRegistration;

public class VerifyAccount {
	
	private int intVerificationID;
	private String strVerificationCode;
	private int intAccountID;
	
	private String result;
	
	public String execute() throws SQLException{
		
		System.out.println(this.intVerificationID);
		String verificationCode = CustomerRegistration.getVerificationCode(this.intVerificationID);
		
		System.out.print(verificationCode + " = ");
		
		if(this.strVerificationCode.equals(verificationCode)){
			this.result = CustomerRegistration.verifyAccount(this.intAccountID, this.intVerificationID);
			return result;
		}else{
			this.result = "incorrect";
			return result;
		}
		
	}
	
	public void setIntVerificationID(int intVerificationID) {
		this.intVerificationID = intVerificationID;
	}
	public void setStrVerificationCode(String strVerificationCode) {
		this.strVerificationCode = strVerificationCode;
	}
	public void setIntAccountID(int intAccountID) {
		this.intAccountID = intAccountID;
	}

	public String getResult() {
		return result;
	}
	
}
