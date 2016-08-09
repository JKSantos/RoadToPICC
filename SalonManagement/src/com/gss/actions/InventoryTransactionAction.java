package com.gss.actions;
import java.util.List;

import com.gss.model.Employee;
import com.gss.model.Product;

public class InventoryTransactionAction {

	private List<Product> productList;
	private List<Employee> employeeList;

	public String execute(){
		
		this.productList = Product.getAllProduct();
		this.employeeList = Employee.getEmployeeList();
		
		return "success";
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}
}

