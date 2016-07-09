package com.gss.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.gss.model.Employee;
import com.gss.model.EmployeeCategory;
import com.gss.model.Job;
import com.gss.service.EmployeeServiceImpl;

public class EmployeeMaintenanceAction implements SessionAware{

	private List<EmployeeCategory> empCategory;
	private List<Employee> empList;
	private List<Job> jobList = new ArrayList<Job>();
	private Map<String, Object> userSession;
	
	public String execute(){
	
		if(!userSession.containsKey("firstName") && !userSession.containsKey("lastName") && !userSession.containsKey("id")){
			
			return "input";
		}
		else{
		
			EmployeeServiceImpl empService = new EmployeeServiceImpl();
			this.empCategory = empService.getAllCategory();
			this.empList = empService.getAllEmployees();
		
			return "success";
		}
	}

	public List<Employee> getEmpList() {
		return empList;
	}

	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}

	public List<EmployeeCategory> getEmpCategory() {
		return empCategory;
	}

	public void setEmpCategory(List<EmployeeCategory> empCategory) {
		this.empCategory = empCategory;
	}

	public List<Job> getJobList() {
		return jobList;
	}

	public void setJobList(List<Job> jobList) {
		this.jobList = jobList;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		
		this.userSession = arg0;
	}
}
