package com.gss.testers;

import java.util.List;

import com.gss.model.Employee;
import com.gss.model.Job;
import com.gss.service.EmployeeServiceImpl;

public class LogInEmployee {
	
	public static void main(String[] args){
		
		EmployeeServiceImpl service = new EmployeeServiceImpl();
		Employee emp = service.getEmployeeByUserPass("jeffjeff", "jeffjeff");
		
		if(emp != null){
			System.out.println("Name: " + emp.getStrEmpLastName() + ", " + emp.getStrEmpFirstName());
			System.out.println("Jobs:");
			
			List<Job> jobList = emp.getStrJobQualification();
			
			for(int i = 0; i < emp.getStrJobQualification().size(); i++){
				Job job = jobList.get(i);
				System.out.println("\t" + job.getStrJobDesc());
			}
		}
		else{
			System.out.print("Account Does Not Exist...");
		}
	}

}
