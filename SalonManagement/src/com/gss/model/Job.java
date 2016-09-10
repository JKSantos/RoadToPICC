package com.gss.model;

import java.util.List;

import com.gss.dao.EmployeeJDBCRepository;

public class Job {
	
	private String strJobDesc;
	private int intJobStatus;
	
	public Job(String strJobDesc, int intJobStatus){
		this.strJobDesc = strJobDesc;
		this.intJobStatus = intJobStatus;
	}

	public String getStrJobDesc() {
		return strJobDesc;
	}

	public void setStrJobDesc(String strJobDesc) {
		this.strJobDesc = strJobDesc;
	}

	public int getIntJobStatus() {
		return intJobStatus;
	}

	public void setIntJobStatus(int intJobStatus) {
		this.intJobStatus = intJobStatus;
	}

	public static List<Job> getJob(){
		return EmployeeJDBCRepository.getJob();
	}
}
