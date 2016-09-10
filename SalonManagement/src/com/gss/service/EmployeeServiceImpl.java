package com.gss.service;

import java.util.List;

import com.gss.dao.EmployeeJDBCRepository;
import com.gss.dao.EmployeeRepository;
import com.gss.model.Employee;
import com.gss.model.EmployeeCategory;

public class EmployeeServiceImpl implements EmployeeService{
	
	public boolean create(Employee emp){
		
		EmployeeRepository repo = new EmployeeJDBCRepository();
		boolean isRecorded = repo.createEmployee(emp);
		
		return isRecorded;
	}
	
	public List<Employee> getAllEmployees(){
		
		EmployeeRepository repo = new EmployeeJDBCRepository();
		
		return repo.getAllEmployee();
	}
	
	public Employee getEmployeeByUserPass(String username, String password){
		
		EmployeeRepository repo = new EmployeeJDBCRepository();
		System.out.println();
		return repo.getEmployeeByUserPass(username, password);
	}
	
	public Employee getEmployeeByID(int intID){
		
		EmployeeRepository repo = new EmployeeJDBCRepository();
		
		return repo.getEmployeeByID(intID);
	}
	
	public boolean updateEmployee(Employee emp){
		
		EmployeeRepository repo = new EmployeeJDBCRepository();
		
		return repo.updateEmployee(emp);
	}
	
	public List<EmployeeCategory> getAllCategory(){
		
		EmployeeRepository repo = new EmployeeJDBCRepository();
				
		return repo.getAllCategory();

	}

	@Override
	public boolean deactivateEmployee(int empID) {
		
		EmployeeRepository repo = new EmployeeJDBCRepository();
		
		return repo.deactivateEmployee(empID);
	}

	@Override
	public List<Employee> getAllEmployeeNoImage() {
		
		EmployeeRepository repo = new EmployeeJDBCRepository();
		
		return repo.getAllEmployeeNoImage();
	}

	@Override
	public List<Employee> queryAllEmployee() {

		EmployeeRepository repo = new EmployeeJDBCRepository();
		
		return repo.queryAllEmployee();
	}

	@Override
	public boolean postion(String positionName, int type) {

		EmployeeRepository repo = new EmployeeJDBCRepository();
		
		return repo.postion(positionName, type);
	}
}
