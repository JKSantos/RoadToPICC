package com.gss.actions;

import com.gss.dao.EmployeeJDBCRepository;
import com.gss.model.Employee;

public class EmployeePosition {
	
	public String positionName;
	public int type;
	
	public String result;
	
	public String add(){
		
		if(EmployeeJDBCRepository.searchJob(positionName).equalsIgnoreCase("EXISTING")){
			result = "existing";
			return "existing";
		}else{
			if(Employee.position(positionName, type) == true){
				result = "success";
				return "success";
			}else{
				result = "failed";
				return "failed";
			}
		}
	}
	
	public String remove(){
		if(Employee.position(positionName, type) == true){
			result = "success";
			return "success";
		}else{
			result = "failed";
			return "failed";
		}
	}

	public String getResult() {
		return result;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public void setType(int type) {
		this.type = type;
	}
}
