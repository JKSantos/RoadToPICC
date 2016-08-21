package com.gss.actions;
import java.util.List;

import com.gss.model.Employee;
import com.gss.model.Product;
import com.gss.model.ProductTag;

public class InventoryTransactionAction {

	private List<Product> productList;
	private List<Employee> employeeList;
	private List<ProductTag> tagList;

	public String execute(){
		
		this.productList = Product.getAllProduct();
		this.employeeList = Employee.getEmployeeList();
		this.tagList = ProductTag.getAllTagList();

		return "success";
	}

	public List<Product> getProductList() {
		return productList;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public List<ProductTag> getTagList() {
		return tagList;
	}
}

