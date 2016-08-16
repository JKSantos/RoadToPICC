package com.gss.actions.Employee;

import java.util.List;

import com.gss.model.Employee;

public class QueryAllEmployee {
	
	private List<Employee> employeeList;
	
	public String execute(){
		
		this.employeeList = Employee.queryAllEmployee();
		
		return "success";
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}
}
