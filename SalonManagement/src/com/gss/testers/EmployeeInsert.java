package com.gss.testers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gss.model.Employee;
import com.gss.model.Job;
import com.gss.service.EmployeeServiceImpl;
import com.gss.utilities.DateHelper;

public class EmployeeInsert {
	
	public static void main(String[] args){
		
		EmployeeServiceImpl service = new EmployeeServiceImpl();
		
		Job job = new Job("ADMIN", 1);
		List<Job> jobList = new ArrayList<Job>();
		jobList.add(job);
		
		
		Date mydate = DateHelper.parseDate("1997-02-03");
		Employee emp = new Employee(1, "CHICHARON", "NI", "MANG JUAN", mydate, "M", "ROSARIO, PASIG CITY", "09361144842", "A", "jksantos03", "jksantos03", "resource/jeff.jpg", null, jobList);
									//(int intEmpID, String strEmpLastName, String strEmpFirstName, String strEmpMiddleName, Date datBirthdate, String strEmpGender, String strAddress, String strEmpContactNo, String strEmpStatus, String strUsername, String strPassword, String empPhoto, byte[] bytActualPhoto, String[] strJobQualification)	
		if(service.create(emp))
			System.out.println("success");
	}

}
