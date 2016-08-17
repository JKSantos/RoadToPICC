package com.gss.actions;

import java.sql.Date;

import com.gss.model.Employee;
import com.gss.model.Product;
import com.gss.model.ProductTag;
import com.gss.service.ProductTagImpl;
import com.gss.service.ProductTags;

public class UpdateStockAction {
	
	private int intProductID;
	private int intQuantity;
	private int intType;
	private int employees;
	
	public String execute(){
		
		boolean recorded = false;
		
		Product product = Product.createNullProduct(intProductID);
		
		if(intType == 1){
			ProductTags addStock = new ProductTagImpl();
			recorded = addStock.addStock(new ProductTag(1, product, new Date(employees), intType, Employee.createNullEmployee(employees), intQuantity));
		}
		else{
			ProductTags addStock = new ProductTagImpl();
			recorded = addStock.subtractStock(new ProductTag(1, product, new Date(employees), intType, Employee.createNullEmployee(employees), intQuantity));
		}
		
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

	public int getIntType() {
		return intType;
	}

	public void setIntType(int intType) {
		this.intType = intType;
	}

	public void setEmployees(int employees) {
		this.employees = employees;
	}
}
