package com.gss.model;

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

}
