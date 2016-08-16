package com.gss.dao;

import java.util.List;

import com.gss.model.Employee;
import com.gss.model.EmployeeCategory;
import com.gss.model.Job;

public interface EmployeeRepository {
	
	public List<Employee> getAllEmployee();
	
	public Employee getEmployeeByUserPass(String username, String password);
	
	public Employee getEmployeeByID(int intID);
	
	public boolean createEmployee(Employee emp);
	
	public boolean updateEmployee(Employee emp);
	
	public List<EmployeeCategory> getAllCategory();
	
	public List<Job> getEmployeeJob(int empID);
	
	public boolean deactivateEmployee(int empID);
	
	public List<Employee> getAllEmployeeNoImage();
	
	public List<Employee> queryAllEmployee();

}
