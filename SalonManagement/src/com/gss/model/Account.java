package com.gss.model;

public class Account {
	
	private String strUsername;
	private String strPassword;
	
	public Account(String strUsername, String strPassword){
		this.strUsername = strUsername;
		this.strPassword = strPassword;
	}

	public String getStrUsername() {
		return strUsername;
	}

	public void setStrUsername(String strUsername) {
		this.strUsername = strUsername;
	}

	public String getStrPassword() {
		return strPassword;
	}

	public void setStrPassword(String strPassword) {
		this.strPassword = strPassword;
	}

}
