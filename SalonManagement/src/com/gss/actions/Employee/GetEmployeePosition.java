package com.gss.actions.Employee;

import java.util.List;

import com.gss.model.Job;

public class GetEmployeePosition {
	
	private List<Job> jobList;
	
	public String execute(){
		this.jobList = Job.getJob();
		
		return "success";
	}

	public List<Job> getJobList() {
		return jobList;
	}
}
