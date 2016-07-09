package com.gss.actions;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONObject;

import com.gss.model.Employee;
import com.gss.service.EmployeeService;
import com.gss.service.EmployeeServiceImpl;

public class employeeListAction {
 private List<Employee> empList;
 
 public JSONArray execute(){
	 EmployeeService empService = new EmployeeServiceImpl();
	 this.empList = empService.getAllEmployees();
	
	 JSONObject responseDetailsJson = new JSONObject();
	 JSONArray jsonArray = new JSONArray(empList);
	 return jsonArray;
 }

public List<Employee> getEmpList() {
	return empList;
}

public void setEmpList(List<Employee> empList) {
	this.empList = empList;
}
}
