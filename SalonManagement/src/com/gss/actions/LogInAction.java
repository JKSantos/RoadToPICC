package com.gss.actions;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.quartz.SchedulerException;

import com.gss.model.Employee;
import com.gss.service.EmployeeServiceImpl;
import com.gss.testers.PromoChecker;
import com.opensymphony.xwork2.ActionSupport;

public class LogInAction extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = 1L;
	private String username;
	private String pass;
	private Map<String, Object> userSession;
	
	public String execute(){
		
		EmployeeServiceImpl login = new EmployeeServiceImpl();
		Employee emp = login.getEmployeeByUserPass(this.username, this.pass);
		String success = "success";
		String input = "input";
		
		if(userSession.containsKey("firstName") && userSession.containsKey("lastName") && userSession.containsKey("id")){
			
			return success;
		}
		
		if(emp != null){
			
			if(emp.isAccessGranted() == false)
			{
				return "unauthorized";
			}
			else{
				userSession.put("firstName", emp.getStrEmpFirstName());
				userSession.put("lastName", emp.getStrEmpLastName());
				userSession.put("username", emp.getStrEmpUsername());
				userSession.put("id", emp.getIntEmpID());
				return success;
			}
		}
		else
			return input;
		
	}

	public String logout(){
		
		userSession.remove("firstName");
		userSession.remove("lastName");
		userSession.remove("id");
		
		return "success";
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		
		this.userSession = session;
	}

}
