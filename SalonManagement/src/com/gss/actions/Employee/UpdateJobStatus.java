package com.gss.actions.Employee;

public class UpdateJobStatus {
	
	private int intEmployeeID;
	private int intJobID;
	private String strTransType;
	private String strJobType;
	private String strStatus;
	
	public String execute(){
		return "success";
	}
	
	public void setIntEmployeeID(int intEmployeeID) {
		this.intEmployeeID = intEmployeeID;
	}
	public void setIntJobID(int intJobID) {
		this.intJobID = intJobID;
	}
	public void setStrTransType(String strTransType) {
		this.strTransType = strTransType;
	}
	public void setStrJobType(String strJobType) {
		this.strJobType = strJobType;
	}
	public void setStrStatus(String strStatus) {
		this.strStatus = strStatus;
	}
}
