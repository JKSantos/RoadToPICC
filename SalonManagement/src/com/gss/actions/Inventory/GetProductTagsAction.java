package com.gss.actions.Inventory;

import java.util.List;

import com.gss.model.Employee;
import com.gss.model.ProductTag;

public class GetProductTagsAction {
	
	private List<Employee> employeeList;
	private List<ProductTag> tagList;
	
	public String execute(){
		this.employeeList = Employee.getEmployeeList();
		this.tagList = ProductTag.getAllTagList();

		return "success";
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public List<ProductTag> getTagList() {
		return tagList;
	}
}
