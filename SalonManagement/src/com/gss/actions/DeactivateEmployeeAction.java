package com.gss.actions;

import com.gss.service.EmployeeService;
import com.gss.service.EmployeeServiceImpl;

public class DeactivateEmployeeAction {
	
	private int intEmpID;
	
	public String execute(){
		
		EmployeeService service = new EmployeeServiceImpl();
		
		if(service.deactivateEmployee(this.intEmpID) == true){
			return "success";
		}
		else{
			return "failed";
		}
	}

	public int getIntEmpID() {
		return intEmpID;
	}

	public void setIntEmpID(int intEmpID) {
		this.intEmpID = intEmpID;
	}

}
