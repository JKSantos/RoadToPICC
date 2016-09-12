package com.gss.actions.Employee;

import java.util.List;

import com.gss.dao.Employee.EmployeeJobRepository;
import com.gss.model.EmployeeJob;

public class GetEmployeeJob {
	
	private List<EmployeeJob> jobList;
	private int intEmployeeID;
	
	public String execute(){
		this.jobList = EmployeeJobRepository.getWalkInJob(intEmployeeID);
		
		return "success";
	}

	public List<EmployeeJob> getJobList() {
		return jobList;
	}

	public void setIntEmployeeID(int intEmployeeID) {
		this.intEmployeeID = intEmployeeID;
	}
}
