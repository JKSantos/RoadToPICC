package com.gss.actions.Employee;

import com.gss.dao.EmployeeJDBCRepository;

public class ChangeUsernameAndPassword {
	
	private int intEmployeeID;
	private String strUsername;
	private String strPassword;
	private String oldPassword;
	
	private String result;
	
	public String changeUserName(){
		boolean changed = EmployeeJDBCRepository.changeUserName(strUsername, intEmployeeID);
		
		if(changed == true){
			result = "success";
			return result;
		}else{
			result = "failed";
			return result;
		}
	}
	
	public String changePassword(){
		String oldPassword = EmployeeJDBCRepository.getCurrentPassword(intEmployeeID);
		
		if(oldPassword.equals(this.oldPassword)){
			EmployeeJDBCRepository.changePassword(strPassword, intEmployeeID);
			result = "success";
			return result;
		}else{
			result = "incorrect";
			return result;
		}
	}

	public String getResult() {
		return result;
	}

	public void setIntEmployeeID(int intEmployeeID) {
		this.intEmployeeID = intEmployeeID;
	}

	public void setStrUsername(String strUsername) {
		this.strUsername = strUsername;
	}

	public void setStrPassword(String strPassword) {
		this.strPassword = strPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
}


