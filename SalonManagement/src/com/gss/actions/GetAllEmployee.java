package com.gss.actions;

import java.util.List;

import com.gss.model.Discount;
import com.gss.model.Employee;
import com.gss.service.DiscountService;
import com.gss.service.DiscountServiceImpl;
import com.gss.service.EmployeeService;
import com.gss.service.EmployeeServiceImpl;

public class GetAllEmployee {
	
	private List<Employee> employeeList;
	private String result;
	
	public String execute(){
		
		EmployeeService service = new EmployeeServiceImpl();
		this.employeeList = service.getAllEmployeeNoImage();
		
		
		result = "success";
		return result;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}
	
	public String getResult(){
		return this.result;
	}
	

}
