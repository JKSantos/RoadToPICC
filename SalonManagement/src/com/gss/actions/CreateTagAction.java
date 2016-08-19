package com.gss.actions;

import java.sql.Date;

import com.gss.model.Employee;
import com.gss.model.Product;
import com.gss.model.ProductTag;
import com.gss.service.ProductTagImpl;
import com.gss.service.ProductTags;

public class CreateTagAction {
	
	private int intProductID;
	private int intQuantity;
	private int intTagType;
	private int employees;
	
	public String execute(){
		
		boolean recorded = false;
		
		Product product = Product.createNullProduct(intProductID);
		
		ProductTags createTag = new ProductTagImpl();
		recorded = createTag.defectiveTag(new ProductTag(1, product, new Date(employees), intTagType, Employee.createNullEmployee(employees), intQuantity));
		System.out.println(employees);
		if(recorded == true){
			return "success";
			
		}
		else{
			return "failed";
		}
	}

	public int getIntProductID() {
		return intProductID;
	}

	public void setIntProductID(int intProductID) {
		this.intProductID = intProductID;
	}

	public int getIntQuantity() {
		return intQuantity;
	}

	public void setIntQuantity(int intQuantity) {
		this.intQuantity = intQuantity;
	}

	public int getIntTagType() {
		return intTagType;
	}

	public void setIntTagType(int intTagType) {
		this.intTagType = intTagType;
	}

	public void setEmployees(int employees) {
		this.employees = employees;
	}
	
	

}
