package com.gss.testers;

import java.util.List;

import com.gss.model.Employee;
import com.gss.service.EmployeeServiceImpl;

public class GetAllEmployee {
	
	public static void main(String[] args){
		EmployeeServiceImpl service = new EmployeeServiceImpl();
		List<Employee> empList = service.getAllEmployees();
		
		for(int i = 0; i <empList.size(); i++){
			System.out.println("");
		}
	}

}
