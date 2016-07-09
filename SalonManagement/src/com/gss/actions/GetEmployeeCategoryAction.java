package com.gss.actions;

import java.util.List;

import com.gss.model.EmployeeCategory;
import com.gss.service.EmployeeServiceImpl;

public class GetEmployeeCategoryAction {
	
	private List<EmployeeCategory> empCategory;
	
	public String execute(){
		
		EmployeeServiceImpl empService = new EmployeeServiceImpl();
		this.empCategory = empService.getAllCategory();
		
		return "success";
	}

	public List<EmployeeCategory> getEmpCategory() {
		return empCategory;
	}

	public void setEmpCategory(List<EmployeeCategory> empCategory) {
		this.empCategory = empCategory;
	}
}
