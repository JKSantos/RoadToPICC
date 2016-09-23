package com.gss.model;

public class MiniEmployee {

	private int id;
	private String empName;


	public MiniEmployee(int id, String empName) {
		super();
		this.id = id;
		this.empName = empName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
}
