package com.gss.actions.Employee;

import java.util.List;

import com.gss.dao.EmployeeJDBCRepository;

public class GetAllUserNames {
	
	private List<String> usernames;
	
	public String execute(){
		this.usernames = EmployeeJDBCRepository.getAllUsername();
		
		return "success";
	}

	public List<String> getUsernames() {
		return usernames;
	}
}
