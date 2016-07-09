package com.gss.actions;

import java.util.ArrayList;
import java.util.List;

import com.gss.model.Discount;
import com.gss.model.Employee;
import com.gss.model.EmployeeCategory;
import com.gss.model.Job;
import com.gss.service.DiscountService;
import com.gss.service.DiscountServiceImpl;
import com.gss.service.EmployeeServiceImpl;

public class SampleAction {

	
	private List<Discount> discountList;
	
	
	public String execute(){
		
		DiscountService discServ = new DiscountServiceImpl();
		this.setDiscountList(discServ.getAllDiscount());
		
		return "success";
	}


	public List<Discount> getDiscountList() {
		return discountList;
	}


	public void setDiscountList(List<Discount> discountList) {
		this.discountList = discountList;
	}

	
	
}
