package com.gss.actions.Employee;

import com.gss.dao.Employee.EmployeeJobRepository;

public class UpdateJobStatus {
	
	private int intJobID;
	private String strTransType;
	private String strJobType;
	private String strStatus;
	private String result = "failed";
	
	public String execute(){
		
		boolean recorded = EmployeeJobRepository.updateJobStatus(intJobID, strTransType, strJobType, strStatus);
		
		if(recorded == true)
			result = "success";
		
		return result;
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

	public String getResult() {
		return result;
	}
}
