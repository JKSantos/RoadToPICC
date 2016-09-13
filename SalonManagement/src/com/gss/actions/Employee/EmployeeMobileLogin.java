package com.gss.actions.Employee;

import com.gss.dao.Employee.EmployeeMobileLogIn;
import com.gss.model.Employee;

public class EmployeeMobileLogin {
	
	private String username;
	private String password;
	
	private Employee employee;
	private String result;
	
	public String execute(){
		
		this.employee = EmployeeMobileLogIn.logIn(username, password);
		
		if(employee.getIntEmpID() == 0){
			this.employee = null;
			result = "incorrect";
			return result;
		}
		else{
			result = "success";
			return result;
		}
	}

	public Employee getEmployee() {
		return employee;
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
