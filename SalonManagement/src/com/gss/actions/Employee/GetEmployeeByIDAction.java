package com.gss.actions.Employee;

import com.gss.model.Employee;
import com.gss.service.EmployeeService;
import com.gss.service.EmployeeServiceImpl;

public class GetEmployeeByIDAction {
	
	private int intEmpID;
	private Employee employee;
	private String result;
	
	public String execute(){
		
		EmployeeService employee = new EmployeeServiceImpl();
		this.employee = employee.getEmployeeByID(this.intEmpID);
		
		if(this.employee != null)
			result = "success";
			
		return result;
	}


	public void setIntEmpID(int intEmpID) {
		this.intEmpID = intEmpID;
	}


	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
