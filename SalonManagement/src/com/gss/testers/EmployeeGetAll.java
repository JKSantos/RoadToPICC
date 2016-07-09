package com.gss.testers;

import java.util.List;

import com.gss.model.Employee;
import com.gss.model.Job;
import com.gss.service.EmployeeServiceImpl;

public class EmployeeGetAll {
public static void main(String[] args){
		
		EmployeeServiceImpl service = new EmployeeServiceImpl();
		
		List<Employee> empList= service.getAllEmployees();
		if(empList != null)
		{
			for(int j = 0; j < empList.size(); j++)
			{
				Employee emp = empList.get(j);
				System.out.println(emp.getStrEmpLastName() + " " + emp.getStrEmpFirstName() + " " + " " + emp.getStrEmpMiddleName()); 
				
				System.out.println("Jobs:");
				
				List<Job> jobList = emp.getStrJobQualification();
				
				for(int i = 0; i < emp.getStrJobQualification().size(); i++){
					Job job = jobList.get(i);
					System.out.println("\t" + job.getStrJobDesc());
				}
			
			}
			
		}
		
	}
}
