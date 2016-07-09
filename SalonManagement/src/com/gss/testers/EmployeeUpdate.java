package com.gss.testers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gss.model.Employee;
import com.gss.model.Job;
import com.gss.service.EmployeeServiceImpl;
import com.gss.utilities.DateHelper;

public class EmployeeUpdate {

	public static void main(String[] args){
		EmployeeServiceImpl service = new EmployeeServiceImpl();
		Job job = new Job("ADMIN", 1);
		Job job2 = new Job("CASHIER", 0);
		Job job3 = new Job("HAIRSTYLE", 1);
		Job job4 = new Job("HARISTYLE", 1);
		
		List<Job> jobList = new ArrayList<Job>();
		jobList.add(job);
		jobList.add(job2);
		jobList.add(job3);
		jobList.add(job4);
		
		Date mydate = DateHelper.parseDate("1997-02-03");
		Employee emp = new Employee(19, "KATIGBAK", "FRANCIS", "CHAN", mydate, "M", "ROSARIO, PASIG CITY", "09361144842", "A", "jeffjeff", "jeffjeff", "resource/jeff.jpg", null, jobList);
									//(int intEmpID, String strEmpLastName, String strEmpFirstName, String strEmpMiddleName, Date datBirthdate, String strEmpGender, String strAddress, String strEmpContactNo, String strEmpStatus, String strUsername, String strPassword, String empPhoto, byte[] bytActualPhoto, String[] strJobQualification)	
		if(service.updateEmployee(emp))
			System.out.println("Queries Successfully Executed....");
	}
}
