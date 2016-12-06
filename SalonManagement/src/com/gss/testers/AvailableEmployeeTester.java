package com.gss.testers;

import java.util.List;

import com.gss.dao.Employee.EmployeeDao;
import com.gss.model.Employee;

public class AvailableEmployeeTester {
	
	public static void main(String[] args) {
		
		EmployeeDao dao = new EmployeeDao();
		List<Employee> empList = dao.event("2016-12-4", "05:30:00", "07:30:00");
		
		for(Employee emp : empList) {
			System.out.println(emp.getStrEmpFirstName());
		}
	}

}
