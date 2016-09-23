package com.gss.actions.Employee;

import java.util.List;

import com.gss.model.MiniEmployee;

public class GetEmployeeBySpecialization {
	
	private String type;
	private int id;
	
	private List<MiniEmployee> empList;
	
	public String execute(){
		return "success";
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<MiniEmployee> getEmpList() {
		return empList;
	}
}
